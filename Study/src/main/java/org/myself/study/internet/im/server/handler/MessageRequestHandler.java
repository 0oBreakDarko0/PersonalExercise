package org.myself.study.internet.im.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.myself.study.internet.im.protocol.request.MessageRequestPacket;
import org.myself.study.internet.im.protocol.response.MessageResponsePacket;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {

        ctx.channel().writeAndFlush(receiveMessage(messageRequestPacket));
    }

    private MessageResponsePacket receiveMessage(MessageRequestPacket messageRequestPacket) {
        System.out.println(new Date() + "： 收到客户端消息： " +
                messageRequestPacket.getMessage());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复【" +
                messageRequestPacket.getMessage() + "】");

        return messageResponsePacket;
    }
}
