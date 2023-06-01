package org.myself.study.internet.netty.chapter18.protocol.response;

import lombok.Data;
import org.myself.study.internet.netty.chapter18.protocol.Packet;
import org.myself.study.internet.netty.chapter18.session.Session;

import java.util.List;

import static org.myself.study.internet.netty.chapter18.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
