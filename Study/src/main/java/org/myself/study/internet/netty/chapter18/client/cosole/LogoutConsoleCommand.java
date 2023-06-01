package org.myself.study.internet.netty.chapter18.client.cosole;

import io.netty.channel.Channel;
import org.myself.study.internet.netty.chapter18.protocol.request.LogoutRequestPacket;

import java.util.Scanner;

public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
