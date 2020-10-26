package me.techchrism.resourcepackapi.pack.model;

public class ModelElementRotation
{
    private float[] origin;
    private Character axis;
    private Float angle;
    private Boolean rescale;
    
    public float[] getOrigin()
    {
        return origin;
    }
    
    public void setOrigin(float[] origin)
    {
        this.origin = origin;
    }
    
    public Character getAxis()
    {
        return axis;
    }
    
    public void setAxis(Character axis)
    {
        this.axis = axis;
    }
    
    public Float getAngle()
    {
        return angle;
    }
    
    public void setAngle(Float angle)
    {
        this.angle = angle;
    }
    
    public Boolean getRescale()
    {
        return rescale;
    }
    
    public void setRescale(Boolean rescale)
    {
        this.rescale = rescale;
    }
}
