package org.myself.study.internet.im.protocol.response;

import lombok.Data;
import org.myself.study.internet.im.protocol.Packet;

import static org.myself.study.internet.im.protocol.command.Command.LOGIN_RESPONSE;


@Data
public class LoginResponsePacket extends Packet {
    private String userId;

    private String userName;

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
