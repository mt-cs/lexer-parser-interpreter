import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTreeTest {
    ExpressionTree et;

    @BeforeEach
    void setUp(){
        et = new ExpressionTree();
    }

    @Test
    void parseIdentifier() {
        Token t1 = new Token(Lexer.IDENTIFER, "a123");
        ExpressionTree.Node n = ExpressionTree.parseIdentifier(t1);
        System.out.println(n);
    }

    @Test
    void parseAssignmentOp() {
        Token t1 = new Token(Lexer.ASSIGNMENT, "=");
        ExpressionTree.Node n = ExpressionTree.parseAssignmentOp(t1);
        System.out.println(n);
    }

    @Test
    void parseOperator() {
        Token t1 = new Token(Lexer.OPERATOR, "+");
        ExpressionTree.Node n = ExpressionTree.parseOperator(t1);
        System.out.println(n);
    }

    @Test
    void parseExprOperator() {
        Token t1 = new Token(Lexer.EXPRASSIGNMENT, "#");
        ExpressionTree.Node n = ExpressionTree.parseExprOperator(t1);
        System.out.println(n);
    }

    @Test
    void parseNumber() {
        Token t1 = new Token(Lexer.INT, "31");
        ExpressionTree.Node n = ExpressionTree.parseNumber(t1);
        System.out.println(n);
    }

    @Test
    void parseNumberOrIdentifier() {
        Token t1 = new Token(Lexer.FLOAT, "333.4");
        ExpressionTree.Node n = ExpressionTree.parseNumberOrIdentifier(t1);
        System.out.println(n);
    }

    @Test
    void hasPrecedence() {
    }

    @Test
    void parseExpression() {
        List<Token> tokenList = new ArrayList<>();
        // x + 55.00 * abc6 / 8.0 - 10 - 7.00
        tokenList.add(new Token(Lexer.IDENTIFER, "X"));
        tokenList.add(new Token(Lexer.OPERATOR, "+"));
        tokenList.add(new Token(Lexer.FLOAT, "55.00"));

        ExpressionTree.Node result = ExpressionTree.parseExpression(tokenList);
        System.out.println(result);
        ExpressionTree.Node expectedResult = new ExpressionTree.Node(Lexer.OPERATOR, "+", new ExpressionTree.Node(Lexer.IDENTIFER, "X", null, null), new ExpressionTree.Node(Lexer.FLOAT, "55.00", null, null));
        System.out.println(expectedResult);
        assertEquals(result, expectedResult);
    }

    @Test
    void parseAssignment() {
    }

    @Test
    void parseExprAssignment() {
    }

    @Test
    void parseTokens() {

    }

    @Test
    void parse() {
        List<Token> tl = new LinkedList<>();
        Token t1 = new Token(Lexer.IDENTIFER, "a123");
        Token t2 = new Token(Lexer.ASSIGNMENT, "=");
        Token t3 = new Token(Lexer.INT, "1234");
        Token t4 = new Token(Lexer.OPERATOR, "-");
        Token t5 = new Token(Lexer.FLOAT, "345.666");
        SymbolTable table = new SymbolTable();
        tl.add(t1);
        tl.add(t2);
        tl.add(t3);
        tl.add(t4);
        tl.add(t5);
        ExpressionTree.Node n = ExpressionTree.parseTokens(tl, table);
    }

    @Test
    void evaluate() {
        List<Token> tl = new LinkedList<>();
        Token t1 = new Token(Lexer.INT,"3");
        Token t2 = new Token(Lexer.OPERATOR,"*");
        Token t3 = new Token(Lexer.FLOAT,"5");
        Token t4 = new Token(Lexer.INT,"+");
        Token t5 = new Token(Lexer.INT,"a123");
        SymbolTable table = new SymbolTable();
        tl.add(t1);
        tl.add(t2);
        tl.add(t3);
        tl.add(t4);
        tl.add(t5);
        ExpressionTree t = new ExpressionTree();
        t.parse(tl, table);
        System.out.println(t.evaluate(table));
    }
}