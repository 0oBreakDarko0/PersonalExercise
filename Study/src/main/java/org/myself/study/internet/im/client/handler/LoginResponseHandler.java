package org.myself.study.internet.im.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.myself.study.internet.im.protocol.PacketCodeClient;
import org.myself.study.internet.im.protocol.request.LoginRequestPacket;
import org.myself.study.internet.im.protocol.response.LoginResponsePacket;
import org.myself.study.internet.im.util.LoginUtil;

import java.util.Date;
import java.util.UUID;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 开始发送登录信息");
        //创建登陆对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("老马");
        loginRequestPacket.setPassword("123456");

        ByteBuf byteBuf = PacketCodeClient.INSTANCE.encode(ctx.alloc().buffer(), loginRequestPacket);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        if (loginResponsePacket.isSuccess()) {
            LoginUtil.markAsLogin(ctx.channel());
            System.out.println(new Date() + "： 客户端登录成功");
        } else {
            System.out.println(new Date() + "： 客户端登录失败，原因：" +
                    loginResponsePacket.getReason());
        }
    }
}
