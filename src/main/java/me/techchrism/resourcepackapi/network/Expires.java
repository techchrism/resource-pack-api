package me.techchrism.resourcepackapi.network;

public class Expires<T extends Expirable>
{
    private static final long EXPIRE_TIME = (1000 * 60);
    private final long queuedTime = System.currentTimeMillis();
    private T value;
    
    public Expires()
    {
    
    }
    
    public Expires(T value)
    {
        this.value = value;
    }
    
    public boolean isExpired()
    {
        return (this.queuedTime + EXPIRE_TIME < System.currentTimeMillis());
    }
    
    public T get()
    {
        return value;
    }
    
    public void set(T value)
    {
        this.value = value;
    }
}
