package com.walle.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Random;

/**
 * @author: bbpatience
 * @date: 2018/12/29
 * @description: EchoClientHandler
 **/
@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf bb = Unpooled.buffer(42);
        Random r = new Random();
        while (bb.writableBytes() >= 4) {
            int i = r.nextInt(10);
            bb.writeInt(i);
        }
        ctx.writeAndFlush(bb);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf)
        throws Exception {
//        System.out.println("Client received: " + byteBuf.toString(CharsetUtil.UTF_8));
        while (byteBuf.isReadable()) {
            System.out.print(byteBuf.readInt() + ",");
        }
        System.out.println();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
