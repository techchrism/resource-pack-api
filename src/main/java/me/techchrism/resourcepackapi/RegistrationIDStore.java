package me.techchrism.resourcepackapi;

import org.bukkit.NamespacedKey;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RegistrationIDStore
{
    private File storageFile;
    private HashMap<NamespacedKey, Integer> ids = new HashMap<>();
    private boolean changed = false;
    
    // Choose 2000 as a starting value so any additions from other resource packs have something to work with
    private int current = 2000;
    
    public RegistrationIDStore(File storageFile) throws IOException
    {
        this.storageFile = storageFile;
        if(!this.storageFile.exists())
        {
            //this.storageFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(storageFile));
            writeHeader(writer);
            writer.close();
        }
        else
        {
            // Custom csv parsing? What could go wrong?
            BufferedReader reader = new BufferedReader(new FileReader(storageFile));
            String line = reader.readLine();
            while(line != null)
            {
                if(line.contains(","))
                {
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[2]);
                    ids.put(new NamespacedKey(parts[0], parts[1]), id);
                    
                    if(id > current)
                    {
                        current = id;
                    }
                }
                
                line = reader.readLine();
            }
            reader.close();
        }
    }
    
    private void writeHeader(BufferedWriter writer) throws IOException
    {
        writer.write("  !! This file is for registering custom item ids. Do not modify unless you know what you are doing. !!");
        writer.newLine();
        writer.newLine();
    }
    
    public void save() throws IOException
    {
        if(changed)
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(storageFile));
            writeHeader(writer);
            for(Map.Entry<NamespacedKey, Integer> entry : ids.entrySet())
            {
                writer.write(entry.getKey().getNamespace());
                writer.write(",");
                writer.write(entry.getKey().getKey());
                writer.write(",");
                writer.write(entry.getValue().toString());
                writer.newLine();
            }
            writer.close();
            changed = false;
        }
    }
    
    public boolean hasChanged()
    {
        return changed;
    }
    
    public boolean has(NamespacedKey key)
    {
        return ids.containsKey(key);
    }
    
    public int register(NamespacedKey key)
    {
        if(this.has(key))
        {
            return ids.get(key);
        }
        else
        {
            changed = true;
            current++;
            ids.put(key, current);
            return current;
        }
    }
    
    public int get(NamespacedKey key)
    {
        return ids.get(key);
    }
}

