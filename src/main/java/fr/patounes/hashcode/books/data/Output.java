package fr.patounes.hashcode.books.data;

import java.util.List;

public class Output {
    private int nbLibraries;
    private List<OutputLibrary> libraries;

    public Output(int nbLibraries, List<OutputLibrary> libraries) {
        this.nbLibraries = nbLibraries;
        this.libraries = libraries;
    }

    public int getNbLibraries() {
        return nbLibraries;
    }

    public void setNbLibraries(int nbLibraries) {
        this.nbLibraries = nbLibraries;
    }

    public List<OutputLibrary> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<OutputLibrary> libraries) {
        this.libraries = libraries;
    }
}
