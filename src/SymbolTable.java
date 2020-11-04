import java.util.HashMap;
import java.util.Map;

/**
 *  SymTab maps the identifiers in the program to where they are stored in memory
 *  It wraps hashMaps to do its work.
 */
public class SymbolTable {
    Map<String, Double> symTable = new HashMap<>();
    Map<String, ExpressionTree> functionTable = new HashMap<>();

    /**
     * Check if symTable has key
     * @param key String
     * @return true if contains key
     */
    public boolean has(String key) { return symTable.containsKey(key);}

    /**
     * Get value on the symTable from key
     * @param key String
     * @return value
     */
    public Double getValue (String key){ return symTable.get(key);}

    /**
     * Store value to the symbol table
     * @param key String
     * @param value Double
     */
    public void storeValue(String key, Double value) { symTable.put(key, value);}

    /**
     * Check if function Table has key
     * @param key String
     * @return true if contains key
     */
    public boolean hasFunction(String key) { return functionTable.containsKey(key);}

    /**
     * Get expression on the functionTable from key
     * @param key String
     * @return expression tree
     */
    public ExpressionTree getFunction (String key){ return functionTable.get(key);}

    /**
     * Store expression tree to the function table
     * @param key String
     * @param value Expression Tree
     */
    public void storeFunction(String key, ExpressionTree value) { functionTable.put(key, value);}

    /**
     * Override toString method
     */
    @Override
    public String toString() {
        String s = "";
        if (!symTable.isEmpty()) {
            s += symTable;
        }
        if (!functionTable.isEmpty()) {
            s += "\n" + functionTable;
        }
        return s;
    }
}
