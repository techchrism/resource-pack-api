package me.techchrism.resourcepackapi.pack;

public class ResourcePackModel
{
    private String model;
    private Integer x;
    private Integer y;
    private Boolean uvlock;
    private Integer weight;
    
    public ResourcePackModel(String model, Integer x, Integer y, Boolean uvlock, Integer weight)
    {
        this.model = model;
        this.x = x;
        this.y = y;
        this.uvlock = uvlock;
        this.weight = weight;
    }
    
    public String getModel()
    {
        return model;
    }
    
    public void setModel(String model)
    {
        this.model = model;
    }
    
    public Integer getX()
    {
        return x;
    }
    
    public void setX(Integer x)
    {
        this.x = x;
    }
    
    public Integer getY()
    {
        return y;
    }
    
    public void setY(Integer y)
    {
        this.y = y;
    }
    
    public Boolean getUvlock()
    {
        return uvlock;
    }
    
    public void setUvlock(Boolean uvlock)
    {
        this.uvlock = uvlock;
    }
    
    public Integer getWeight()
    {
        return weight;
    }
    
    public void setWeight(Integer weight)
    {
        this.weight = weight;
    }
}
