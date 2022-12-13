package jr.temp.postgres;

import jr.temp.postgres.cmn.dto.CredentialDto;
import jr.temp.postgres.cmn.dto.Secrets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;

import javax.sql.DataSource;
import java.net.URI;

/**
 * DB配置
 */
@Slf4j
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

        //指定vault服务的地址，这里需要在本地启动vault服务，这里启动的只是开发模式
        URI uri = URI.create("http://192.168.3.137:8200");
        /* 还有就是这里的参数不能直接new VaultEndpoint(),然后再设置host或者port啥的，
         因为VaultEndpoint默认的scheme为https,然后本地的服务是http的，所以访问不了的。 */
        VaultTemplate vaultTemplate = new VaultTemplate(VaultEndpoint.from(uri),
                //这里填入自己的token，因为是开发模式，所以这里填入的是root token，后面的生产环境可以进行权限细分
                new TokenAuthentication("hvs.bs0cWkGUyNQXQUJqZiOTS0hE"));

        /*
         * 这里有个大坑，开发者模式下默认是有一个“/secret”的path的，这个path用通用的读写指令"write/read"都不行，其他path
         * 都可以，在终端也是一样，需要用"kv put"和"kv get"才行，暂时不知道为什么.
         */
        VaultResponseSupport<CredentialDto> response = vaultTemplate.read("database/creds/dbuser", CredentialDto.class);
        username = response.getData().getUsername();
        password = response.getData().getPassword();
        log.info("---- [DataSourceConfig] -- username: {}", username);
        log.info("---- [DataSourceConfig] -- password: {}", password);
        return DataSourceBuilder.create()
                .username("v-root-dbuser-bKzg5qn6VUQB88JkgEDr-1670900995")
                //.username(username)
                .password("2ZykZhk-90-wyiy2YMSa")
                //.password(password)
                .url("jdbc:postgresql://localhost:5432/test_db")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
