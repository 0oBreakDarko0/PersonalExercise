package org.myself.study.internet.im.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.myself.study.internet.im.protocol.Packet;
import org.myself.study.internet.im.protocol.PacketCodeClient;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Packet decode = PacketCodeClient.INSTANCE.decode(byteBuf);
        list.add(decode);
    }
}
