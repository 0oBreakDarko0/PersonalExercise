package org.example.mybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import org.example.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 扫描包路径和映射器代理类注册服务
 * @author yunjikeji
 */
public class MapperRegister {
    private final Map<Class<?>, MapperProxyFactory<?>> knowMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knowMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }

        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public <T> void addMapper(Class<T> type) {
        // 如果是接口，添加到缓存中
        if (type.isInterface()) {
            if (knowMappers.containsKey(type)) {
                // 如果重复添加了，报错
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }

            knowMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    /**
     * 根据包路径扫描并添加到缓存中
     * @param packageName 包路径
     */
    public void addMappers(String packageName) {
        Set<Class<?>> classes = ClassScanner.scanPackage(packageName);
        for (Class<?> targetClass : classes) {
            addMapper(targetClass);
        }
    }
}
