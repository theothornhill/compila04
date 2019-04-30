import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class SymbolTable {

    public Map<String, Object> table;

    public SymbolTable() {
        table = new HashMap<String, Object>();
    }

    public String toString() {
        // return Arrays.asList(table).toString();
        return Arrays.toString(table.entrySet().toArray());
    }

    public void insert(String key, Object val) {
        table.put(key, val);
    }

    public Object lookup(String key) {
         return table.get(key);
    }
        
}
