package me.techchrism.resourcepackapi.pack;

public class TextureMeta
{
    private TextureMetaAnimation animation;
    private TextureMetaVillager villager;
    private TextureMetaTexture texture;
    
    public TextureMetaAnimation getAnimation()
    {
        return animation;
    }
    
    public void setAnimation(TextureMetaAnimation animation)
    {
        this.animation = animation;
    }
    
    public TextureMetaVillager getVillager()
    {
        return villager;
    }
    
    public void setVillager(TextureMetaVillager villager)
    {
        this.villager = villager;
    }
    
    public TextureMetaTexture getTexture()
    {
        return texture;
    }
    
    public void setTexture(TextureMetaTexture texture)
    {
        this.texture = texture;
    }
}
