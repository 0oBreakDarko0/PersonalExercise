package org.myself.study.internet.im.server.handler;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.myself.study.internet.im.protocol.Packet;
import org.myself.study.internet.im.protocol.PacketCodeClient;
import org.myself.study.internet.im.protocol.request.LoginRequestPacket;
import org.myself.study.internet.im.protocol.request.MessageRequestPacket;
import org.myself.study.internet.im.protocol.response.LoginResponsePacket;
import org.myself.study.internet.im.protocol.response.MessageResponsePacket;

import java.util.Date;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf requestByteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeClient.INSTANCE.decode(requestByteBuf);

        //判断是否是登录请求数据包
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());

            if (valid(loginRequestPacket)) {
                System.out.println("登录成功");
                loginResponsePacket.setSuccess(true);

            } else {
                System.out.println("登录失败");
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("用户名或者密码错误");
            }

            ByteBuf byteBuf = PacketCodeClient.INSTANCE.encode(ctx.alloc().buffer(), loginResponsePacket);
            ctx.channel().writeAndFlush(byteBuf);
        } else if (packet instanceof MessageRequestPacket) {
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + "： 收到客户端消息： " +
                    messageRequestPacket.getMessage());
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复【" +
                    messageRequestPacket.getMessage() + "】");
            ByteBuf responseByteBuf = PacketCodeClient.INSTANCE.encode(ctx.alloc().buffer(), messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }

    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
