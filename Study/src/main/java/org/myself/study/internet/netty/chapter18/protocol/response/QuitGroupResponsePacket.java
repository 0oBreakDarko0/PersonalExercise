package org.myself.study.internet.netty.chapter18.protocol.response;

import lombok.Data;
import org.myself.study.internet.netty.chapter18.protocol.Packet;

import static org.myself.study.internet.netty.chapter18.protocol.command.Command.QUIT_GROUP_RESPONSE;


@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }
}
