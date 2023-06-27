package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.mybatis.binding.MapperProxyFactory;
import org.example.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MapperProxyFactoryTest {
    Logger logger = LoggerFactory.getLogger(MapperProxyFactoryTest.class);
    @Test
    public void test_proxy() {
        MapperProxyFactory<IUserDao> userDaoProxy = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("org.example.mybatis.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("org.example.mybatis.test.dao.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");

        IUserDao userDao = userDaoProxy.newInstance(sqlSession);
        String res = userDao.queryUserName("10001");
        logger.info("测试结果：{}", res);
    }
}
