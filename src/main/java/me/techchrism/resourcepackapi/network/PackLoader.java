package me.techchrism.resourcepackapi.network;

import me.techchrism.resourcepackapi.pack.ResourcePack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PackLoader
{
    /**
     * Turns a ResourcePack into a LoadedResourcePack by saving it to a file
     * @param pack the ResourcePack to load
     * @param root the directory to save the file to
     * @param name the name of the file (will be appended with .zip)
     * @return the LoadedResourcePack after saving the file
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static LoadedResourcePack load(ResourcePack pack, File root, String name) throws IOException, NoSuchAlgorithmException
    {
        File packFile = new File(root, name + ".zip");
        FileOutputStream fos = new FileOutputStream(packFile);
        pack.writeToZip(fos);
        byte[] hash = createSha1(packFile);
        return new LoadedResourcePack(packFile, hash);
    }
    
    public static byte[] createSha1(File file) throws IOException, NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(file), md))
        {
            while(dis.read() != -1); //empty loop to clear the data
            md = dis.getMessageDigest();
        }
        return md.digest();
    }
}
