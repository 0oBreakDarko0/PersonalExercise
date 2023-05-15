package org.myself.study.internet.netty.chapter8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

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
        }


    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
