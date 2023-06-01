package org.myself.study.internet.netty.chapter18.protocol.request;

import lombok.Data;
import org.myself.study.internet.netty.chapter18.protocol.Packet;

import static org.myself.study.internet.netty.chapter18.protocol.command.Command.QUIT_GROUP_REQUEST;


@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_REQUEST;
    }
}
