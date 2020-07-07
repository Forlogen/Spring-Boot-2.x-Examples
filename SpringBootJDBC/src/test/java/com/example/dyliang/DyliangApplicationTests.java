package com.example.dyliang;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@RunWith(SpringRunner.class)
@SpringBootTest
class DyliangApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    /*
    class com.zaxxer.hikari.HikariDataSource
    HikariProxyConnection@2111669429 wrapping com.mysql.cj.jdbc.ConnectionImpl@73aeef7d
     */

    /*
    class com.alibaba.druid.pool.DruidDataSource
    com.mysql.cj.jdbc.ConnectionImpl@423c5404

     */
}
