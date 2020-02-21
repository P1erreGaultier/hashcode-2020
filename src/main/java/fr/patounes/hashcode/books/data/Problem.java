package fr.patounes.hashcode.books.data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Problem {
    private int nbBooks;
    private int nbLibraries;
    private int duration;
    private List<Library> libraries;

    public Problem(int nbBooks, int nbLibraries, int duration) {
        this.nbBooks = nbBooks;
        this.nbLibraries = nbLibraries;
        this.duration = duration;
        libraries = new LinkedList<>();
    }

    public int getNbBooks() {
        return nbBooks;
    }

    public void setNbBooks(int nbBooks) {
        this.nbBooks = nbBooks;
    }

    public int getNbLibraries() {
        return nbLibraries;
    }

    public void setNbLibraries(int nbLibraries) {
        this.nbLibraries = nbLibraries;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void addLibrary(Library library) {
        libraries.add(library);
    }

    public Library getLibrary(int index) {
        return this.libraries.get(index);
    }

    private int computeScore(Library library) {
        // int score = (duration - library.getSignupDuration()) + library.getNbParallelScanning();
        int totalBookScore = library.getBooks().stream().map(book -> book.getScore()).reduce((x, y) -> x + y).get();
        int scorePerDay = totalBookScore / library.getNbParallelScanning();
        //return scorePerDay / library.getSignupDuration();
        return scorePerDay * (duration - library.getSignupDuration());
    }

    public void sort() {
        Collections.sort(libraries, (l1, l2) -> {
            if (computeScore(l1) == computeScore(l2)) {
                return 0;
            } else if (computeScore(l1) > computeScore(l2)) {
                return -1;
            }
            return 1;
        });
    }

    @Override
    public String toString() {
        return "Problem{" +
                "nbBooks=" + nbBooks +
                ", nbLibraries=" + nbLibraries +
                ", duration=" + duration +
                ", libraries=" + libraries +
                '}';
    }
}
