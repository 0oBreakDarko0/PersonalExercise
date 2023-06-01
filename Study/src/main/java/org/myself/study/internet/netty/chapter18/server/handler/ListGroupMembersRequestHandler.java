package org.myself.study.internet.netty.chapter18.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.myself.study.internet.netty.chapter18.protocol.request.ListGroupMembersRequestPacket;
import org.myself.study.internet.netty.chapter18.protocol.response.ListGroupMembersResponsePacket;
import org.myself.study.internet.netty.chapter18.session.Session;
import org.myself.study.internet.netty.chapter18.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket requestPacket) throws Exception {
        // 1. 获取群的ChannelGroup
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        // 2. 遍历群成员的Channel对应的Session，构造群成员的信息
        List<Session> sessionList = new ArrayList<>();
        for (Channel channel : channelGroup) {
            Session session = SessionUtil.getSession(channel);
            sessionList.add(session);

        }
        // 3. 构建获取群成员列表响应，写回客户端
        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();
        responsePacket.setGroupId(groupId);
        responsePacket.setSessionList(sessionList);
        ctx.channel().writeAndFlush(responsePacket);
    }
}
