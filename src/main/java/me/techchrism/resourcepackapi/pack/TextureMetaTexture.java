package me.techchrism.resourcepackapi.pack;

import java.util.List;

public class TextureMetaTexture
{
    private Boolean blur;
    private Boolean clamp;
    private List<Integer> mipmaps;
    
    public TextureMetaTexture(Boolean blur, Boolean clamp)
    {
        this.blur = blur;
        this.clamp = clamp;
    }
    
    public Boolean isBlur()
    {
        return blur;
    }
    
    public void setBlur(Boolean blur)
    {
        this.blur = blur;
    }
    
    public Boolean isClamp()
    {
        return clamp;
    }
    
    public void setClamp(Boolean clamp)
    {
        this.clamp = clamp;
    }
    
    public List<Integer> getMipmaps()
    {
        return mipmaps;
    }
    
    public void setMipmaps(List<Integer> mipmaps)
    {
        this.mipmaps = mipmaps;
    }
}
