package jr.temp.postgres;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;

import javax.sql.DataSource;
import java.net.URI;

/**
 * DB配置
 */
@Configuration
public class DataSourceConfig {

    /** database.test_db.dbuser.username */
    // @Value("${username}")
    //String username;
    /** database.test_db.dbuser.password */
    //@Value("${password}")
    //String password;

    @Bean
    public DataSource dataSourceOne() {

        String username = "";
        String password = "";

        /* 参照URL：https://blog.csdn.net/qq_35746632/article/details/123815142 */
        //指定vault服务的地址，这里需要在本地启动vault服务，这里启动的只是开发模式
        URI uri = URI.create("http://192.168.3.67:8200");
        /** 还有就是这里的参数不能直接new VaultEndpoint(),然后再设置host或者port啥的，
         因为VaultEndpoint默认的scheme为https,然后本地的服务是http的，所以访问不了的。 **/
        VaultTemplate vaultTemplate = new VaultTemplate(VaultEndpoint.from(uri),
                //这里填入自己的token，因为是开发模式，所以这里填入的是root token，后面的生产环境可以进行权限细分
                new TokenAuthentication("s.ThO3hz9Faq0oRCC6LlVQdMAF"));

        return DataSourceBuilder.create()
                .username("testuser")
                //.username(username)
                .password("testpassword")
                //.password(password)
                .url("jdbc:postgresql://localhost:5432/test_db")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
