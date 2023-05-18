package org.myself.study.internet.im.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.myself.study.internet.im.protocol.request.LoginRequestPacket;
import org.myself.study.internet.im.protocol.response.LoginResponsePacket;
import org.myself.study.internet.im.util.LoginUtil;

import java.util.Date;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println(new Date() + ": 收到客户端登录请求……");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());

        if (valid(loginRequestPacket)) {
            System.out.println("登录成功");
            loginResponsePacket.setSuccess(true);
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println("登录失败");
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("用户名或者密码错误");
        }
        ctx.channel().writeAndFlush(loginResponsePacket);
    }
//
//    private LoginResponsePacket login(LoginRequestPacket loginRequestPacket) {
//        System.out.println(new Date() + ": 收到客户端登录请求……");
//
//        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
//        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
//
//        if (valid(loginRequestPacket)) {
//            System.out.println("登录成功");
//            loginResponsePacket.setSuccess(true);
//
//        } else {
//            System.out.println("登录失败");
//            loginResponsePacket.setSuccess(false);
//            loginResponsePacket.setReason("用户名或者密码错误");
//        }
//
//        return loginResponsePacket;
//
//    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
