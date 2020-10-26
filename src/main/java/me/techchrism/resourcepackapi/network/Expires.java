package me.techchrism.resourcepackapi.network;

/**
 * Basic container class that holds a value that will expire in the future
 * @param <T> The class to hold
 */
public class Expires<T extends Expirable>
{
    private static final long EXPIRE_TIME_NANOS = 1000L * 1000L * 1000L * 60L;
    private final long queuedTime = System.nanoTime();
    private boolean hasExpired = false;
    private T value;
    
    public Expires(T value)
    {
        this.value = value;
    }
    
    public boolean isExpired()
    {
        if(this.queuedTime + EXPIRE_TIME_NANOS < System.nanoTime())
        {
            if(!hasExpired)
            {
                value.onExpire();
                hasExpired = true;
            }
            return true;
        }
        return false;
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
