import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public static final String INT="INT";
    public static final String FLOAT="FLOAT";
    public static final String IDENTIFER="ID";
    public static final String OPERATOR="OP";
    public static final String ASSIGNMENT="ASSIGN";
    public static final String EXPRASSIGNMENT="EXPR";
    public static final String EOFTOKEN="EOF";
    public String buffer;

    public Lexer(String fileName) {


    }

    public Lexer() {
        buffer = "";
    }


    public void getInputFromFile(String fileName)  {

    }

    public void getInputFromString(String s) {
        buffer = s;
    }

    /* scan ahead in the string to the end of the current token.
    ref is the current pointer in the string.

     */
    public Token getNextToken(int ref) {


    }

    public Token getOperator(int ref) {

    }

    public String getIdentifier(int ref) {

    }

    public String getNumber(int ref) {

    }


    /* iterate through the buffer and return a list of tokens. */
    public List<Token> getAllTokens() {

    }



}



