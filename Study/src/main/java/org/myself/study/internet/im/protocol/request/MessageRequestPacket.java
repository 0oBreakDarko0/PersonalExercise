package org.myself.study.internet.im.protocol.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.myself.study.internet.im.protocol.Packet;

import static org.myself.study.internet.im.protocol.command.Command.MESSAGE_REQUEST;

@Data
@AllArgsConstructor
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
