package me.techchrism.resourcepackapi.pack;

import com.google.gson.annotations.SerializedName;
import me.techchrism.resourcepackapi.pack.model.ModelDisplays;
import me.techchrism.resourcepackapi.pack.model.ModelElement;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class BlockModel
{
    private String parent;
    @SerializedName("ambientocclusion")
    private Boolean ambientOcclusion;
    private ModelDisplays display;
    private Map<String, String> textures;
    private List<ModelElement> elements;
    
    public BlockModel()
    {
        textures = new HashMap<>();
        elements = new ArrayList<>();
    }
    
    public String getParent()
    {
        return parent;
    }
    
    public void setParent(String parent)
    {
        this.parent = parent;
    }
    
    public Boolean getAmbientOcclusion()
    {
        return ambientOcclusion;
    }
    
    public void setAmbientOcclusion(Boolean ambientOcclusion)
    {
        this.ambientOcclusion = ambientOcclusion;
    }
    
    public ModelDisplays getDisplay()
    {
        return display;
    }
    
    public void setDisplay(ModelDisplays display)
    {
        this.display = display;
    }
    
    public Map<String, String> getTextures()
    {
        return textures;
    }
    
    public void setTextures(Map<String, String> textures)
    {
        this.textures = textures;
    }
    
    public List<ModelElement> getElements()
    {
        return elements;
    }
    
    public void setElements(List<ModelElement> elements)
    {
        this.elements = elements;
    }
}
