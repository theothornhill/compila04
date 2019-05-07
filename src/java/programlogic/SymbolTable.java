import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.LinkedList;

public class SymbolTable {
    
    public Map<String, Object> table;
    public int key;
        
    public SymbolTable() {
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

    public void insert(Object val) throws Exception {
        // this.table.get("" + this.key).add(val);
        if (this.table.containsKey(val.toString()))
            throw new Exception("Double declaration of symbol " + val);
        this.table.put(val.toString(), val); 
    }

    public Object lookup(Object scope, String key) {
        SymbolTable currentScope;
        if (scope instanceof Program) {
            currentScope = ((Program)scope).getTable();
            return currentScope.table.containsKey(key)
                ? currentScope.table.get(key)
                : null;
        }
        // if (scope instanceof Stmt)
        //     currentScope = ((Stmt)scope).getTable();
        // if (scope instanceof Decl)
        currentScope = ((Decl)scope).getTable();

        return currentScope.table.containsKey(key)
            ? currentScope.table.get(key)
            : lookup(((Decl)scope).getCreatedBy(), key);
    }

    public String toString() {
        return Arrays.asList(this.table).toString();
        // return Arrays.toString(table.entrySet().toArray());
    }
        
}
