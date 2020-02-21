package fr.patounes.hashcode.pizza.data;

import java.util.HashMap;
import java.util.Map;

public class Pizza {
    private int numRows;
    private int numColumns;
    private int minEachIngredientPerSlice;
    private int maxCellsPerSlice;
    private Map<String, Cell> cells;

    public Pizza(int numRows, int numColumns, int minEachIngredientPerSlice, int maxCellsPerSlice) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.minEachIngredientPerSlice = minEachIngredientPerSlice;
        this.maxCellsPerSlice = maxCellsPerSlice;
        this.cells = new HashMap<>();
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

    public int getMinEachIngredientPerSlice() {
        return minEachIngredientPerSlice;
    }

    public void setMinEachIngredientPerSlice(int minEachIngredientPerSlice) {
        this.minEachIngredientPerSlice = minEachIngredientPerSlice;
    }

    public int getMaxCellsPerSlice() {
        return maxCellsPerSlice;
    }

    public void setMaxCellsPerSlice(int maxCellsPerSlice) {
        this.maxCellsPerSlice = maxCellsPerSlice;
    }

    private String makeCellKey(int x, int y) {
        return String.format("x=%s;y=%s", x, y);
    }

    public void addCell(int x, int y, Cell cell) {
        cells.put(makeCellKey(x, y), cell);
    }

    public Cell getCell(int x, int y) {
        return cells.get(makeCellKey(x, y));
    }

    @Override
    public String toString() {
        String output = "";
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numColumns; j++) {
                output += getCell(i, j).toString();
            }
            output += "\n";
        }
        return output;
    }
}
