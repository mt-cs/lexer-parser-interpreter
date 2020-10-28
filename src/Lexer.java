import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Lexer is a tool that takes a String as input and emits a list of Tokens.
 */
public class Lexer {
    public static final String INT="INT";
    public static final String FLOAT="FLOAT";
    public static final String IDENTIFER="ID";
    public static final String OPERATOR="OP";
    public static final String ASSIGNMENT="ASSIGN";
    public static final String EXPRASSIGNMENT="EXPR";
    public static final String EOFTOKEN="EOF";
    public String buffer;

    /**
     * Constructor with parameter:
     * @param fileName name of file
     */
    public Lexer(String fileName) {
        getInputFromFile(fileName);
    }

    /**
     * empty constructor
     */
    public Lexer() {
        buffer = "";
    }

    /**
     * takes input filename and assign to lexer's buffer
     * @param fileName name of file
     */
    public void getInputFromFile(String fileName)  {
        try {
            Path filePath = Paths.get(fileName);
            byte[] allBytes = Files.readAllBytes(filePath);
            buffer = new String (allBytes);
        } catch (IOException e) {
            System.out.println ("You did not enter a valid file name in the run arguments.");
            System.out.println ("Please enter a string to be parsed: ");
            Scanner scanner = new Scanner(System.in);
            buffer = scanner.nextLine();
        }
    }

    /**
     * takes input from string
     * @param s input string
     */
    public void getInputFromString(String s) {
        buffer = s;
    }

    /**
     * scan ahead in the string to the end of the current token.
     * @param ref the current pointer in the string.
     * @return t Token
     */
    public Token getNextToken(int ref) {
        Token t;
        t = getEOF(ref);
        if (t != null) {
            return t;
        }
        t = getOperator(ref);
        if (t != null) {
            return t;
        }
        t = getAssignment(ref);
        if ( t != null) {
            return t;
        }
        t = getExpressionAssignment(ref);
        if (t != null) {
            return t;
        }
        t = getIdentifier(ref);
        if (t != null) {
            return t;
        }
        t = getNumber(ref); // where should I handle the exception float should the program stop running?
        return t;
    }

    /**
     * Iterate through the buffer and return a list of tokens.
     * @return allToken ArrayList
     */
    public List<Token> getAllTokens() {
        ArrayList<Token> allToken = new ArrayList<>();
        for(int index = 0; index < this.buffer.length() ; index++){
            Token token = getNextToken(index);
            if(token != null){
                index += token.length()-1;
                allToken.add(token);
            }
        }
        // allToken.add(new Token(EOFTOKEN, "EOF"));
        return allToken;
    }

    /**
     * Get +, -, *, /
     * @param ref the current index in the string
     * @return new Token if operator is found otherwise null
     */
    public Token getOperator(int ref) {
        if (buffer.charAt(ref) == '+' || buffer.charAt(ref) == '-' || buffer.charAt(ref) == '*' ||
            buffer.charAt(ref) == '/'){
            return new Token(OPERATOR, Character.toString(buffer.charAt(ref)));
        } else {
            System.out.println(buffer.charAt(ref));
            return null;
            // throw new IllegalArgumentException("Lexical error: " + buffer.charAt(ref));
        }
    }

    /**
     * Get #
     * @param ref the current index in the string
     * @return new Token if operator is found otherwise null
     */
    public Token getExpressionAssignment(int ref){
        if (buffer.charAt(ref) == '#') {
            return new Token(EXPRASSIGNMENT, "#");
        } else {
            return null;
        }
    }

    /**
     * Get =
     * @param ref the current index in the string
     * @return new Token if operator is found otherwise null
     */
    public Token getAssignment(int ref){
        if (buffer.charAt(ref) == '=') {
            return new Token(ASSIGNMENT, "=");
        } else {
            return null;
        }
    }

    /**
     * Get end of file
     * @param ref the current index in the string
     * @return new Token if operator is found otherwise null
     */
    public Token getEOF(int ref){
        if (buffer.charAt(ref) == '\n') {
            return new Token(EOFTOKEN, "eof");
        } else {
            return null;
        }
    }

    /**
     * Get Identifier: A letter, followed by letters or numbers.
     * @param ref the current index in the string
     * @return new Token if operator is found otherwise null
     */
    public Token getIdentifier(int ref) {
        StringBuilder temp = new StringBuilder();
        if (Character.isLetter(buffer.charAt(ref))) {
            while (Character.isLetterOrDigit(buffer.charAt(ref)) && ref < buffer.length()) {
                temp.append(buffer.charAt(ref));
                ref++;
            }
        } else {
            return null;
            //throw new IllegalArgumentException("Lexical error: " + buffer.charAt(ref) + " doesn't start with a letter");
        }
        return new Token(IDENTIFER, temp.toString());
    }

    /**
     * Get integer or floats
     * @param ref the current index in the string
     * @return new Token if operator is found otherwise null
     */
    public Token getNumber(int ref) {
        StringBuilder temp = new StringBuilder();
        int decimalCount = 0;
        if (Character.isDigit(buffer.charAt(ref))) {
            while (ref < buffer.length() && Character.isDigit(buffer.charAt(ref)) || buffer.charAt(ref) == '.') {
                temp.append(buffer.charAt(ref));
                ref++;
                if (buffer.charAt(ref) == '.') {
                    decimalCount++;
                }
                if (decimalCount > 1) {
                    throw new IllegalArgumentException("Lexical error: float value is invalid ");
                }
            }
        } else {
            return null;
            // throw new IllegalArgumentException("Lexical error: " + buffer.charAt(ref));
        }

        if (decimalCount == 1) {
            return new Token(FLOAT, temp.toString());
        } else {
            return new Token(INT, temp.toString());
        }
    }
}



