import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.LinkedList;

public class SymbolTable {
    
    public Map<String, Object> table;
        
    public SymbolTable() {
        this.table = new HashMap<String, Object>();
    }

    public void insert(Object val) throws Exception {
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

        currentScope = ((Decl)scope).getTable();

        return currentScope.table.containsKey(key)
            ? currentScope.table.get(key)
            : lookup(((Decl)scope).getCreatedBy(), key);
    }

    public String toString() {
        return Arrays.asList(this.table).toString();
    }
        
}
