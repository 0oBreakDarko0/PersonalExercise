package org.myself.study.internet.im.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.myself.study.internet.im.client.handler.ClientHandler;
import org.myself.study.internet.im.client.handler.LoginResponseHandler;
import org.myself.study.internet.im.client.handler.MessageResponseHandler;
import org.myself.study.internet.im.codec.PacketDecoder;
import org.myself.study.internet.im.codec.PacketEncoder;
import org.myself.study.internet.im.protocol.PacketCodeClient;
import org.myself.study.internet.im.protocol.request.MessageRequestPacket;

import java.util.Scanner;

public class NettyClient {
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //ch.pipeline().addLast(new ClientHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketDecoder());
                    }
                });


        connect(bootstrap, "127.0.0.1", 8000, 5);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {

        bootstrap.connect(host, port).addListener(future ->  {
            if (future.isSuccess()) {
                System.out.println("连接成功");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.println("输入消息发送至服务端： ");
                Scanner scanner = new Scanner((System.in));
                String line = scanner.nextLine();
                MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                messageRequestPacket.setMessage(line);

                ByteBuf byteBuf = PacketCodeClient.INSTANCE.encode(channel.alloc().buffer(), messageRequestPacket);
                channel.writeAndFlush(byteBuf);
            }
        }).start();
    }
}
