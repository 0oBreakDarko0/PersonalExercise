package org.myself.study.internet.im.protocol.response;

import lombok.Data;
import org.myself.study.internet.im.protocol.Packet;

import static org.myself.study.internet.im.protocol.command.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}
