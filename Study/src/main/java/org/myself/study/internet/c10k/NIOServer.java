package org.myself.study.internet.c10k;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class NIOServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        LinkedList<SocketChannel> clients = new LinkedList<>();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9998));
        //设置操作系统级别的ServerSocketChannel为非阻塞
        serverSocketChannel.configureBlocking(false);

        while (true) {
            Thread.sleep(5000);

            /**
             * accept 调用了内核，
             * 在设置configureBlocking(false) 及非阻塞的情况下
             * 若有客户端连进来，直接返回客户端，
             * 若无客户端连接，则返回null
             * 设置成NONBLOCKING后，代码不阻塞，线程不挂起，继续往下执行
             */
            SocketChannel client = serverSocketChannel.accept();

            if (client == null) {
                System.out.println("==== Client Null ====");
            } else {
                /**
                 * 重点，设置client读写数据时非阻塞
                 */
                client.configureBlocking(false);
                int port = client.socket().getPort();
                System.out.println("client..port: " + port);
                clients.add(client);
            }

            ByteBuffer buffer = ByteBuffer.allocate(4096);

            for(SocketChannel c: clients) {
                int num = c.read(buffer);
                if (num > 0) {
                    buffer.flip();
                    byte[] temp = new byte[buffer.limit()];
                    buffer.get(temp);
                    String b = new String(temp);
                    System.out.println(c.socket().getPort() + " : " + b);
                    buffer.clear();
                }
            }
        }
    }
}
