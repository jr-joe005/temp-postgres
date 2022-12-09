package jr.temp.postgres;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * DB配置
 */
@Configuration
public class DataSourceConfig {
//
//    @Value("${username}")
//    String username;
//
//    @Value("${password}")
//    String password;

    @Bean
    public DataSource dataSourceOne() {
        return DataSourceBuilder.create()
                .username("testuser")
                .password("testpassword")
                .url("jdbc:postgresql://localhost:5432/test_db")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
