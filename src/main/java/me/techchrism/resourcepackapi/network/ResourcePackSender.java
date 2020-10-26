package me.techchrism.resourcepackapi.network;

import me.techchrism.resourcepackapi.pack.ResourcePack;
import org.bukkit.entity.Player;

import java.io.InputStream;

public interface ResourcePackSender
{
    void sendPackToPlayer(Player player, ResourcePack pack);
    void cleanPending();
    
    void setURL(String url);
    String getURL();
}
