package me.techchrism.resourcepackapi.network;

import me.techchrism.resourcepackapi.pack.ResourcePack;

import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PackLoader
{
    public static LoadedResourcePack load(ResourcePack pack, File root, String name) throws IOException, NoSuchAlgorithmException
    {
        File packFile = new File(root, name + ".zip");
        FileOutputStream fos = new FileOutputStream(packFile);
        pack.writeToZip(fos);
        byte[] hash = createSha1(packFile);
        System.out.println("Hash: " + Base64.getEncoder().encodeToString(hash));
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
