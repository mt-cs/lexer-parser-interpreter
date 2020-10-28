import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class LexerTest {
    public Lexer luthor;
    Token t;

    @BeforeEach
    void setUp(){
        luthor = new Lexer();
    }

    @Test
    void getIdentifier() {
        luthor.buffer = "X+aaa123+0a1";
        t = luthor.getIdentifier(2);
        System.out.println(t);
    }

    @Test
    void getNumber() {
        luthor.buffer = "567 + ";
        t = luthor.getNumber(0);
        System.out.println(t);
    }

    @Test
    void getOperator() {
        luthor.buffer = "123+456";
        t = luthor.getOperator(3);
        System.out.println(t);
    }

    @Test
    void getNextToken() {
        luthor.buffer = "X + 55 - a123 + 6.00\n";
        t = luthor.getNextToken(1);
        System.out.println(t);
    }

    @Test
    void getInputFromFile(){
        String filename = "test.txt";
        luthor.getInputFromFile(filename);
        System.out.println(luthor.buffer);
    }

    @Test
    void getExpressionAssignment(){
        luthor.buffer = "X # 55";
        t = luthor.getExpressionAssignment(2);
        System.out.println(t);
    }

    @Test
    void getEOF(){
        luthor.buffer = "X # 55\n";
        t = luthor.getEOF(6);
        System.out.println(t);
    }

    @Test
    void getAssignment(){
        luthor.buffer = "X = 55";
        t = luthor.getAssignment(2);
        System.out.println(t);
    }

    @Test
    void getInputFromString() {
        String x = "X + 55 - a123 + 6.00\n";
        luthor.getInputFromString(x);
        assertEquals(luthor.buffer, x);
    }

    @Test
    void getAllTokens(){
        luthor.buffer = "X + 55 - a123 + 6.00\n";
        System.out.println(luthor.getAllTokens());
    }
}