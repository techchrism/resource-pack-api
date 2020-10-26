package me.techchrism.resourcepackapi.pack;

import java.util.List;
import java.util.Map;

public class ResourcePackBlockState
{
    private Map<String, List<ResourcePackModel>> variants;
    private List<ResourcePackMultipartComponent> multipart;
    
    public ResourcePackBlockState(Map<String, List<ResourcePackModel>> variants, List<ResourcePackMultipartComponent> multipart)
    {
        this.variants = variants;
        this.multipart = multipart;
    }
    
    public Map<String, List<ResourcePackModel>> getVariants()
    {
        return variants;
    }
    
    public void setVariants(Map<String, List<ResourcePackModel>> variants)
    {
        this.variants = variants;
    }
    
    public List<ResourcePackMultipartComponent> getMultipart()
    {
        return multipart;
    }
    
    public void setMultipart(List<ResourcePackMultipartComponent> multipart)
    {
        this.multipart = multipart;
    }
}
