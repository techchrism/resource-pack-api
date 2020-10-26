package me.techchrism.resourcepackapi;

import me.techchrism.resourcepackapi.network.ResourcePackSender;
import me.techchrism.resourcepackapi.pack.ResourcePack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ResourcePackAPI
{
    private final RegistrationIDStore registrationIDStore;
    private final ResourcePackSender resourcePackSender;
    
    protected ResourcePackAPI(RegistrationIDStore registrationIDStore, ResourcePackSender resourcePackSender)
    {
        this.registrationIDStore = registrationIDStore;
        this.resourcePackSender = resourcePackSender;
    }
    
    /**
     * Sends a ResourcePackCompileEvent for the specified player and, if not cancelled, sends the pack to the player
     *
     * @param player The player to compile the resource pack for
     */
    public void recompilePack(Player player)
    {
        ResourcePack pack = getDefaultPack();
        ResourcePackCompileEvent event = new ResourcePackCompileEvent(pack, player);
        Bukkit.getPluginManager().callEvent(event);
        
        if(!event.isCancelled())
        {
            try
            {
                resourcePackSender.sendPackToPlayer(player, pack);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Gets the default pack base used.
     *
     * @return the default pack
     */
    public ResourcePack getDefaultPack()
    {
        ResourcePack defaultPack = new ResourcePack();
        defaultPack.setPackFormat(4);
        return defaultPack;
    }
    
    /**
     * Get the registration id store
     *
     * @return the registration id store
     */
    public RegistrationIDStore getRegistrationIDStore()
    {
        return registrationIDStore;
    }
    
    /**
     * Get the port the server is running on
     *
     * @return the server port
     */
    public int getServerPort()
    {
        return Bukkit.getPort();
    }
    
    public String getPackUrl()
    {
        return resourcePackSender.getURL();
    }
    
    public void setPackUrl(String packUrl)
    {
        resourcePackSender.setURL(packUrl);
    }
}
