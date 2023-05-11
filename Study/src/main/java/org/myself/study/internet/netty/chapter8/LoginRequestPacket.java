package org.myself.study.internet.netty.chapter8;

import lombok.Data;

@Data
public class LoginRequestPacket extends Packet{

    private Integer userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
