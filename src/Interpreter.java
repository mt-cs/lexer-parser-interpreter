import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * The interpreter is the engine that runs everything
 */
public class Interpreter {
    Lexer luthor;
    SymbolTable variables;

    /**
     * empty constructor
     */
    public Interpreter() {
        luthor = new Lexer();
        variables = new SymbolTable();
    }

    /**
     * a method that can launch an interactive interpreter.
     * Read in a line, execute it, and print out the result.
     * Also add a verbose option that prints out the input and symbol table.
     */
    public void runShell() {
        Scanner sc = new Scanner(System.in);
        List<Token> tokenList;
        System.out.println("Do you want to print out input, output, and symbol table while parsing? (y/n)");
        String verbose = sc.nextLine();
        System.out.println("Enter an expression or 'q' to exit");
        while(true) {
            System.out.println(">>");
            String buffer = sc.nextLine();
            if (buffer.equalsIgnoreCase("q")){
                break;
            }
            luthor.getInputFromString(buffer);
            tokenList = luthor.getAllTokens();
            ExpressionTree tree = new ExpressionTree();
            tree.parse(tokenList, variables);
            System.out.println(tree.evaluate(variables));

            if (verbose.equalsIgnoreCase("y")) {
                System.out.println("\nInput: " + luthor.buffer);
                System.out.println("Output: " + tree.evaluate(variables) + "\n");
                System.out.println("Symbol Table: " + variables.toString() + "\n");
                System.out.println("=======================================\n");
            }
        }
    }

    /**
     * a method that can read a series of lines in from a file, execute each one, and print out the result.
     * Also add a verbose option that prints out the input and symbol table.
     * @param filename name of file
     */
    public void executeFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                List<Token> tokenList;
                luthor.getInputFromString(scanner.nextLine());
                tokenList = luthor.getAllTokens();
                ExpressionTree tree = new ExpressionTree();
                tree.parse(tokenList, variables);

                System.out.println("Input: " + luthor.buffer);
                System.out.println("Output: " + tree.evaluate(variables) +"\n");
                System.out.println("Symbol Table: " + variables.toString()  +"\n");
                System.out.println("=======================================\n");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        Interpreter shell = new Interpreter();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter 'r' for run shell mode or 'e' to execute file: ");
        String option = sc.nextLine();
        if (option.equalsIgnoreCase("r")) {
            shell.runShell();
        } else if (option.equalsIgnoreCase("e")){
            //        String fileName = "test2.txt";
            System.out.print("Please enter a filename: ");
            String fileName = sc.nextLine();
            shell.executeFile(fileName);
        } else {
            throw new IllegalArgumentException("Invalid option, please try again");
        }
    }
}
