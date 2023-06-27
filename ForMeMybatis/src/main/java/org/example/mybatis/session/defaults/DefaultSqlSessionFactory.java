package org.example.mybatis.session.defaults;

import org.example.mybatis.binding.MapperRegister;
import org.example.mybatis.session.SqlSession;
import org.example.mybatis.session.SqlSessionFactory;

/**
 * @author yunjikeji
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final MapperRegister mapperRegister;

    public DefaultSqlSessionFactory(MapperRegister mapperRegister) {
        this.mapperRegister = mapperRegister;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegister);
    }
}
