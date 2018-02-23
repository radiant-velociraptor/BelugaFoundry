package config.appconfig;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.hibernate.SessionFactory;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author tmblount
 */
@Configuration
@PropertySource("classpath:shiro.properties")
@Import({DatabaseConfig.class})
public class ShiroConfig
{
    @Autowired
    private DataSource dataSource;

    @Value("${shiro.userrole}")
    private String shiroUserRoleQuery;

    @Value("${shiro.auth}")
    private String shiroAuthQuery;

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

        jdbcRealm.setAuthenticationQuery(shiroAuthQuery);

        jdbcRealm.setDataSource(dataSource);

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

    @Bean(name = "hibernateFactory")
    @DependsOn("dataSource")
    public LocalSessionFactoryBean hibernateFactory()
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(dataSource);

        sessionFactory.setPackagesToScan("{views}");

        sessionFactory.setHibernateProperties(additionalProperties());

        return sessionFactory;
    }

    @Bean
    @DependsOn("hibernateFactory")
    public SessionFactory sessionFactory()
    {
        return hibernateFactory().getObject();
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

    Properties additionalProperties()
    {
        Properties properties = new Properties();

        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        return properties;
    }
}
