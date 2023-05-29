package org.myself.study.internet.netty.chapter15.serialize.impl;

import com.alibaba.fastjson2.JSON;
import org.myself.study.internet.netty.chapter15.serialize.Serializer;
import org.myself.study.internet.netty.chapter15.serialize.SerializerAlogrithm;

public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}
