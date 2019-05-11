import bytecode.*;
import bytecode.type.*;
import bytecode.instructions.*;

public class Assign extends Stmt {
    public Assign(Object name, Object e) {
        this.target = name;
        this.e = e;
    }

    public void typeCheck(SymbolTable table, Object scope) throws Exception {
        Var targetVar = (Var)target;
        Object assignTarget = null;
        if (targetVar.expr != null)
            assignTarget = table.lookup(scope, targetVar.expr.toString());
        else
            assignTarget = table.lookup(scope, targetVar.name.toString());

        if (assignTarget == null)
            throw new Exception("Symbol " + target + " is not declared");
        typeCheckAssign(targetVar, table, scope, assignTarget);
        
    }

    public void typeCheckAssign(Var targetVar, SymbolTable table,
                                Object scope, Object assignTarget) throws Exception {
        if (assignTarget instanceof VarDecl) {
            if (targetVar.expr == null) {
                typeCheckIfVar(table, scope, (VarDecl)assignTarget);
            }
            if (targetVar.expr != null) {
                typeCheckIfRecord(table, scope, (VarDecl)assignTarget);
            }
        } if (assignTarget instanceof Param) {

            typeCheckIfParam(table, scope, (Param)assignTarget);
        }

    }

    public void typeCheckIfParam(SymbolTable table, Object scope, Param assignTarget) throws Exception {
        if (table.lookup(scope, assignTarget.name) == null)
            throw new Exception("Param not declared");
        if (e instanceof Call) {
            if (!TypeCheckHelper.isLibraryProcedure(e.toString())) {
                ((Call)e).typeCheck(table, scope);
                if (!((Call)e).type.equals(assignTarget.type.toString())) {
                    throw new Exception("Wrong type in assignment");                
                }
            }
        } else {
            ((Expr)e).typeCheck(table, scope);
            if (!((Expr)e).type.equals(assignTarget.type.toString())) {
                if (!TypeCheckHelper.isAssignable(assignTarget, (Expr)e)) {
                    throw new Exception("Wrong type in assignment on " + assignTarget + " and " + e); 
                } 
            }
        }    
    }

    public void typeCheckIfVar(SymbolTable table, Object scope, VarDecl assignTarget) throws Exception {
        if (table.lookup(scope, assignTarget.name) == null)
            throw new Exception("Variable not declared");
        if (e instanceof Call) {
            if (!TypeCheckHelper.isLibraryProcedure(e.toString())) {
                ((Call)e).typeCheck(table, scope);
                if (!((Call)e).type.equals(assignTarget.type.toString())) {
                    throw new Exception("Wrong type in assignment");                
                }
            }
        } else {
            ((Expr)e).typeCheck(table, scope);
            if (!((Expr)e).type.equals(assignTarget.type.toString())) {
                if (!TypeCheckHelper.isAssignable(assignTarget, (Expr)e)) {
                    throw new Exception("Wrong type in assignment on " + assignTarget + " and " + e); 
                } 
            }
        }
    }

    public void typeCheckIfRecord(SymbolTable table, Object scope, VarDecl assignTarget) throws Exception {
        RecDecl record = (RecDecl)table.lookup(scope, assignTarget.type.toString());
        Param param = null;
        if (record == null)
            throw new Exception("Record not declared");
        param = (Param)table.lookup(record, ((Var)target).name);
        if (param == null)
            throw new Exception("Attribute not declared");
        if (e instanceof BinaryExpr)
            ((BinaryExpr)e).typeCheck(table, scope);
        if (!((Expr)e).type.equals(param.type.toString())) {
            if (!TypeCheckHelper.isAssignable(param, (Expr)e)) {
                throw new Exception(""+ param + " type: " + param.type + " cannot be assigned a " + ((Expr)e).type); 
            }
        }
    }

    public Object getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Object node) {
        this.createdBy = node;
    }

    public void setCreatorOf() {
        // Does not do anything yet
    }

    public void setLexicalScopeLevel(int scope) {
        this.lexicalScopeLevel = scope;
    }

    public void addToSymbolTable(SymbolTable table) {
    }

    public void generateCode(CodeFile codeFile, SymbolTable table, Object scope) {

    }

    public void generateCode(CodeProcedure proc, SymbolTable table, Object scope) {
        // System.out.println(target.getClass());
        if (e instanceof Literal) {
            proc.addInstruction(CodeGenerationHelper.literalHelper(((Literal)e), proc));
            if (target instanceof Var) {
                if (((Var)target).expr != null) {
                    // we need to load a variable and put in record field
                    generateRecordPutField(target, proc, table, scope);
                    return;
                }                
            }
        } else if (e instanceof BinaryExpr) {
            ((BinaryExpr)e).generateCode(proc, table, scope);
            if (target instanceof Var) {
                if (((Var)target).expr != null) {
                    // we need to load a variable and put in record field
                    generateRecordPutField(target, proc, table, scope);
                    return;
                }
            }
        } else if (e instanceof Var)
            proc.addInstruction(new LOADLOCAL(proc.variableNumber(e.toString())));
        else if (e instanceof New) {
            ((New)e).generateCode(proc, table, scope);
        } else if (e instanceof Call) {
            ((Call)e).generateCode(proc, table, scope);
        }
        if (proc.variableNumber(target.toString()) == -1) {
            proc.addInstruction(new STOREGLOBAL(proc.getCodeFile().globalVariableNumber(target.toString())));
        } else {
            proc.addInstruction(new STORELOCAL(proc.variableNumber(target.toString())));        
        }

    }

    public void generateRecordPutField(Object ex, CodeProcedure proc,
                                       SymbolTable table, Object scope) {
        String varName = ((Var)ex).expr.toString();
        String fieldName = ((Var)ex).toString();
        String type = ((VarDecl)table.lookup(scope, varName)).type.toString();
        if (proc.variableNumber(varName) == -1) {
            proc.addInstruction(new LOADGLOBAL(proc.getCodeFile().globalVariableNumber(varName)));
        } else {
            proc.addInstruction(new LOADLOCAL(proc.variableNumber(varName)));            
        }
        proc.addInstruction(new PUTFIELD(proc.fieldNumber(type, fieldName),
                                         proc.structNumber(type)));        
    }

    public String printAst(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("(ASSIGN_STMT");
        sb.append(PrintHelper.newlineAndIndentWithHelper(target, indentLevel+1));
        sb.append(PrintHelper.newlineAndIndentWithHelper(e, indentLevel+1));
        sb.append(PrintHelper.endWithParen(indentLevel));
        return sb.toString();
    }

    public String toString() {
        return target.toString();
    }

}
