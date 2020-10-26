package me.techchrism.resourcepackapi;

import me.techchrism.resourcepackapi.network.netty.NettyResourcePackSender;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ResourcePackPlugin extends JavaPlugin implements Listener
{
    private NettyResourcePackSender resourcePackSender;
    public static ResourcePackPlugin INSTANCE;
    private String bruhSound = "";
    public static File PLUGIN_FOLDER;
    private ResourcePackAPI api;
    
    @Override
    public void onEnable()
    {
        INSTANCE = this;
    
        // Set up directories
        PLUGIN_FOLDER = getDataFolder();
        if(!PLUGIN_FOLDER.exists())
        {
            PLUGIN_FOLDER.mkdir();
        }
    
        // Initialize registration store
        RegistrationIDStore store;
        try
        {
            store = new RegistrationIDStore(new File(getDataFolder(), "id-store.txt"));
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
        resourcePackSender.inject();
        api = new ResourcePackAPI(store, resourcePackSender);
    
        getServer().getScheduler().scheduleSyncRepeatingTask(
                this, () -> resourcePackSender.cleanPending(), 0L, (20 * 60));
        
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    @Override
    public void onDisable()
    {
        resourcePackSender.cleanup();
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onResourcePackCompile(ResourcePackCompileEvent event)
    {
        event.getResourcePack().setDescription(
                ChatColor.translateAlternateColorCodes('&',
                        "&6Crafted with &c\u2764&6 for " + event.getPlayer().getName()));
        
        InputStream is = getClass().getResourceAsStream("/bruh.ogg");
        bruhSound = event.getResourcePack().addSimpleSound(new NamespacedKey(this, "bruh"), is, "bruh");
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerSwapItem(PlayerSwapHandItemsEvent event)
    {
        event.setCancelled(true);
        event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), bruhSound, 1F, 1F);
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        api.recompilePack(event.getPlayer());
    }
}
