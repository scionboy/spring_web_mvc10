package com.spring.webmvc.config;


// db 접속 관련 설정 (접속, 커넥션풀....)

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db_info.properties")
public class DataBaseConfig {
    // 이대로 깃헙에 올리면 db아이디 비번이 다 노출되니 이렇게 하자
    // resources 폴더 아래에  db_info.properties 파일 만들기
    // 위에 어노테이션에 properts...추가하기

    @Value("${local.db.username}")
    private String username;

    @Value("${local.db.password}")
    private String password;

    @Value("${local.db.url}")
    private String url;

    @Bean
    public DataSource dataSource() {
        // 스프링에서 공식적으로 채택한 커넥션풀
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setJdbcUrl(url);
        config.setDriverClassName("org.mariadb.jdbc.Driver");

        return new HikariDataSource(config);

    }

    // 이제 git에 올릴떄 db_info.properties는 안올리면 됨.
    // .gitignore에 /src/main/resources/db_info.properties 넣어주면 됨 ( git올릴떄 제외한다는뜻)

}
