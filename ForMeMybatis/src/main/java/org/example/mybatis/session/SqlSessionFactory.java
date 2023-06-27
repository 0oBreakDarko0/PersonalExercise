package org.example.mybatis.session;

/**
 * @author yunjikeji
 */
public interface SqlSessionFactory {
    /**
     * 打开一个 session
     * @return sql session
     */
    SqlSession openSession();
}
