package org.myself.study.internet.netty.chapter16.protocol.response;

import lombok.Data;
import org.myself.study.internet.netty.chapter16.protocol.Packet;

import static org.myself.study.internet.netty.chapter15.protocol.command.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}
