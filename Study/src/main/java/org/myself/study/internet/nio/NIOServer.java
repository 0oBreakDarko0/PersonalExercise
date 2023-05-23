package org.myself.study.internet.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO方式的网络IO服务端
 * @author mapengfei
 */

public class NIOServer {
    public static void main(String[] args) throws IOException {
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        new Thread(() -> {
            try {
                ServerSocketChannel listenerSocketChannel = ServerSocketChannel.open();
                listenerSocketChannel.socket().bind(new InetSocketAddress(8000));
                listenerSocketChannel.configureBlocking(false);
                listenerSocketChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                while (true) {
                    if (serverSelector.select(1) > 0) {
                        Set<SelectionKey> selectionKeys = serverSelector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();

                        while (iterator.hasNext()) {
                            SelectionKey key = iterator.next();
                            try {
                                if (key.isAcceptable()) {
                                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                    clientChannel.configureBlocking(false);
                                    clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                }
                            } finally {
                                iterator.remove();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    if (clientSelector.select(1) > 0) {
                        Set<SelectionKey> selectionKeys = clientSelector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey key = iterator.next();
                            if (key.isReadable()) {
                                try {
                                    SocketChannel clientChannel = ((SocketChannel) key.channel());
                                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                                    clientChannel.read(readBuffer);
                                    readBuffer.flip();
                                    System.out.println(Charset.defaultCharset().newDecoder().decode(readBuffer).toString());
                                } finally {
                                    iterator.remove();
                                    key.interestOps(SelectionKey.OP_READ);
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
