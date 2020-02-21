package fr.patounes.hashcode.pizza;

import fr.patounes.hashcode.pizza.data.Pizza;
import fr.patounes.hashcode.pizza.data.Slice;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "pizza-cutter", description= "cuts some pizzas")
public class MainPizza implements Callable<Integer> {

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
            Pizza pizza = PizzaParser.fromTextFile(inputFile);

            // solve problem
            PizzaCutter cutter = new SimplePizzaCutter();
            List<Slice> slices = cutter.cutPizza(pizza);

            // print solutions
            System.out.println("Writing results to " + outputFile);
            PizzaParser.printSlices(slices, outputFile);
            System.out.println("----------------");
        }
        System.out.println("Done");
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new MainPizza()).execute(args);
        System.exit(exitCode);
    }
}
