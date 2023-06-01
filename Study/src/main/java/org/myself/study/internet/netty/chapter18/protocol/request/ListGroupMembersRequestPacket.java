package org.myself.study.internet.netty.chapter18.protocol.request;

import lombok.Data;
import org.myself.study.internet.netty.chapter18.protocol.Packet;

import static org.myself.study.internet.netty.chapter18.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;


@Data
public class ListGroupMembersRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
