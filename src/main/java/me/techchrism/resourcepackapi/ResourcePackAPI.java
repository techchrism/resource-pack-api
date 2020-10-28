package me.techchrism.resourcepackapi;

import me.techchrism.resourcepackapi.network.ResourcePackSender;
import me.techchrism.resourcepackapi.network.netty.NettyResourcePackSender;
import me.techchrism.resourcepackapi.pack.ResourcePack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ResourcePackAPI extends JavaPlugin
{
    private static RegistrationIDStore registrationIDStore;
    private static ResourcePackSender resourcePackSender;
    public static File PLUGIN_FOLDER;
    public static File QUEUE_FOLDER;
    
    @Override
    public void onEnable()
    {
        // Set up directories
        PLUGIN_FOLDER = getDataFolder();
        if(!PLUGIN_FOLDER.exists())
        {
            PLUGIN_FOLDER.mkdir();
        }
        QUEUE_FOLDER = new File(PLUGIN_FOLDER, "resource-pack-sending-queue");
        if(!QUEUE_FOLDER.exists())
        {
            QUEUE_FOLDER.mkdir();
        }
        
        // Initialize registration store
        try
        {
            registrationIDStore = new RegistrationIDStore(new File(getDataFolder(), "id-store.txt"));
        }
        catch(IOException e)
        {
            getLogger().severe("Could not initialize data id store");
            e.printStackTrace();
            getPluginLoader().disablePlugin(this);
            return;
        }
        
        resourcePackSender = new NettyResourcePackSender();
        resourcePackSender.setURL("http://localhost:{port}/{pack-id}");
        resourcePackSender.start();
        
        getServer().getScheduler().scheduleSyncRepeatingTask(
                this, () -> resourcePackSender.cleanPending(), 0L, (20 * 60));
    }
    
    @Override
    public void onDisable()
    {
        resourcePackSender.stop();
    }
    
    /**
     * Sends a ResourcePackCompileEvent for the specified player and, if not cancelled, sends the pack to the player
     *
     * @param player The player to compile the resource pack for
     */
    public static void recompilePack(Player player)
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
    public static ResourcePack getDefaultPack()
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
    public static int getServerPort()
    {
        return Bukkit.getPort();
    }
    
    public static String getPackUrl()
    {
        return resourcePackSender.getURL();
    }
    
    public static void setPackUrl(String packUrl)
    {
        resourcePackSender.setURL(packUrl);
    }
}
