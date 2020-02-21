package fr.patounes.hashcode.pizza;

import fr.patounes.hashcode.pizza.data.Ingredient;
import fr.patounes.hashcode.pizza.data.Pizza;
import fr.patounes.hashcode.pizza.data.Slice;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SimplePizzaCutter implements PizzaCutter {

    private boolean sliceIsValid (Slice slice, Pizza pizza) {
        Set<Ingredient> ingredients = new HashSet<>();
        int nbCells = 0;

        for(int x = slice.getStartX(); x < slice.getEndX(); x++) {
            for(int y = slice.getStartY(); y < slice.getEndY(); y++) {
                ingredients.add(pizza.getCell(x, y).getType());
                nbCells++;
            }
        }
        return ingredients.size() <= pizza.getMinEachIngredientPerSlice() && nbCells <= pizza.getMaxCellsPerSlice();
    }

    @Override
    public List<Slice> cutPizza(Pizza pizza) {
        // The strategy is the following:
        // 1) cut the pizza in slices of a single cell
        // 2) try to merge cells together until we cannot merge anything

        List<Slice> slices = new LinkedList<>();

        // generate slice of size 1
        for(int i = 0; i < pizza.getNumRows(); i++) {
            for (int j = 0; j < pizza.getNumColumns(); j++) {
                slices.add(new Slice(i, i, j, j));
            }
        }

        // try to merge slices until we hit stability
        boolean shouldContinue = true;
        while (shouldContinue) {
            // reset flag at start
            shouldContinue = false;
            List<Slice> tempSlices = new LinkedList<>();
            // stores the slice already visited
            List<Integer> processedSlices = new LinkedList<>();
            for(int i = 0; i < slices.size(); i++) {
                if (!processedSlices.contains(i)) {
                    Slice currentSlice = slices.get(i);
                    boolean hasMerged = false;
                    // try to merge with another slice
                    for(int j = 0; j < slices.size(); j++) {
                        if (i != j && !processedSlices.contains(j)) {
                            Slice candidate = currentSlice.merge(slices.get(j));
                            // we found a valid merge
                            if (sliceIsValid(candidate, pizza)) {
                                processedSlices.add(i);
                                processedSlices.add(j);
                                tempSlices.add(candidate);
                                hasMerged = true;
                                shouldContinue = true;
                                break;
                            }
                        }
                    }
                    // if we never managed to merge this slide, put it into the list
                    // we may be able to merge it in the next round
                    if (!hasMerged) {
                        tempSlices.add(currentSlice);
                    }
                }
            }
            slices = new LinkedList<>(tempSlices);
        }
        // DEBUG check if your slides are valid
        /*for(Slice slice: slices) {
            System.out.println(sliceIsValid(slice, pizza));
        }*/
        return slices;
    }
}
