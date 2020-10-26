package me.techchrism.resourcepackapi.pack;

import com.google.gson.annotations.SerializedName;

public class ResourcePackSound
{
    private String name;
    private Float volume;
    private Float pitch;
    private Integer weight;
    private Boolean stream;
    @SerializedName("attenuation_distance")
    private Integer attenuationDistance;
    private Boolean preload;
    private String type;
    
    public ResourcePackSound(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Float getVolume()
    {
        return volume;
    }
    
    public void setVolume(Float volume)
    {
        this.volume = volume;
    }
    
    public Float getPitch()
    {
        return pitch;
    }
    
    public void setPitch(Float pitch)
    {
        this.pitch = pitch;
    }
    
    public Integer getWeight()
    {
        return weight;
    }
    
    public void setWeight(Integer weight)
    {
        this.weight = weight;
    }
    
    public Boolean getStream()
    {
        return stream;
    }
    
    public void setStream(Boolean stream)
    {
        this.stream = stream;
    }
    
    public Integer getAttenuationDistance()
    {
        return attenuationDistance;
    }
    
    public void setAttenuationDistance(Integer attenuationDistance)
    {
        this.attenuationDistance = attenuationDistance;
    }
    
    public Boolean getPreload()
    {
        return preload;
    }
    
    public void setPreload(Boolean preload)
    {
        this.preload = preload;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
}
