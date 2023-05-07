package org.myself.study.internet.traditional;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 传统的网络IO客户端
 * @author mapengfei
 */
public class IOClient {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("localhost", 8000);
                while (true) {
                    socket.getOutputStream().write((new Date() + ": Hello World!").getBytes(StandardCharsets.UTF_8));
                    Thread.sleep(200);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
