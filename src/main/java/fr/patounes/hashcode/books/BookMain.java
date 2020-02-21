package fr.patounes.hashcode.books;

import fr.patounes.hashcode.books.data.Output;
import fr.patounes.hashcode.books.data.Problem;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "pizza-cutter", description= "cuts some pizzas")
public class BookMain implements Callable<Integer> {
    @CommandLine.Parameters(description = "Path to the folder that contains files")
    private String[] files;

    @CommandLine.Option(names = "-o", defaultValue = "src/main/resources/output/", description = "Output directory")
    private String outputDirectory;

    @Override
    public Integer call() throws Exception {
        System.out.println("Initialization");
        // init output folder (if not exists)
        File output = new File(outputDirectory);
        output.mkdirs();

        // process each file
        System.out.println("----------------");
        for(String inputFile: files) {
            String fileName = Paths.get(inputFile).getFileName().toString();
            String outputFile = Paths.get(outputDirectory, fileName + ".out").toString();

            System.out.println("Processing file " + fileName);
            // read input
            Problem problem = BookParser.fromText(inputFile);
            // solve problem
            BookSolver solver = new SimpleSolver();
            Output solution = solver.solve(problem);
            // print solutions
            System.out.println("Writing results to " + outputFile);
            BookParser.print(solution, outputFile);
            System.out.println("----------------");
        }
        System.out.println("Done");
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new BookMain()).execute(args);
        System.exit(exitCode);
    }
}
