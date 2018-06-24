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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import service.PetInfoService;
import views.Pet;
import views.Pets;
import views.User;
import views.UserPet;

import javax.annotation.PostConstruct;
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
@EnableTransactionManagement
public class ShiroConfig
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PetInfoService petInfoService;

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

    @Bean
    public SessionFactory sessionFactory() throws ClassNotFoundException
    {
        Properties properties = new Properties();
        // Required for Mysql 5+. Otherwise Hibernate adds type=MyISAM to your queries and explodes.
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.show_sql", "true");
        //properties.put("hibernate.hbm2ddl.auto","update");

        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
        standardServiceRegistryBuilder.applySettings(properties);
        standardServiceRegistryBuilder.applySetting(Environment.DATASOURCE, dataSource);

        MetadataSources metadataSources = new MetadataSources(standardServiceRegistryBuilder.build());
        metadataSources.addAnnotatedClass(User.class);
        metadataSources.addAnnotatedClass(Pet.class);
        metadataSources.addAnnotatedClass(UserPet.class);
        return metadataSources.getMetadataBuilder().build().buildSessionFactory();
    }

    @Bean
    public Session readSession() throws ClassNotFoundException
    {
        Session readOnlySession = sessionFactory().openSession();

        readOnlySession.setDefaultReadOnly(true);

        return readOnlySession;
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
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new
                AuthorizationAttributeSourceAdvisor();

        authorizationAttributeSourceAdvisor.setSecurityManager(this.securityManager());

        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JpaVendorAdapter getJpaVendorAdapter()
    {
        return new HibernateJpaVendorAdapter();
    }

    @PostConstruct
    @Bean
    public Pets createPetsViews()
    {
        return new Pets(petInfoService.getAllPets());
    }
}
