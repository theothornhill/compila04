public interface AttributeGrammar {
    public Object getCreatedBy();
    public void setCreatedBy(Object node);    
    public void setCreatorOf();
    public void setLexicalScopeLevel(int scope);
    public int getLexicalScopeLevel();
}
