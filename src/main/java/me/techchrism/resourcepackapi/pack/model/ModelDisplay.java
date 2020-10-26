package me.techchrism.resourcepackapi.pack.model;

public class ModelDisplay
{
    private float[] rotation;
    private float[] translation;
    private float[] scale;
    
    public float[] getRotation()
    {
        return rotation;
    }
    
    public void setRotation(float[] rotation)
    {
        this.rotation = rotation;
    }
    
    public float[] getTranslation()
    {
        return translation;
    }
    
    public void setTranslation(float[] translation)
    {
        this.translation = translation;
    }
    
    public float[] getScale()
    {
        return scale;
    }
    
    public void setScale(float[] scale)
    {
        this.scale = scale;
    }
}
