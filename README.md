# temp-postgres
#### 该项目主要处理内容：
  1. 在Spring Boot项目中，通过SSL连接到Postgresql数据库。<br/>
    ** 连接到DB的认证方式：账户+SSL证书<br/>
  2. 在Spring Boot项目中，通过HashiCrop Vault，获取连接Postgresql数据库需要的账号密码，然后连接到Postgresql数据库。<br/>
    ** 使用DataSourceConfig.java手动配置DB数据源，在代码中通过Vault获取到连接DB用的账户密码，连接到DB。<br/>
    ** 使用Vault的pom依赖项为：spring-vault-core

  ** 具体说明文档，见document/备注.md
