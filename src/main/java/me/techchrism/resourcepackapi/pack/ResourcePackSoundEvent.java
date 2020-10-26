package me.techchrism.resourcepackapi.pack;

import java.util.ArrayList;
import java.util.List;

public class ResourcePackSoundEvent
{
    private Boolean replace;
    private String subtitle;
    private List<ResourcePackSound> sounds;
    
    public ResourcePackSoundEvent()
    {
        sounds = new ArrayList<>();
    }
    
    public ResourcePackSoundEvent(Boolean replace, String subtitle, List<ResourcePackSound> sounds)
    {
        this.replace = replace;
        this.subtitle = subtitle;
        this.sounds = sounds;
    }
    
    public Boolean isReplace()
    {
        return replace;
    }
    
    public void setReplace(Boolean replace)
    {
        this.replace = replace;
    }
    
    public String getSubtitle()
    {
        return subtitle;
    }
    
    public void setSubtitle(String subtitle)
    {
        this.subtitle = subtitle;
    }
    
    public List<ResourcePackSound> getSounds()
    {
        return sounds;
    }
    
    public void setSounds(List<ResourcePackSound> sounds)
    {
        this.sounds = sounds;
    }
}
