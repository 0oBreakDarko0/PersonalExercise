package org.myself.study.internet.netty.chapter6;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 *
 * @author mapengfei
 */
public class NettyClient {
    public static void main(String[] args) {
        //声明客户端引导器
        Bootstrap bootstrap = new Bootstrap();
        //线程模型
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                //指定IO模型
                .channel(NioSocketChannel.class)
                //客户端向服务端写入数据处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new FirstClientHandler());
                    }
                });

        bootstrap.connect("127.0.0.1", 8000).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("连接成功");
                } else {
                    System.out.println("连接失败");
                }
            }
        });
    }
}
