package me.techchrism.resourcepackapi.pack;

import me.techchrism.resourcepackapi.pack.model.ModelOverride;

import java.util.ArrayList;
import java.util.List;

public class ItemModel extends BlockModel
{
    private List<ModelOverride> overrides;
    
    public ItemModel()
    {
        super();
        overrides = new ArrayList<>();
    }
    
    public List<ModelOverride> getOverrides()
    {
        return overrides;
    }
    
    public void setOverrides(List<ModelOverride> overrides)
    {
        this.overrides = overrides;
    }
}
