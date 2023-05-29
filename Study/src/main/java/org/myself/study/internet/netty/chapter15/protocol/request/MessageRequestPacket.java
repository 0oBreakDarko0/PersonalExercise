package org.myself.study.internet.netty.chapter15.protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.myself.study.internet.netty.chapter15.protocol.Packet;

import static org.myself.study.internet.netty.chapter15.protocol.command.Command.MESSAGE_REQUEST;

@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private String message;

    public MessageRequestPacket(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
