package org.myself.study.internet.traditional;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统的阻塞网络IO服务端
 * @author mapengfei
 */
public class IOServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            new Thread(() -> {
                while (true) {
                    try {
                        Socket accept = serverSocket.accept();
                        new Thread(() -> {
                            int len;
                            byte[] buf = new byte[1024];
                            try {
                                InputStream inputStream = accept.getInputStream();

                                while ((len = inputStream.read(buf)) != -1) {
                                    System.out.println(new String(buf, 0, len));
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
