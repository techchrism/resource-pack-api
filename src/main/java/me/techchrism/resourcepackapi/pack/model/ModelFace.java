package me.techchrism.resourcepackapi.pack.model;

import com.google.gson.annotations.SerializedName;

public class ModelFace
{
    private float[] uv;
    private String texture;
    @SerializedName("cullface")
    private String cullFace;
    private Integer rotation;
    @SerializedName("tintindex")
    private Integer tintIndex;
    
    public float[] getUv()
    {
        return uv;
    }
    
    public void setUv(float[] uv)
    {
        this.uv = uv;
    }
    
    public String getTexture()
    {
        return texture;
    }
    
    public void setTexture(String texture)
    {
        this.texture = texture;
    }
    
    public String getCullFace()
    {
        return cullFace;
    }
    
    public void setCullFace(String cullFace)
    {
        this.cullFace = cullFace;
    }
    
    public Integer getRotation()
    {
        return rotation;
    }
    
    public void setRotation(Integer rotation)
    {
        this.rotation = rotation;
    }
    
    public Integer getTintIndex()
    {
        return tintIndex;
    }
    
    public void setTintIndex(Integer tintIndex)
    {
        this.tintIndex = tintIndex;
    }
}
