package me.techchrism.resourcepackapi.pack.model;

import com.google.gson.annotations.SerializedName;

public class ModelOverridePredicate
{
    private Float angle;
    private Integer blocking;
    private Integer broken;
    private Integer cast;
    private Float cooldown;
    private Float damage;
    private Integer damaged;
    private Integer lefthanded;
    private Float pull;
    private Integer pulling;
    private Integer throwing;
    private Float time;
    @SerializedName("custom_model_data")
    private Integer customModelData;
    
    public Float getAngle()
    {
        return angle;
    }
    
    public void setAngle(Float angle)
    {
        this.angle = angle;
    }
    
    public Integer getBlocking()
    {
        return blocking;
    }
    
    public void setBlocking(Integer blocking)
    {
        this.blocking = blocking;
    }
    
    public Integer getBroken()
    {
        return broken;
    }
    
    public void setBroken(Integer broken)
    {
        this.broken = broken;
    }
    
    public Integer getCast()
    {
        return cast;
    }
    
    public void setCast(Integer cast)
    {
        this.cast = cast;
    }
    
    public Float getCooldown()
    {
        return cooldown;
    }
    
    public void setCooldown(Float cooldown)
    {
        this.cooldown = cooldown;
    }
    
    public Float getDamage()
    {
        return damage;
    }
    
    public void setDamage(Float damage)
    {
        this.damage = damage;
    }
    
    public Integer getDamaged()
    {
        return damaged;
    }
    
    public void setDamaged(Integer damaged)
    {
        this.damaged = damaged;
    }
    
    public Integer getLefthanded()
    {
        return lefthanded;
    }
    
    public void setLefthanded(Integer lefthanded)
    {
        this.lefthanded = lefthanded;
    }
    
    public Float getPull()
    {
        return pull;
    }
    
    public void setPull(Float pull)
    {
        this.pull = pull;
    }
    
    public Integer getPulling()
    {
        return pulling;
    }
    
    public void setPulling(Integer pulling)
    {
        this.pulling = pulling;
    }
    
    public Integer getThrowing()
    {
        return throwing;
    }
    
    public void setThrowing(Integer throwing)
    {
        this.throwing = throwing;
    }
    
    public Float getTime()
    {
        return time;
    }
    
    public void setTime(Float time)
    {
        this.time = time;
    }
    
    public Integer getCustomModelData()
    {
        return customModelData;
    }
    
    public void setCustomModelData(Integer customModelData)
    {
        this.customModelData = customModelData;
    }
}
