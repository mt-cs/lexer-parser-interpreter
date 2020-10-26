/*
    grammar:
    EXPR -> NUMBER
    EXPR -> IDENTIFIER
    EXPR -> EXPR OP EXPR
    ASSIGNMENT -> IDENTIFIER ASSIGNOP EXPR
    EXPRASSIGNMENT -> IDENTIFIER EXPRASSIGN EXPR
    OP -> + | - | * | /
    NUMBER -> INTEGER
    NUMBER -> FLOAT
    INTEGER -> [0-9]+
    FLOAT -> [0-9]+.[0-9]+
    IDENTIFIER -> [A-Za-z]+[A-Za-z0-9]*
    ASSIGNMENT -> =
    EXPRASSIGN -> #


 */
import java.util.HashMap;
import java.util.List;
import java.util.Stack;


public class ExpressionTree {

    Node root;
    static HashMap<String, Integer> precedence;
    static {
        precedence = new HashMap<>();
        precedence.put("+", 1);
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);
    }

    public ExpressionTree() {
        root = null;
    }

    public ExpressionTree(Token t, ExpressionTree leftTree, ExpressionTree rightTree) {
        this.root = new Node(t);
        this.root.left = leftTree.root;
        this.root.right = rightTree.root;
    }

    public ExpressionTree(Token t) {
        this.root = new Node(t);
        this.root.left = null;
        this.root.right = null;
    }

    public static class Node {
        String type;
        String val;
        Node left;
        Node right;

        Node() {
            type = "";
            val = "";
            left = null;
            right = null;
        }

        Node(Token t) {
            this.type = t.type;
            this.val = t.value;
        }

        public String toString() {
            return this.type + ":" + this.val;
        }

        /* evaluate an Expression tree via postorder traversal. */

        public double eval(SymbolTable table) {

        }


    }

/*parse variable identifiers */
    public static Node parseIdentifier(Token t) {

    }

    /* parse = */
    public static Node parseAssignmentOp(Token t) {

    }
    /* parse +,-,*,/ */
    public static Node parseOperator(Token t) {

    }

    /* parse the expr (#) operator */
    public static Node parseExprOperator(Token t) {

    }

    /* parse a number or identifier and return the appropriate expression tree */

    public static Node parseNumberOrIdentifier(Token t) {


    }

    /* a helper method to tell which of two operators has precendence. */
    public static boolean hasPrecedence(String op1, String op2) {
        if (precedence.get(op1) > precedence.get(op2)) {
            return true;
        } else {
            return false;
        }
    }

    /* parseExpression. Use the shunting algorithm to parse the list of tokens into an expression tree. */

    public static Node parseExpression(List<Token> tokenList) {

    }



    /* parse an assignment statement - grab the variable and assignment operator, parse the expression on the right-hand side,
        evaluate it, and store the result in the symbol table.
     */
    public static Node parseAssignment(List<Token> tokenList, SymbolTable table) {

    }

    /* Similar to parseAssignment, except that we're not going to evaluate the expression. Instead, store the expression tree
        in the symbol table.
     */

    public static Node parseExprAssignment(List<Token> tokenList, SymbolTable table) {

    }

    /* take a list of tokens, look ahead to see what we are parsing, and call the appropriate method */

    public static Node parseTokens(List<Token> tokenList, SymbolTable table) {

    }

    /* wrapper method for parseTokens */
    public void parse(List<Token> tokenList, SymbolTable table) {
        root = parseTokens(tokenList, table);
    }

    /* wrapper method for eval */
    public double evaluate(SymbolTable table) {
        return root.eval(table);
    }

}
