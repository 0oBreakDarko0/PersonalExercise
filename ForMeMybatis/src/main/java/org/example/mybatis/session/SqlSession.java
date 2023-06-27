package org.example.mybatis.session;

/**
 * @author yunjikeji
 */
public interface SqlSession {
    /**
     * 根据指定的sql ID获取一条记录的封装对象
     * @param <T> 封装之后的对象类型
     * @param statement sql ID
     * @return 封装之后的对象
     */
    <T> T selectOne(String statement);

    /**
     * 根据指定的sql ID获取一条记录的封装对象，允许传参数
     * @param statement sql ID
     * @param parameter 参数，可能是对象POJO，Map等
     * @return 封装之后的对象
     * @param <T> 封装之后的对象类型
     */
    <T> T selectOne(String statement, String parameter);

    /**
     * 获取Mapper映射器
     * @param type 类型
     * @return Mapper映射器
     * @param <T> 类型
     */
    <T> T getMapper(Class<T> type);
}
