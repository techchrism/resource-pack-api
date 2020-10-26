package me.techchrism.resourcepackapi.network;

import java.io.File;

public class LoadedResourcePack implements Expirable
{
    private final File file;
    private final byte[] hash;
    
    public LoadedResourcePack(File file, byte[] hash)
    {
        this.file = file;
        this.hash = hash;
    }
    
    public File getFile()
    {
        return file;
    }
    
    public byte[] getHash()
    {
        return hash;
    }
    
    @Override
    public void onExpire()
    {
        file.delete();
    }
}
