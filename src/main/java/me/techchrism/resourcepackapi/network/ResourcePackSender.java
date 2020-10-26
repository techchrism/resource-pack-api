package me.techchrism.resourcepackapi.network;

import me.techchrism.resourcepackapi.pack.ResourcePack;
import org.bukkit.entity.Player;

public interface ResourcePackSender
{
    void sendPackToPlayer(Player player, ResourcePack pack);
    void cleanPending();
    
    void setURL(String url);
    String getURL();
}
