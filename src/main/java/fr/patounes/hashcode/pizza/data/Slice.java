package fr.patounes.hashcode.pizza.data;

public class Slice {
    private int startX;
    private int endX;
    private int startY;
    private int endY;

    public Slice(int startX, int endX, int startY, int endY) {
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public Slice merge(Slice other) {
        int startX = Math.min(this.startX, other.getStartX());
        int endX = Math.max(this.endX, other.getEndX());
        int startY = Math.min(this.startY, other.getStartY());
        int endY = Math.max(this.endY, other.getEndY());
        return new Slice(startX, endX, startY, endY);
    }
}
