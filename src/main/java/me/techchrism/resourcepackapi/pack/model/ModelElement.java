package me.techchrism.resourcepackapi.pack.model;

import com.google.gson.annotations.SerializedName;

public class ModelElement
{
    @SerializedName("__comment")
    private String comment;
    private float[] from;
    private float[] to;
    private ModelElementRotation rotation;
    private Boolean shade;
    private ModelFaces faces;
    
    public String getComment()
    {
        return comment;
    }
    
    public void setComment(String comment)
    {
        this.comment = comment;
    }
    
    public float[] getFrom()
    {
        return from;
    }
    
    public void setFrom(float[] from)
    {
        this.from = from;
    }
    
    public float[] getTo()
    {
        return to;
    }
    
    public void setTo(float[] to)
    {
        this.to = to;
    }
    
    public ModelElementRotation getRotation()
    {
        return rotation;
    }
    
    public void setRotation(ModelElementRotation rotation)
    {
        this.rotation = rotation;
    }
    
    public Boolean isShade()
    {
        return shade;
    }
    
    public void setShade(Boolean shade)
    {
        this.shade = shade;
    }
    
    public ModelFaces getFaces()
    {
        return faces;
    }
    
    public void setFaces(ModelFaces faces)
    {
        this.faces = faces;
    }
}
