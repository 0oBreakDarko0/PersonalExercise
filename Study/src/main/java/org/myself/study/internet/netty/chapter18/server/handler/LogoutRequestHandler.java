package org.myself.study.internet.netty.chapter18.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.myself.study.internet.netty.chapter18.protocol.request.LogoutRequestPacket;
import org.myself.study.internet.netty.chapter18.protocol.response.LogoutResponsePacket;
import org.myself.study.internet.netty.chapter18.util.SessionUtil;

/**
 * 登出请求
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) {
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
