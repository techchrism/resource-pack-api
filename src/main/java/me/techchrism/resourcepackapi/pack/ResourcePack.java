package me.techchrism.resourcepackapi.pack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.bukkit.NamespacedKey;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.FileTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ResourcePack
{
    private String description;
    private transient InputStream icon;
    private int packFormat;
    private Map<String, ResourcePackLanguageMeta> languageMeta = new HashMap<>();
    
    //private ResourcePackSounds sounds;
    private Map<String, ResourcePackSoundEvent> sounds = new HashMap<>();
    private Map<String, ResourcePackLanguage> languages = new HashMap<>();
    private Map<String, InputStream> textures = new HashMap<>();
    private Map<String, TextureMeta> textureMeta = new HashMap<>();
    private Map<String, InputStream> soundFiles = new HashMap<>();
    private Map<String, ResourcePackBlockState> blockStates = new HashMap<>();
    private Map<String, BlockModel> blockModels = new HashMap<>();
    private Map<String, ItemModel> itemModels = new HashMap<>();
    //TODO fonts, colormaps, particles
    
    public ResourcePack()
    {
    
    }
    
    public ResourcePack(ZipFile zip) throws IOException
    {
        addFromZip(zip);
    }
    
    /**
     * Reads an existing resource pack zip file and merges/replaces with this pack
     * @param zip The ZipFile to read from
     * @throws IOException
     */
    public void addFromZip(ZipFile zip) throws IOException
    {
        Enumeration<? extends ZipEntry> entries = zip.entries();
        Gson gson = new Gson();
        
        // Iterate through the zip, checking each file
        while(entries.hasMoreElements())
        {
            ZipEntry entry = entries.nextElement();
            InputStream stream = zip.getInputStream(entry);
            
            String n = entry.getName();
            if(n.startsWith("assets/minecraft/textures/"))
            {
                // Texture file
                if(n.endsWith(".mcmeta"))
                {
                    Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
                    textureMeta.put(n.substring("assets/minecraft/textures/".length()),
                            gson.fromJson(reader, TextureMeta.class));
                    reader.close();
                    stream.close();
                }
                else
                {
                    textures.put(n.substring("assets/minecraft/textures/".length()), stream);
                }
            }
            else if(n.startsWith("assets/minecraft/sounds/"))
            {
                // Sound file
                soundFiles.put(n.substring("assets/minecraft/sounds/".length()), stream);
            }
            else if(n.startsWith("assets/minecraft/blockstates/"))
            {
                // Block state json
                Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
                blockStates.put(n.substring("assets/minecraft/blockstates/".length()),
                        gson.fromJson(reader, ResourcePackBlockState.class));
                reader.close();
                stream.close();
            }
            else if(n.startsWith("assets/minecraft/lang/"))
            {
                // Language json
                Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
                languages.put(n.substring("assets/minecraft/lang/".length()),
                        gson.fromJson(reader, ResourcePackLanguage.class));
                reader.close();
                stream.close();
            }
            else if(n.startsWith("assets/minecraft/models/block/"))
            {
                // Block model json
                Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
                blockModels.put(n.substring("assets/minecraft/models/block/".length()),
                        gson.fromJson(reader, BlockModel.class));
                reader.close();
                stream.close();
            }
            else if(n.startsWith("assets/minecraft/models/item/"))
            {
                // Item model json
                Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
                itemModels.put(n.substring("assets/minecraft/models/item/".length()),
                        gson.fromJson(reader, ItemModel.class));
                reader.close();
                stream.close();
            }
            else if(n.equals("assets/minecraft/sounds.json"))
            {
                // Sounds json
                Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
                Type type = new TypeToken<HashMap<String, ResourcePackSoundEvent>>(){}.getType();
                HashMap<String, ResourcePackSoundEvent> from = gson.fromJson(reader, type);
                sounds.putAll(from);
                reader.close();
                stream.close();
            }
            else if(n.equals("pack.mcmeta"))
            {
                // Pack metadata json
                Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
                JsonObject mcmeta = gson.fromJson(reader, JsonObject.class);
                if(mcmeta.has("language"))
                {
                    Type type = new TypeToken<HashMap<String, ResourcePackLanguageMeta>>(){}.getType();
                    HashMap<String, ResourcePackLanguageMeta> languageMetas = gson.fromJson(mcmeta.get("language"), type);
                    languageMeta.putAll(languageMetas);
                }
                reader.close();
                stream.close();
            }
        }
    }
    
    private ZipEntry cleanTime(ZipEntry entry)
    {
        entry.setLastAccessTime(FileTime.fromMillis(0));
        entry.setCreationTime(FileTime.fromMillis(0));
        entry.setLastModifiedTime(FileTime.fromMillis(0));
        return entry;
    }
    
    /**
     * Compiles and zips the resource pack to the specified output stream
     * Closes all sound and texture streams
     *
     * @param os The output stream for the compiled resource pack
     * @throws IOException
     */
    public void writeToZip(OutputStream os) throws IOException
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String assets = "assets/minecraft/";
        
        ZipOutputStream zos = new ZipOutputStream(os);
        Writer writer = new OutputStreamWriter(zos, StandardCharsets.UTF_8);
    
        // Write pack meta
        zos.putNextEntry(cleanTime(new ZipEntry("pack.mcmeta")));
        JsonObject packMCMeta = new JsonObject();
        JsonObject packMetaObject = new JsonObject();
        packMetaObject.addProperty("description", description);
        packMetaObject.addProperty("pack_format", packFormat);
        packMCMeta.add("pack", packMetaObject);
        packMCMeta.add("languages", gson.toJsonTree(languageMeta));
        gson.toJson(packMCMeta, writer);
        writer.flush();
        zos.closeEntry();

        // Write sound meta
        if(sounds != null && sounds.size() > 0)
        {
            zos.putNextEntry(cleanTime(new ZipEntry(assets + "sounds.json")));
            gson.toJson(sounds, writer);
            writer.flush();
            zos.closeEntry();
        }
        
        // Write sound files
        if(soundFiles != null)
        {
            for(Map.Entry<String, InputStream> entry : soundFiles.entrySet())
            {
                zos.putNextEntry(cleanTime(new ZipEntry(assets + "sounds/" + entry.getKey())));
                byte[] buffer = new byte[10*1024];
                for(int length; (length = entry.getValue().read(buffer)) != -1;)
                {
                    zos.write(buffer, 0, length);
                }
                entry.getValue().close();
                zos.closeEntry();
            }
        }
        
        // Write texture files
        if(textures != null)
        {
            for(Map.Entry<String, InputStream> entry : textures.entrySet())
            {
                zos.putNextEntry(cleanTime(new ZipEntry(assets + "textures/" + entry.getKey())));
                byte[] buffer = new byte[10*1024];
                for(int length; (length = entry.getValue().read(buffer)) != -1;)
                {
                    zos.write(buffer, 0, length);
                }
                entry.getValue().close();
                zos.closeEntry();
            }
        }
        
        // Write texture meta files
        if(textureMeta != null)
        {
            for(Map.Entry<String, TextureMeta> entry : textureMeta.entrySet())
            {
                zos.putNextEntry(cleanTime(new ZipEntry(assets + "textures/" + entry.getKey())));
                gson.toJson(entry.getValue(), writer);
                writer.flush();
                zos.closeEntry();
            }
        }
        
        // Write blockstate json
        if(blockStates != null)
        {
            for(Map.Entry<String, ResourcePackBlockState> entry : blockStates.entrySet())
            {
                zos.putNextEntry(cleanTime(new ZipEntry(assets + "blockstates/" + entry.getKey())));
                gson.toJson(entry.getValue(), writer);
                writer.flush();
                zos.closeEntry();
            }
        }
    
        // Write language json
        if(languages != null)
        {
            for(Map.Entry<String, ResourcePackLanguage> entry : languages.entrySet())
            {
                zos.putNextEntry(cleanTime(new ZipEntry(assets + "lang/" + entry.getKey())));
                gson.toJson(entry.getValue(), writer);
                writer.flush();
                zos.closeEntry();
            }
        }
        
        // Write item model json
        if(itemModels != null)
        {
            for(Map.Entry<String, ItemModel> entry : itemModels.entrySet())
            {
                zos.putNextEntry(cleanTime(new ZipEntry(assets + "models/item/" + entry.getKey())));
                gson.toJson(entry.getValue(), writer);
                writer.flush();
                zos.closeEntry();
            }
        }
    
        // Write block model json
        if(blockModels != null)
        {
            for(Map.Entry<String, BlockModel> entry : blockModels.entrySet())
            {
                zos.putNextEntry(cleanTime(new ZipEntry(assets + "models/block/" + entry.getKey())));
                gson.toJson(entry.getValue(), writer);
                writer.flush();
                zos.closeEntry();
            }
        }
        
        writer.close();
        zos.close();
    }
    
    /**
     * A convenience method for adding a simple sound
     *
     * @param name The name of the sound to add
     * @param stream A stream for the sound data
     * @param subtitle Optional subtitle for when the sound is playing
     * @return The sound ID that can be passed into the playSound method
     */
    public String addSimpleSound(NamespacedKey name, InputStream stream, String subtitle)
    {
        String pathName = "custom/" + name.toString().replaceAll("\\.", "/")
                .replaceAll(":", "/");
        String soundName = name.toString().replaceAll(":", ".");
        
        ResourcePackSoundEvent ev = new ResourcePackSoundEvent();
        ev.getSounds().add(new ResourcePackSound(pathName));
        ev.setSubtitle(subtitle);
        sounds.put(soundName, ev);
        
        soundFiles.put(pathName + ".ogg", stream);
        
        return soundName;
    }
    
    public String addSimpleSound(NamespacedKey name, InputStream stream)
    {
        return addSimpleSound(name, stream, null);
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public InputStream getIcon()
    {
        return icon;
    }
    
    public void setIcon(InputStream icon)
    {
        this.icon = icon;
    }
    
    public int getPackFormat()
    {
        return packFormat;
    }
    
    public void setPackFormat(int packFormat)
    {
        this.packFormat = packFormat;
    }
    
    public Map<String, ResourcePackLanguageMeta> getLanguageMeta()
    {
        return languageMeta;
    }
    
    public void setLanguageMeta(Map<String, ResourcePackLanguageMeta> languageMeta)
    {
        this.languageMeta = languageMeta;
    }
    
    public Map<String, ResourcePackSoundEvent> getSounds()
    {
        return sounds;
    }
    
    public void setSounds(Map<String, ResourcePackSoundEvent> sounds)
    {
        this.sounds = sounds;
    }
    
    public Map<String, ResourcePackLanguage> getLanguages()
    {
        return languages;
    }
    
    public void setLanguages(Map<String, ResourcePackLanguage> languages)
    {
        this.languages = languages;
    }
    
    public Map<String, InputStream> getTextures()
    {
        return textures;
    }
    
    public void setTextures(Map<String, InputStream> textures)
    {
        this.textures = textures;
    }
    
    public Map<String, TextureMeta> getTextureMeta()
    {
        return textureMeta;
    }
    
    public void setTextureMeta(Map<String, TextureMeta> textureMeta)
    {
        this.textureMeta = textureMeta;
    }
    
    public Map<String, InputStream> getSoundFiles()
    {
        return soundFiles;
    }
    
    public void setSoundFiles(Map<String, InputStream> soundFiles)
    {
        this.soundFiles = soundFiles;
    }
    
    public Map<String, ResourcePackBlockState> getBlockStates()
    {
        return blockStates;
    }
    
    public void setBlockStates(Map<String, ResourcePackBlockState> blockStates)
    {
        this.blockStates = blockStates;
    }
    
    public Map<String, BlockModel> getBlockModels()
    {
        return blockModels;
    }
    
    public void setBlockModels(Map<String, BlockModel> blockModels)
    {
        this.blockModels = blockModels;
    }
    
    public Map<String, ItemModel> getItemModels()
    {
        return itemModels;
    }
    
    public void setItemModels(Map<String, ItemModel> itemModels)
    {
        this.itemModels = itemModels;
    }
}
