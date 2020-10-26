package me.techchrism.resourcepackapi;

import me.techchrism.resourcepackapi.pack.ResourcePack;

public class PendingResourcePack
{
    private static final long EXPIRE_TIME = (1000 * 60);
    private final long queuedTime;
    private final ResourcePack pack;
    
    public PendingResourcePack(ResourcePack pack)
    {
        this.pack = pack;
        this.queuedTime = System.currentTimeMillis();
    }
    
    public boolean isExpired()
    {
        return (this.queuedTime + EXPIRE_TIME < System.currentTimeMillis());
    }
    
    public ResourcePack getPack()
    {
        return this.pack;
    }
}
