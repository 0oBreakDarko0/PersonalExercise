package org.myself.study.internet.im.protocol;

import io.netty.buffer.ByteBuf;
import org.myself.study.internet.im.protocol.request.LoginRequestPacket;
import org.myself.study.internet.im.protocol.request.MessageRequestPacket;
import org.myself.study.internet.im.protocol.response.LoginResponsePacket;
import org.myself.study.internet.im.protocol.response.MessageResponsePacket;
import org.myself.study.internet.im.serilazer.impl.JSONSerializer;
import org.myself.study.internet.im.serilazer.Serializer;


import java.util.HashMap;
import java.util.Map;

import static org.myself.study.internet.im.protocol.command.Command.*;


public class PacketCodeClient {
    public static final int MAGIC_NUMBER = 0x12345678;

    public static final PacketCodeClient INSTANCE = new PacketCodeClient();

    private final Map<Byte, Class<? extends Packet>> packetMap;

    private final Map<Byte, Serializer> serializerMap;

    public PacketCodeClient() {
        packetMap = new HashMap<>();
        packetMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public ByteBuf encode(ByteBuf buf, Packet packet) {

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

    public void encodeNoReturn(ByteBuf buf, Packet packet) {

        //序列化 Java 对象
        byte[] serialize = Serializer.DEFAULT.serialize(packet);

        //实际编码过程
        buf.writeInt(MAGIC_NUMBER);
        buf.writeByte(packet.getVersion());
        buf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        buf.writeByte(packet.getCommand());
        buf.writeInt(serialize.length);
        buf.writeBytes(serialize);

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

    private Class<? extends Packet> getRequestType(Byte command) {
        return packetMap.get(command);
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }
}
