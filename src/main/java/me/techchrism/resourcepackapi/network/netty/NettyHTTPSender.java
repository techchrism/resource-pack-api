package me.techchrism.resourcepackapi.network.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;
import me.techchrism.resourcepackapi.network.LoadedResourcePack;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class NettyHTTPSender extends SimpleChannelInboundHandler<Object>
{
    private HttpRequest request;
    private final NettyResourcePackSender resourcePackSender;
    
    public NettyHTTPSender(NettyResourcePackSender resourcePackSender)
    {
        this.resourcePackSender = resourcePackSender;
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
    {
        ctx.flush();
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws IOException
    {
        if(msg instanceof HttpRequest)
        {
            HttpRequest request = this.request = (HttpRequest) msg;
            if(HttpUtil.is100ContinueExpected(request))
            {
                writeContinueResponse(ctx);
            }
        }
        
        if(msg instanceof HttpContent)
        {
            if(msg instanceof LastHttpContent)
            {
                LastHttpContent trailer = (LastHttpContent) msg;
    
                try
                {
                    UUID id = UUID.fromString(request.uri().replaceAll("/", ""));
                    LoadedResourcePack loadedResourcePack = resourcePackSender.getPack(id);
                    if(loadedResourcePack == null)
                    {
                        writeNotFoundResponse(ctx);
                    }
                    else
                    {
                        writeZipResponse(ctx, trailer, loadedResourcePack.getFile());
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    writeNotFoundResponse(ctx);
                }
            }
        }
    }
    
    private void writeZipResponse(ChannelHandlerContext ctx, LastHttpContent trailer, File file) throws IOException
    {
        boolean keepAlive = HttpUtil.isKeepAlive(request);
        
        FullHttpResponse httpResponse = new DefaultFullHttpResponse(HTTP_1_1, trailer.decoderResult()
                .isSuccess() ? OK : BAD_REQUEST, Unpooled.EMPTY_BUFFER);
        
        httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/zip");
        httpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, (int) file.length());
        
        if(keepAlive)
        {
            httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }
        
        ctx.write(httpResponse);
        ctx.channel().writeAndFlush(new ChunkedFile(file));
        
        if(!keepAlive)
        {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }
    
    private void writeContinueResponse(ChannelHandlerContext ctx)
    {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, CONTINUE, Unpooled.EMPTY_BUFFER);
        ctx.write(response);
    }
    
    private void writeNotFoundResponse(ChannelHandlerContext ctx)
    {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, NOT_FOUND, Unpooled.EMPTY_BUFFER);
        ctx.write(response);
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
    {
        cause.printStackTrace();
        ctx.close();
    }
}
