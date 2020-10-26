import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interpreter {
    Lexer luthor;
    SymbolTable variables;


    public Interpreter() {
        luthor = new Lexer();
        variables = new SymbolTable();
    }

    /* a method that can launch an interactive interpreter.
        Read in a line, execute it, and print out the result.
        Also add a verbose option that prints out the input and symbol table.
     */
    public void runShell() {


    }

    /* a method that can read a series of lines in from a file, execute each one,
    and print out the result.
        Also add a verbose option that prints out the input and symbol table.

     */
    public void executeFile(String filename) {

    }

    public static void main (String[] args) {
        Interpreter shell = new Interpreter();
        shell.runShell();
    }

}
