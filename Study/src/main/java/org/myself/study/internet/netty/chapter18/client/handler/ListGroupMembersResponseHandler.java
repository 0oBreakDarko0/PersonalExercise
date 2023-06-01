package org.myself.study.internet.netty.chapter18.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.myself.study.internet.netty.chapter18.protocol.response.ListGroupMembersResponsePacket;

public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersResponsePacket responsePacket) throws Exception {
        System.out.println("群［" + responsePacket.getGroupId() + "］中的人包括：" + responsePacket.getSessionList());
    }
}
