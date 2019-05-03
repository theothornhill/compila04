import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.LinkedList;

public class SymbolTable {
    
    public Map<String, LinkedList<Object>> table;
    public int key;
        
    public SymbolTable(int key) {
        this.key = key;
        this.table = new HashMap<String, LinkedList<Object>>();
        this.table.put("" + this.key, new LinkedList<Object>());
    }

    public int getKey() {
        return this.key;
    }

    public void incrementKey() {
        this.key++;
    }

    public void decrementKey() {
        this.key--;
    }

    public void addTable() {
        // must be used after key as been incremented
        table.put("" + this.key, new LinkedList<Object>());
    }

    public void insert(Object val) {
        this.table.get("" + this.key).add(val);
    }

    public LinkedList lookup(String key) {
         return this.table.get(key);
    }

    public String toString() {
        return Arrays.asList(this.table).toString();
        // return Arrays.toString(table.entrySet().toArray());
    }
        
}
