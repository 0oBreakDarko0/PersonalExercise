package org.myself.study.internet.c10k;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.stream.IntStream;

/**
 * c10k问题：Concurrent 10 000 Connections
 * 传统BIO式IO客户端
 *
 * @author mapengfei
 */
public class C10kTestIOClient {
    public static void main(String[] args) throws IOException {
        String ip = "localhost";
        LinkedList<SocketChannel> clients = new LinkedList<>();
        InetSocketAddress serverAddr = new InetSocketAddress(ip, 9998);
        IntStream.range(20000, 50000).forEach(i -> {
            try {
                SocketChannel client = SocketChannel.open();
                client.bind(new InetSocketAddress(ip, i));
                client.connect(serverAddr);
                System.out.println("client:" + i + " connected");
                clients.add(client);

            } catch (IOException e) {
                System.out.println("IOException" + i);
                e.printStackTrace();
            }
        });
        System.out.println("clients.size: " + clients.size());
        //阻塞主线程
        System.in.read();
    }
}
