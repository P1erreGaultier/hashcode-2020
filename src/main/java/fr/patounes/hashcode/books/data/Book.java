package fr.patounes.hashcode.books.data;

public class Book {
    private int id;
    private int score;

    public Book(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", score=" + score +
                '}';
    }
}
