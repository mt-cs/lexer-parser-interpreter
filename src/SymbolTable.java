import java.util.Map;

public class SymbolTable {

    Map<String, Double> symTable;
    Map<String, ExpressionTree> functionTable;

    /* you add constructors and setters/getters. */

   public boolean has(String key) { return symTable.containsKey(key);}

   public Double getValue (String key){ return symTable.get(key);}

   public void storeValue(String key, Double value) { symTable.put(key, value);}

   public boolean hasFunction(String key) { return functionTable.containsKey(key);}

   public ExpressionTree getFunction (String key){ return functionTable.get(key);}

   public void storeFunction(String key, ExpressionTree value) { functionTable.put(key, value);}

}
