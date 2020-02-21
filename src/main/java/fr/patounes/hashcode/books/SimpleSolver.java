package fr.patounes.hashcode.books;

import fr.patounes.hashcode.books.data.*;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleSolver implements BookSolver {

    private Library onSignup;
    private int signupIndex;
    private int signupDays;
    private List<Library> signup;
    private Map<Integer, Integer> nbScannedPerLibrary;
    private Set<Integer> seenBefore;
    private double targetRatio;
    private double minRatio;

    public SimpleSolver() {
        onSignup = null;
        signupIndex = 0;
        signupDays = 0;
        signup = new LinkedList<>();
        nbScannedPerLibrary = new HashMap<>();
        seenBefore = new HashSet<>();
        targetRatio = 0.99;
        minRatio = 0.90;
    }

    private void signup(Problem problem) {
        if (signupIndex < problem.getNbLibraries()) {
            if (onSignup == null) {
                // onSignup = problem.getLibrary(signupIndex);
                for(int i = signupIndex; i < problem.getNbLibraries(); i++) {
                    Library current = problem.getLibrary(i);
                    Set<Integer> books = current.getBooks().stream().map(b -> b.getId()).collect(Collectors.toSet());
                    books.removeAll(seenBefore);
                    int ratio = books.size() / current.getNbBooks();
                    if (ratio >= targetRatio && current.getSignupDuration() < problem.getDuration() * 0.2) {
                        onSignup = current;
                        signupIndex = i;
                        targetRatio -= (0.99 - minRatio) / problem.getDuration();
                        break;
                    }
                }
                if (onSignup == null) {
                    problem.getLibrary(signupIndex);
                }
            }
            signupDays++;
            if (onSignup != null) {
                if (signupDays == onSignup.getSignupDuration()) {
                    signup.add(onSignup);
                    nbScannedPerLibrary.put(onSignup.getId(), 0);
                    signupIndex++;
                    signupDays = 0;
                    onSignup = null;
                }
            }
        }
    }

    private void scan(Problem problem) {
        for(Library library: signup) {
            int parallelism = library.getNbParallelScanning();
            int scannedAlready = nbScannedPerLibrary.get(library.getId());

            int x = Math.min(scannedAlready + parallelism, library.getNbBooks());
            for(int i = scannedAlready; i < x; i++) {
                seenBefore.add(library.getBook(i).getId());
            }
            nbScannedPerLibrary.put(library.getId(), x);
        }
    }


    @Override
    public Output solve(Problem problem) {
        problem.sort();
        // solve
        onSignup = problem.getLibrary(signupIndex);
        for(int jour = 0; jour < problem.getDuration() - 1; jour++) {
            signup(problem);
            scan(problem);
        }
        // write
        /*System.out.println(signup);
        System.out.println(nbScannedPerLibrary);*/
        int nbLibraries = signup.size();
        List<OutputLibrary> libraries = new LinkedList<>();

        for(Library library: signup) {
            int id = library.getId();
            int nbToScan = nbScannedPerLibrary.get(id);
            List<Book> books = new LinkedList<>();

            int cpt = 0;
            for(Book book: library.getBooks()) {
                if (cpt >= nbToScan) {
                    break;
                }
                books.add(book);
                cpt++;
            }
            libraries.add(new OutputLibrary(id, nbToScan, books));
        }

        return new Output(nbLibraries, libraries);
    }
}
