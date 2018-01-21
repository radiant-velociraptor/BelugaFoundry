package config.appconfig;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @author tmblount
 */
public class ShiroConfig
{
    @Value("${mysql.db}")
    private String mysqlDb;

    @Value("${mysql.user}")
    private String user;

    @Value("${mysql.password}")
    private String password;

    @Value("${shiro.userrole}")
    private String shiroUserRoleQuery;

    @Bean(name = "dataSource")
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSource.setUrl(mysqlDb);

        dataSource.setUsername(user);

        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean(name = "realm")
    @DependsOn("dataSource")
    public JdbcRealm realm()
    {
        JdbcRealm jdbcRealm = new JdbcRealm();

        // Hash-matching credential-er
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        jdbcRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        jdbcRealm.setUserRolesQuery(shiroUserRoleQuery);

        jdbcRealm.setDataSource(this.dataSource());

        jdbcRealm.init();

        return jdbcRealm;
    }

    @Bean(name = "securityManager")
    @DependsOn("realm")
    public SecurityManager securityManager()
    {
        SecurityManager securityManager = new DefaultWebSecurityManager();

        // Let BelugaFoundry hog the JVM with its SecurityManager
        SecurityUtils.setSecurityManager(securityManager);

        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean()
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // TODO

        return shiroFilterFactoryBean;
    }

    @Bean
    @DependsOn("securityManager")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator()
    {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();

        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);

        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    @DependsOn("securityManager")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor()
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();

        authorizationAttributeSourceAdvisor.setSecurityManager(this.securityManager());

        return authorizationAttributeSourceAdvisor;
    }
}
