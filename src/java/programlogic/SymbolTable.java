import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.LinkedList;

public class SymbolTable {
    
    public Map<String, Object> table;
    public int key;
        
    public SymbolTable(int key) {
        this.key = key;
        this.table = new HashMap<String, Object>();
        // this.table.put("" + this.key, new LinkedList<Object>());
        // this.table.put("" + this.key, ));
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
        if (!table.containsKey("" + key)) 
            // table.put("" + key, new LinkedList<Object>());
            table.put("" + key, new HashMap<String, Object>());
        else
            table.put("" + key + "a", new LinkedList<Object>());
    }

    public void insert(Object val) {
        // this.table.get("" + this.key).add(val);
        this.table.put(val.toString(), val); 
    }

    public Object lookup(String key) {
        if (table.containsKey(key))
            return table.get(key);
        else if (!table.containsKey(key))
            return null;
        return null;
    }

    public String toString() {
        return Arrays.asList(this.table).toString();
        // return Arrays.toString(table.entrySet().toArray());
    }
        
}
