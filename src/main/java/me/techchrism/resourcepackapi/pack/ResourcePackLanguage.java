package me.techchrism.resourcepackapi.pack;

import java.util.HashMap;
import java.util.Map;

public class ResourcePackLanguage
{
    private String id;
    private Map<String, String> elements = new HashMap<>();
    
    public ResourcePackLanguage(String id)
    {
        this.id = id;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public Map<String, String> getElements()
    {
        return elements;
    }
    
    public void setElements(Map<String, String> elements)
    {
        this.elements = elements;
    }
}
