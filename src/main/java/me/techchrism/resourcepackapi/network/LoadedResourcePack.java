package me.techchrism.resourcepackapi.network;

import java.io.File;

public class LoadedResourcePack implements Expirable
{
    private File file;
    private byte[] hash;
    
    public LoadedResourcePack(File file, byte[] hash)
    {
        this.file = file;
        this.hash = hash;
    }
    
    public File getFile()
    {
        return file;
    }
    
    public void setFile(File file)
    {
        this.file = file;
    }
    
    public byte[] getHash()
    {
        return hash;
    }
    
    public void setHash(byte[] hash)
    {
        this.hash = hash;
    }
    
    @Override
    public void onExpire()
    {
        file.delete();
    }
}
