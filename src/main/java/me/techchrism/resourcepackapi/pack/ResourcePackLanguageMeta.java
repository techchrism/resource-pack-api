package me.techchrism.resourcepackapi.pack;

public class ResourcePackLanguageMeta
{
    private String region;
    private String name;
    private boolean bidirectional;
    
    public ResourcePackLanguageMeta(String region, String name, boolean bidirectional)
    {
        this.region = region;
        this.name = name;
        this.bidirectional = bidirectional;
    }
    
    public String getRegion()
    {
        return region;
    }
    
    public void setRegion(String region)
    {
        this.region = region;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public boolean isBidirectional()
    {
        return bidirectional;
    }
    
    public void setBidirectional(boolean bidirectional)
    {
        this.bidirectional = bidirectional;
    }
}
