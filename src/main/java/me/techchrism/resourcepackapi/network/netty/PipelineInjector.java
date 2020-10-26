package me.techchrism.resourcepackapi.network.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Class designed to hook into the main channel and inject a handler into netty subchannels
 */
public class PipelineInjector extends ChannelInboundHandlerAdapter
{
    private final String name;
    private final ChannelHandler handler;
    
    public PipelineInjector(String name, ChannelHandler handler)
    {
        this.name = name;
        this.handler = handler;
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
    {
        ((Channel) msg).pipeline().addFirst(name, handler);
        ctx.fireChannelRead(msg);
    }
}
