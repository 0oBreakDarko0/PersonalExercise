package org.example.mybatis.session.defaults;

import org.example.mybatis.binding.MapperRegister;
import org.example.mybatis.session.SqlSession;

/**
 * @author yunjikeji
 */
public class DefaultSqlSession implements SqlSession {
    /**
     * 映射注册器
     */
    private MapperRegister mapperRegister;

    public DefaultSqlSession() {
    }

    public DefaultSqlSession(MapperRegister mapperRegister) {
        this.mapperRegister = mapperRegister;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + "方法：" + statement);
    }

    @Override
    public <T> T selectOne(String statement, String parameter) {
        return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegister.getMapper(type, this);
    }
}
