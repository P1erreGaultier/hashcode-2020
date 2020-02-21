package fr.patounes.hashcode.books.data;

import java.util.List;

public class OutputLibrary {
    private int id;
    private int nbToScan;
    private List<Book> books;

    public OutputLibrary(int id, int nbToScan, List<Book> books) {
        this.id = id;
        this.nbToScan = nbToScan;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbToScan() {
        return nbToScan;
    }

    public void setNbToScan(int nbToScan) {
        this.nbToScan = nbToScan;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
