package me.techchrism.resourcepackapi.network;

import me.techchrism.resourcepackapi.pack.ResourcePack;
import org.bukkit.entity.Player;

public interface ResourcePackSender
{
    /**
     * Must be called to start any services the sender requires
     */
    void start();
    
    /**
     * Called to stop any services the sender started
     */
    void stop();
    
    /**
     * Sends the resource pack to the specified player
     * Involves saving the pack to a file, hashing the file, and adding it to the http queue
     * @param player The player to send the pack to
     * @param pack The pack to send
     */
    void sendPackToPlayer(Player player, ResourcePack pack);
    
    /**
     * Cleans resource packs that are expired
     * Should be called on a timer, ideally 60s
     */
    void cleanPending();
    
    /**
     * Sets the URL template for the sender
     * Note that this does not modify the expected URL format of the http listener
     * The following strings will be replaced:
     *   {local-ip}  -  The local ip address of the machine
     *   {port}  -  The port Minecraft is running on
     *   {pack-id}  -  The randomly generated id of the pack
     * @param url The URL template
     */
    void setURL(String url);
    
    /**
     * Gets the URL template
     * @return the URL template
     */
    String getURL();
}
