package me.techchrism.resourcepackapi.pack.model;

public class ModelFaces
{
    private ModelFace up;
    private ModelFace down;
    private ModelFace north;
    private ModelFace south;
    private ModelFace east;
    private ModelFace west;
    
    public ModelFace getUp()
    {
        return up;
    }
    
    public void setUp(ModelFace up)
    {
        this.up = up;
    }
    
    public ModelFace getDown()
    {
        return down;
    }
    
    public void setDown(ModelFace down)
    {
        this.down = down;
    }
    
    public ModelFace getNorth()
    {
        return north;
    }
    
    public void setNorth(ModelFace north)
    {
        this.north = north;
    }
    
    public ModelFace getSouth()
    {
        return south;
    }
    
    public void setSouth(ModelFace south)
    {
        this.south = south;
    }
    
    public ModelFace getEast()
    {
        return east;
    }
    
    public void setEast(ModelFace east)
    {
        this.east = east;
    }
    
    public ModelFace getWest()
    {
        return west;
    }
    
    public void setWest(ModelFace west)
    {
        this.west = west;
    }
}
