package org.myself.study.internet.netty.chapter8;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class PacketCodeClient {
    private static final int MAGIC_NUMBER = 0x12345678;

    public ByteBuf encode(Packet packet) {
        //创建buf对象
        ByteBuf buf = ByteBufAllocator.DEFAULT.ioBuffer();

        //序列化 Java 对象
        byte[] serialize = Serializer.DEFAULT.serialize(packet);

        //实际编码过程
        buf.writeInt(MAGIC_NUMBER);
        buf.writeByte(packet.getVersion());
        buf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        buf.writeByte(packet.getCommand());
        buf.writeInt(serialize.length);
        buf.writeBytes(serialize);

        return buf;
    }

    public Packet decode(ByteBuf buf) {
        //跳过魔数
        buf.skipBytes(4);
        //跳过版本号
        buf.skipBytes(1);
        //序列化算法标识
        byte serializeAlgorithm = buf.readByte();
        //指令
        byte command = buf.readByte();
        //数据长度
        int length = buf.readInt();

        byte[] bytes = new byte[length];
        buf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Class<? extends Packet> getRequestType(int command) {
        return LoginRequestPacket.class;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return Serializer.DEFAULT;
    }
}
