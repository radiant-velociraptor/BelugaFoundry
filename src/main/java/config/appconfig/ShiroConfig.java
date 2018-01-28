package config.appconfig;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tmblount
 */
@Configuration
@PropertySource("classpath:mysql.properties")
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
        // TODO
        //HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        SimpleCredentialsMatcher simpleCredentialsMatcher = new SimpleCredentialsMatcher();

        jdbcRealm.setCredentialsMatcher(simpleCredentialsMatcher);

        jdbcRealm.setUserRolesQuery(shiroUserRoleQuery);

        jdbcRealm.setDataSource(this.dataSource());

        jdbcRealm.init();

        return jdbcRealm;
    }

    @Bean(name = "securityManager")
    @DependsOn("realm")
    public SecurityManager securityManager()
    {
        // Define new security manager and tell it which realm to use
        SecurityManager securityManager = new DefaultSecurityManager(this.realm());

        // Let BelugaFoundry hog the JVM with its SecurityManager
        SecurityUtils.setSecurityManager(securityManager);

        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean()
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        Map<String, String> filters = new HashMap<>();
        filters.put("/login", "authc, roles[guest]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filters);

        shiroFilterFactoryBean.setSecurityManager(this.securityManager());

        shiroFilterFactoryBean.setLoginUrl("/login");

        Map<String, Filter> filters2 = new HashMap<>();
        filters2.put("anon", new AnonymousFilter());
        filters2.put("authc", new FormAuthenticationFilter());
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/logout");
        filters2.put("logout", logoutFilter);

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

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
