package org.myself.study.internet.im.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.myself.study.internet.im.protocol.Packet;
import org.myself.study.internet.im.protocol.PacketCodeClient;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
         PacketCodeClient.INSTANCE.encodeNoReturn(byteBuf, packet);
    }
}
