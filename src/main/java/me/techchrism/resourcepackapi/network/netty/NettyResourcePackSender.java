package me.techchrism.resourcepackapi.network.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import me.techchrism.resourcepackapi.ResourcePackPlugin;
import me.techchrism.resourcepackapi.network.Expires;
import me.techchrism.resourcepackapi.network.LoadedResourcePack;
import me.techchrism.resourcepackapi.network.PackLoader;
import me.techchrism.resourcepackapi.network.ResourcePackSender;
import me.techchrism.resourcepackapi.pack.ResourcePack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class NettyResourcePackSender implements ResourcePackSender
{
    private final HashMap<UUID, Expires<LoadedResourcePack>> packs = new HashMap<>();
    private Channel channel;
    private String packURL;
    
    public NettyResourcePackSender()
    {
    
    }
    
    /**
     * Adds to the default Minecraft server netty channel to intercept GET requests
     */
    public void inject()
    {
        try
        {
            Object server = getNmsClass("MinecraftServer").getMethod("getServer").invoke(null);
            Object serverConnection = server.getClass().getMethod("getServerConnection").invoke(server);
        
            Field listeningChannelsField = serverConnection.getClass().getDeclaredField("listeningChannels");
            listeningChannelsField.setAccessible(true);
            List<ChannelFuture> listeningChannels = (List<ChannelFuture>) listeningChannelsField.get(serverConnection);
        
            // In testing, there has only been 1 listening channel
            channel = listeningChannels.get(0).channel();
            channel.pipeline().addFirst("pipeline_injector", new PipelineInjector("http_checker",
                    new HTTPChecker(this)));
        }
        catch(Exception e)
        {
            System.err.println("Error injecting into netty channel:");
            e.printStackTrace();
        }
    }
    
    /**
     * Removes netty channel pipeline injector
     */
    public void cleanup()
    {
        channel.pipeline().remove("pipeline_injector");
    }
    
    /**
     * Gets a loaded pack by its temporary id
     * @param tempId the temporary id of the pack
     * @return null if the pack doesn't exist or if it has expired, otherwise it returns the loaded pack
     */
    public LoadedResourcePack getPack(UUID tempId)
    {
        if(!packs.containsKey(tempId))
        {
            return null;
        }
        Expires<LoadedResourcePack> pack = packs.get(tempId);
        if(pack.isExpired())
        {
            return null;
        }
        return pack.get();
    }
    
    @Override
    public void sendPackToPlayer(Player player, ResourcePack pack)
    {
        try
        {
            UUID tempId = UUID.randomUUID();
            LoadedResourcePack loadedPack = PackLoader.load(pack, ResourcePackPlugin.INSTANCE.getDataFolder(), tempId.toString());
            packs.put(tempId, new Expires<>(loadedPack));
            
            player.setResourcePack(packURL.replaceAll("\\{local-ip}", InetAddress.getLocalHost().getHostAddress())
                    .replaceAll("\\{port}", Integer.toString(Bukkit.getPort()))
                    .replaceAll("\\{pack-id}", tempId.toString()), loadedPack.getHash());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void cleanPending()
    {
        packs.entrySet().removeIf(uuidExpiresEntry -> uuidExpiresEntry.getValue().isExpired());
    }
    
    @Override
    public void setURL(String url)
    {
        this.packURL = url;
    }
    
    @Override
    public String getURL()
    {
        return packURL;
    }
    
    private Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException
    {
        return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + nmsClassName);
    }
}
