package org.myself.study.internet.netty.chapter16.protocol.request;

import lombok.Data;
import org.myself.study.internet.netty.chapter16.protocol.Packet;

import static org.myself.study.internet.netty.chapter15.protocol.command.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }
}
