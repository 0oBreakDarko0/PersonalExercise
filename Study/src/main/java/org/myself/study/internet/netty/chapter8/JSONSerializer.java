package org.myself.study.internet.netty.chapter8;

import com.alibaba.fastjson2.JSON;

public class JSONSerializer implements Serializer{
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        JSON.parseObject(bytes, clazz);
        return JSON.parseObject(bytes, clazz);
    }
}
