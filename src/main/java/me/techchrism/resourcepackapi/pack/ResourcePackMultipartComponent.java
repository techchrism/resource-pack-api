package me.techchrism.resourcepackapi.pack;

public class ResourcePackMultipartComponent
{
    private ResourcePackModel apply;
    private MultipartConditions when;
    
    public ResourcePackMultipartComponent(ResourcePackModel apply, MultipartConditions when)
    {
        this.apply = apply;
        this.when = when;
    }
    
    public ResourcePackModel getApply()
    {
        return apply;
    }
    
    public void setApply(ResourcePackModel apply)
    {
        this.apply = apply;
    }
    
    public MultipartConditions getWhen()
    {
        return when;
    }
    
    public void setWhen(MultipartConditions when)
    {
        this.when = when;
    }
}
