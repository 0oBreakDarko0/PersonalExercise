package org.myself.study.internet.netty.chapter17.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.myself.study.internet.netty.chapter17.protocol.Packet;
import org.myself.study.internet.netty.chapter17.protocol.PacketCodeC;


public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) {
        PacketCodeC.INSTANCE.encode(out, packet);
    }
}
