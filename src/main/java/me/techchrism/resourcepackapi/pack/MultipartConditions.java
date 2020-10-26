package me.techchrism.resourcepackapi.pack;

public class MultipartConditions
{
    private MultipartConditions OR;
    private String north;
    private String south;
    private String west;
    private String east;
    private String up;
    private String down;
    
    public MultipartConditions getOR()
    {
        return OR;
    }
    
    public void setOR(MultipartConditions OR)
    {
        this.OR = OR;
    }
    
    public String getNorth()
    {
        return north;
    }
    
    public void setNorth(String north)
    {
        this.north = north;
    }
    
    public String getSouth()
    {
        return south;
    }
    
    public void setSouth(String south)
    {
        this.south = south;
    }
    
    public String getWest()
    {
        return west;
    }
    
    public void setWest(String west)
    {
        this.west = west;
    }
    
    public String getEast()
    {
        return east;
    }
    
    public void setEast(String east)
    {
        this.east = east;
    }
    
    public String getUp()
    {
        return up;
    }
    
    public void setUp(String up)
    {
        this.up = up;
    }
    
    public String getDown()
    {
        return down;
    }
    
    public void setDown(String down)
    {
        this.down = down;
    }
}
