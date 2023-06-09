package org.myself.study.internet.netty.chapter18.protocol.response;

import lombok.Data;
import org.myself.study.internet.netty.chapter18.protocol.Packet;

import java.util.List;

import static org.myself.study.internet.netty.chapter17.protocol.command.Command.CREATE_GROUP_RESPONSE;

@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_RESPONSE;
    }
}
