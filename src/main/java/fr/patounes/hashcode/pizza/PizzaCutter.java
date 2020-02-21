package fr.patounes.hashcode.pizza;

import fr.patounes.hashcode.pizza.data.Pizza;
import fr.patounes.hashcode.pizza.data.Slice;

import java.util.List;

public interface PizzaCutter {
    List<Slice> cutPizza(Pizza pizza);
}
