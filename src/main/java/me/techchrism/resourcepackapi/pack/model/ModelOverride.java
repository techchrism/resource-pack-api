package me.techchrism.resourcepackapi.pack.model;

public class ModelOverride
{
    private ModelOverridePredicate predicate;
    private String model;
    
    public ModelOverridePredicate getPredicate()
    {
        return predicate;
    }
    
    public void setPredicate(ModelOverridePredicate predicate)
    {
        this.predicate = predicate;
    }
    
    public String getModel()
    {
        return model;
    }
    
    public void setModel(String model)
    {
        this.model = model;
    }
}
