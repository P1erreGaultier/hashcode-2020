package fr.patounes.hashcode.books.data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Library {
    private int id;
    private int nbBooks;
    private int signupDuration;
    private int nbParallelScanning;
    private List<Book> books;

    public Library(int id, int nbBooks, int signupDuration, int nbParallelScanning) {
        this.id = id;
        this.nbBooks = nbBooks;
        this.signupDuration = signupDuration;
        this.nbParallelScanning = nbParallelScanning;
        books = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void sortBook() {
        Collections.sort(books, ((b1, b2) -> {
            if (b1.getScore() == b2.getScore()) {
                return 0;
            } else if (b1.getScore() > b2.getScore()) {
                return -1;
            }
            return 1;
        }));
    }

    public int getNbBooks() {
        return nbBooks;
    }

    public void setNbBooks(int nbBooks) {
        this.nbBooks = nbBooks;
    }

    public int getSignupDuration() {
        return signupDuration;
    }

    public void setSignupDuration(int signupDuration) {
        this.signupDuration = signupDuration;
    }

    public int getNbParallelScanning() {
        return nbParallelScanning;
    }

    public void setNbParallelScanning(int nbParallelScanning) {
        this.nbParallelScanning = nbParallelScanning;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book getBook(int index) {
        return books.get(index);
    }

    @Override
    public String toString() {
        return "Library{" +
                "nbBooks=" + nbBooks +
                ", signupDuration=" + signupDuration +
                ", nbParallelScanning=" + nbParallelScanning +
                ", books=" + books +
                '}';
    }
}
