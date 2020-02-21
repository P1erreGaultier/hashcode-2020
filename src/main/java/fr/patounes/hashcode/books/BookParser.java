package fr.patounes.hashcode.books;

import fr.patounes.hashcode.books.data.*;
import fr.patounes.hashcode.pizza.data.Slice;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BookParser {

    public static Problem fromText(String path) {
        int bufferSize = 8 * 1024;
        try {
            Problem problem;
            try (BufferedReader reader = new BufferedReader(new FileReader(path), bufferSize)) {
                String line = reader.readLine();
                String[] firstLine = line.split(" ");

                // read problem inputs
                int nbBooks = Integer.parseInt(firstLine[0]);
                int nbLibraries = Integer.parseInt(firstLine[1]);
                int duration = Integer.parseInt(firstLine[2]);
                problem = new Problem(nbBooks, nbLibraries, duration);

                // build books scores
                Map<Integer, Integer> scoresPerBooks = new HashMap<>();
                line = reader.readLine();
                String[] secondLine = line.split(" ");
                for(int i = 0; i < nbBooks; i++) {
                    scoresPerBooks.put(i, Integer.parseInt(secondLine[i]));
                }

                // build libraries content
                for(int i = 0; i < nbLibraries; i++) {
                    // library metadata
                    line = reader.readLine();
                    String[] libraryDescription = line.split(" ");
                    int booksInLib = Integer.parseInt(libraryDescription[0]);
                    int signupDuration = Integer.parseInt(libraryDescription[1]);
                    int nbParallelScanning = Integer.parseInt(libraryDescription[2]);

                    Library library = new Library(i, booksInLib, signupDuration, nbParallelScanning);

                    // books in the library
                    line = reader.readLine();
                    String[] booksLine = line.split(" ");

                    for(int j = 0; j < booksInLib; j++) {
                        int bookID = Integer.parseInt(booksLine[j]);
                        library.addBook(new Book(bookID, scoresPerBooks.get(bookID)));
                    }

                    library.sortBook();
                    problem.addLibrary(library);
                }

                return problem;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void print(Output solution, String outputPath) throws IOException {
        File file = new File(outputPath);
        // create file beforehand
        file.createNewFile();
        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            // line 1: nb libraries
            writer.println(solution.getNbLibraries());

            // one line per library
            for(OutputLibrary library: solution.getLibraries()) {
                // first line: library id + nb books to send
                writer.println(library.getId() + " " + library.getNbToScan());

                // second line: books to send
                String line = "";
                for(Book book: library.getBooks()) {
                    line += book.getId() + " ";
                }
                line.trim();
                writer.println(line);
            }
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
