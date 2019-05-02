import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.LinkedList;

public class SymbolTable {

    public Map<String, LinkedList<Object>> table;

    public SymbolTable(int scopeLevel) {
        this.table = new HashMap<String, LinkedList<Object>>();
        this.table.put("" + scopeLevel, new LinkedList<Object>());
    }

    public String toString() {
        return Arrays.asList(this.table).toString();
        // return Arrays.toString(table.entrySet().toArray());
    }

    public void insert(String key, Object val) {
        this.table.get(key).add(val);
    }

    public LinkedList lookup(String key) {
         return this.table.get(key);
    }
        
}
