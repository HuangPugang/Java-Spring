package com.hp.nettyclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/**
 * Created by Paul on 2018/8/30
 */

public class ClientHandler extends ChannelInboundHandlerAdapter {

    private int count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        for (int i = 0; i < 100; i++) {
            String str = "客户端发送消息" + i + System.getProperty("line.separator");
            byte[] req = str.getBytes("UTF-8");
            ByteBuf buf = Unpooled.buffer(req.length);
            buf.writeBytes(req);
            ctx.writeAndFlush(buf);


        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
//        ByteBuf buf = (ByteBuf)msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String str = new String(req, "UTF-8");
        String str = (String) msg;
        System.out.println("=======客户端收到消息=======");
        System.out.println(str);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }

}
