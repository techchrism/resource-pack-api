package me.techchrism.resourcepackapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ResourcePackCandidate
{
    private String description;
    private BufferedImage icon;
    private int packFormat;
    
    private final HashMap<String, JsonObject> itemModels = new HashMap<>();
    private final JsonObject packMeta;
    public static final int PACK_FORMAT = 4;
    
    public ResourcePackCandidate(String description)
    {
        packMeta = new JsonObject();
        JsonObject meta = new JsonObject();
        meta.addProperty("pack_format", PACK_FORMAT);
        meta.addProperty("description", description);
        packMeta.add("pack", meta);
    }
    
    public void addCustomItem(Material fallback, InputStream model, NamespacedKey key)
    {
        String itemId = fallback.toString().toLowerCase();
        if(!itemModels.containsKey(itemId))
        {
            InputStream defaultJson = getClass().getClassLoader().getResourceAsStream("models\\" + itemId + ".json");
            JsonParser parser = new JsonParser();
            itemModels.put(itemId, parser.parse(new InputStreamReader(defaultJson)).getAsJsonObject());
        }
    }
    
    public void write(OutputStream os) throws IOException
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ZipOutputStream zos = new ZipOutputStream(os);
        Writer writer = new OutputStreamWriter(zos);
        
        ZipEntry packEntry = new ZipEntry("pack.mcmeta");
        zos.putNextEntry(packEntry);
        gson.toJson(packMeta, writer);
        
        writer.flush();
        writer.close();
        zos.flush();
        zos.close();
    }
}
