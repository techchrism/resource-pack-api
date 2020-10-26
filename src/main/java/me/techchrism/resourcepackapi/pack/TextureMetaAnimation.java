package me.techchrism.resourcepackapi.pack;

import java.util.List;

public class TextureMetaAnimation
{
    private Boolean interpolate;
    private Integer frametime;
    private List<Integer> frames;
    
    public Boolean getInterpolate()
    {
        return interpolate;
    }
    
    public void setInterpolate(Boolean interpolate)
    {
        this.interpolate = interpolate;
    }
    
    public Integer getFrametime()
    {
        return frametime;
    }
    
    public void setFrametime(Integer frametime)
    {
        this.frametime = frametime;
    }
    
    public List<Integer> getFrames()
    {
        return frames;
    }
    
    public void setFrames(List<Integer> frames)
    {
        this.frames = frames;
    }
}
