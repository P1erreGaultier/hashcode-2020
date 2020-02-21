package fr.patounes.hashcode.pizza.data;

public class Cell {
    private int x;
    private int y;
    private Ingredient type;
    private boolean cutOut;

    public Cell(int x, int y, Ingredient type) {
        this.x = x;
        this.y = y;
        this.type = type;
        cutOut = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Ingredient getType() {
        return type;
    }

    public void setType(Ingredient type) {
        this.type = type;
    }

    public boolean isCutOut() {
        return cutOut;
    }

    public void setCutOut(boolean cutOut) {
        this.cutOut = cutOut;
    }

    @Override
    public String toString() {
        if (type == Ingredient.Tomato) {
            return "T";
        }
        return "M";
    }
}
