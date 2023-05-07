package org.myself.study.internet.netty.chapter6;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 自定义逻辑处理器
 * @author mapengfei
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //获取数据
        System.out.println(new Date() + ": 客户端写出数据");
        ByteBuf byteBuf = this.getByteBuf(ctx);

        //写入数据
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        System.out.println(new Date() + ": 客户端读取到数据 > " + buf.toString(StandardCharsets.UTF_8));
    }

    /**
     * 获取数据
     * @param ctx
     * @return
     */
    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        //获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "您好，闪电侠！".getBytes(Charset.forName("utf-8"));
        //将数据填充到buffer
        buffer.writeBytes(bytes);

        return buffer;
    }
}
