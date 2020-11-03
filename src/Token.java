/**
 * class Token packages the type and value of each token in a string of text
 */

public class Token {
    String type;
    String value;

    /**
     * empty constructor
     */
    public Token() {
        type = "";
        value = "";
    }

    /**
     * constructor with parameters:
     * @param t type
     * @param v value
     */
    public Token(String t, String v) {
        this.type = t;
        this.value = v;
    }

    /**
     * @return value length
     */
    public int length() {
        return value.length();
    }

    /**
     * override toString method
     * @return type and value
     */
    @Override
    public String toString() {
        return this.type + " " + this.value;
    }
}
