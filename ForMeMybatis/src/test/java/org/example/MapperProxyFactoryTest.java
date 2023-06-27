package org.example;

import org.example.mybatis.binding.MapperRegister;
import org.example.mybatis.session.SqlSession;
import org.example.mybatis.session.SqlSessionFactory;
import org.example.mybatis.session.defaults.DefaultSqlSession;
import org.example.mybatis.session.defaults.DefaultSqlSessionFactory;
import org.example.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapperProxyFactoryTest {
    Logger logger = LoggerFactory.getLogger(MapperProxyFactoryTest.class);
    @Test
    public void test_proxy() {
        MapperRegister mapperRegister = new MapperRegister();
        String packageName = "org.example.mybatis.test.dao";
        mapperRegister.addMappers(packageName);

        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(mapperRegister);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        String result = userDao.queryUserName("1001");
        logger.info("测试结果: {}", result);
    }
}
