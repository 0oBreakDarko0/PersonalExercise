package org.myself.study.internet.im.protocol.request;

import lombok.Data;
import org.myself.study.internet.im.protocol.command.Command;
import org.myself.study.internet.im.protocol.Packet;

@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
