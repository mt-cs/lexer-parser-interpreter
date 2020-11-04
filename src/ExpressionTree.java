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

/**
 * a binary tree in which the leaves are operands
 * and the parent nodes are operators
 */
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

    /**
     * empty constructor that set up root to be null
     */
    public ExpressionTree() {
        root = null;
    }

    /**
     * constructor with three params:
     * @param t token
     * @param leftTree left tree
     * @param rightTree right tree
     */
    public ExpressionTree(Token t, ExpressionTree leftTree, ExpressionTree rightTree) {
        this.root = new Node(t);
        this.root.left = leftTree.root;
        this.root.right = rightTree.root;
    }

    /**
     * constructor with one parameter
     * @param t token
     */
    public ExpressionTree(Token t) {
        this.root = new Node(t);
        this.root.left = null;
        this.root.right = null;
    }

    /**
     * inner class Node
     */
    public static class Node {
        String type;
        String val;
        Node left;
        Node right;

        /**
         * empty constructor
         */
        Node() {
            type = "";
            val = "";
            left = null;
            right = null;
        }

        /**
         * added constructor
         * @param type lexer type
         * @param val value
         * @param left left node
         * @param right right node
         */
        public Node(String type, String val, Node left, Node right) {
            this.type = type;
            this.val = val;
            this.left = left;
            this.right = right;
        }

        /**
         * Token type value
         * @param t Token
         */
        Node(Token t) {
            this.type = t.type;
            this.val = t.value;
        }

        /**
         * toString method
         */
        public String toString() {
            String printTree = this.type + ":" + this.val ;
            if (this.left != null){
                printTree += "\nLeft Tree: " + this.left.toString();
            }
            if (this.right != null){
                printTree += "\nRight Tree: " + this.right.toString();
            }
            return printTree;
        }

        /**
         * evaluate an Expression tree via postorder traversal.
         * @param table symbolTable
         * @return leaf value or result of root evaluation
         */
        public double eval(SymbolTable table) {
            if (this.type == Lexer.FLOAT || this.type.equals(Lexer.INT)){
                return Double.parseDouble(this.val);
            } else if (this.type.equals(Lexer.IDENTIFER)){
                if (table.has(this.val)){
                    return table.getValue(this.val);
                } else if (table.hasFunction(this.val)){
                    return table.getFunction(this.val).evaluate(table);
                } else {
                    throw new IllegalArgumentException(this.val + " is not in symbol table");
                }
            } else if (this.type.equals(Lexer.OPERATOR)) {
                double lhs = left.eval(table);
                double rhs = right.eval(table);
                switch (this.val) {
                    case "+":
                        return lhs + rhs;
                    case "-":
                        return lhs - rhs;
                    case "/":
                        return lhs / rhs;
                    case "*":
                        return lhs * rhs;
                    default:
                        System.out.println("Unknown Operator");
                        return 0.0;
                }
            } else {
                throw new IllegalArgumentException("Evaluation error " + this.toString());
            }
        }

        /**
         * override equals method to do assert equals
         * @param other object
         * @return true if the trees are equal
         */
        @Override
        public boolean equals(Object other){
            if (other instanceof Node){
                Node object = (Node) other;
                boolean flag_left = true;
                boolean flag_right = true;
                if (this.left == null) {
                    if (object.left != null) {
                        flag_left = false;
                    }
                } else {
                    flag_left = this.left.equals(object.left);
                }
                if (this.right == null) {
                    if (object.right != null) {
                        flag_right = false;
                    }
                } else {
                    flag_right = this.right.equals(object.right);
                }
                return this.type.equals(object.type) && this.val.equals(object.val) && flag_left && flag_right;
            }  else {
                return false;
            }
        }
    }

    /**
     * parse variable identifiers
     * @param t token
     * @return new node for the token
     */
    public static Node parseIdentifier(Token t) {
        if (t == null){
            return new Node();
        } else if (t.type.equals(Lexer.IDENTIFER)) {
            return new Node(t);
        } else {
            throw new IllegalArgumentException("Parse error: " + t);
        }
    }

    /**
     * parse =
     * @param t token
     * @return new node for the token
     */
    public static Node parseAssignmentOp(Token t) {
        if (t == null){
            return new Node();
        } else if (t.type.equals(Lexer.ASSIGNMENT)) {
            return new Node(t);
        } else {
            throw new IllegalArgumentException("Parse error: " + t);
        }
    }

    /**
     * parse +,-,*,/
     * @param t token
     * @return new node for the token
     */
    public static Node parseOperator(Token t) {
        if (t == null){
            return new Node();
        } else if (t.type.equals(Lexer.OPERATOR)) {
            return new Node(t);
        } else {
            throw new IllegalArgumentException("Parse error: " + t);
        }
    }

    /**
     * parse the expr (#) operator
     * @param t token
     * @return new node for the token
     */
    public static Node parseExprOperator(Token t) {
        if (t == null){
            return new Node();
        } else if (t.type.equals(Lexer.EXPRASSIGNMENT)) {
            return new Node(t);
        } else {
            throw new IllegalArgumentException("Parse error: " + t);
        }
    }

    /**
     * parse a number return the appropriate expression tree
     * @param t Token
     * @return new node for the token
     */
    public static Node parseNumber(Token t) {
        if (t == null){
            return new Node();
        } else if (t.type.equals(Lexer.FLOAT) || t.type.equals(Lexer.INT)){
            return new Node(t);
        } else {
            throw new IllegalArgumentException("Parse error: " + t);
        }
    }

    /**
     * parse a number or identifier and return the appropriate expression tree
     * @param t Token
     * @return new node for the token
     */
    public static Node parseNumberOrIdentifier(Token t) {
        if (t == null){
            return new Node();
        } else if (t.type.equals(Lexer.FLOAT) || t.type.equals(Lexer.INT) || t.type.equals(Lexer.IDENTIFER)) {
            return new Node(t);
        } else {
            throw new IllegalArgumentException("Parse error: " + t);
        }
    }

    /**
     * a helper method to tell which of two operators has precedence
     * @param op1 String operation 1
     * @param op2 String operation 2
     * @return true if operation 1 > operation 2
     */
    public static boolean hasPrecedence(String op1, String op2) {
        return precedence.get(op1) >= precedence.get(op2);
    }

    /**
     * parseExpression. Use the shunting algorithm to parse the list of tokens into an expression tree.
     * @param tokenList list of token
     * @return expression tree
     */
    public static Node parseExpression(List<Token> tokenList) {
        Stack<Node> operators = new Stack<>();
        Stack<Node> operands = new Stack<>();
        for(Token t : tokenList) {
            if (t.type.equals(Lexer.FLOAT) || t.type.equals(Lexer.INT) || t.type.equals(Lexer.IDENTIFER)) {
                operands.push(parseNumberOrIdentifier(t));
            } else if (t.type.equals(Lexer.OPERATOR)){
                if (operators.isEmpty() || ExpressionTree.hasPrecedence(t.value, operators.peek().val)) {
                    operators.push(parseOperator(t));
                } else {
                    while (!operators.isEmpty()) {
                        Node leftOperand = operands.pop();
                        Node rightOperand = operands.pop();
                        Node treeOperator = operators.pop();
                        treeOperator.left = leftOperand;
                        treeOperator.right = rightOperand;
                        operands.push(treeOperator);
                        operators.push(parseOperator(t));
                    }
                }
            }
        }
        while (!operators.isEmpty()) {
            Node operator = operators.pop();
            Node rightOperand = operands.pop();
            operator.left = operands.pop();
            operator.right = rightOperand;
            operands.push(operator);
        }
        return operands.peek();
    }

    /**
     * parse an assignment statement - grab the variable and assignment operator, parse the expression on the right-hand side,
     * evaluate it, and store the result in the symbol table.
     * @param tokenList list of token
     * @param table symbol table
     * @return node result of the parsing
     */
    public static Node parseAssignment(List<Token> tokenList, SymbolTable table) {
        Node n;
        parseIdentifier(tokenList.get(0));
        parseAssignmentOp(tokenList.get(1));
        n = parseExpression(tokenList.subList(2, tokenList.size()));
        Double result = n.eval(table);
        table.storeValue(tokenList.get(0).value, result);
        return n;
    }

    /**
     * Similar to parseAssignment, except that we're not going to evaluate the expression. Instead, store the expression tree
     * in the symbol table.
     * @param tokenList list of token
     * @param table symbol table
     * @return node result of the parsing
     */
    public static Node parseExprAssignment(List<Token> tokenList, SymbolTable table) {
        Node n;
        parseIdentifier(tokenList.get(0));
        parseExprOperator(tokenList.get(1));
        n = parseExpression(tokenList.subList(2, tokenList.size()));
        ExpressionTree et = new ExpressionTree();
        et.root = n;
        table.storeFunction(tokenList.get(0).value, et);
        return n;
    }

    /**
     * take a list of tokens, look ahead to see what we are parsing, and call the appropriate method
     * @param tokenList list of token
     * @param table symbol table
     * @return node n
     */
    public static Node parseTokens(List<Token> tokenList, SymbolTable table) {
        Node n;
        if (tokenList.size() == 1) {
            if (tokenList.get(0).type.equals(Lexer.INT) || tokenList.get(0).type.equals(Lexer.FLOAT) ||
                    tokenList.get(0).type.equals(Lexer.IDENTIFER)) {
                n = parseNumberOrIdentifier(tokenList.get(0));
            } else {
                throw new IllegalArgumentException("Unexpected token");
            }
        } else if (tokenList.get(0).type.equals(Lexer.IDENTIFER) && tokenList.get(1).type.equals(Lexer.ASSIGNMENT)) {
            n = parseAssignment(tokenList, table);
        } else if (tokenList.get(0).type.equals(Lexer.IDENTIFER) && tokenList.get(1).type.equals(Lexer.EXPRASSIGNMENT)){
            n = parseExprAssignment(tokenList, table);
        } else {
            n = parseExpression(tokenList);
        }
        if(n == null){
            System.out.println("Parse error");
        }
        return n;
    }

    /**
     * wrapper method for parseTokens
     */
    public void parse(List<Token> tokenList, SymbolTable table) {
        root = parseTokens(tokenList, table);
    }

    /**
     * wrapper method for eval
     */
    public double evaluate(SymbolTable table) {
        return root.eval(table);
    }

    /**
     * override toString method
     * @return root
     */
    @Override
    public String toString() {
        return "\nroot: " + root ;
    }
}
