package me.techchrism.resourcepackapi;

import me.techchrism.resourcepackapi.pack.ResourcePack;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ResourcePackCompileEvent extends Event implements Cancellable
{
    private ResourcePack resourcePack;
    private static final HandlerList HANDLERS = new HandlerList();
    private boolean cancelled = false;
    private Player player;
    
    public ResourcePackCompileEvent(ResourcePack resourcePack, Player player)
    {
        this.resourcePack = resourcePack;
        this.player = player;
    }
    
    public HandlerList getHandlers()
    {
        return HANDLERS;
    }
    
    public static HandlerList getHandlerList()
    {
        return HANDLERS;
    }
    
    @Override
    public boolean isCancelled()
    {
        return cancelled;
    }
    
    @Override
    public void setCancelled(boolean cancelled)
    {
        this.cancelled = cancelled;
    }
    
    public ResourcePack getResourcePack()
    {
        return resourcePack;
    }
    
    public Player getPlayer()
    {
        return player;
    }
}
