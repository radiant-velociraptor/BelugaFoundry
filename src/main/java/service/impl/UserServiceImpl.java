package service.impl;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import service.UserService;
import views.User;

/**
 * @author tmblount
 */
@Service("userService")
public class UserServiceImpl implements UserService
{
    @Autowired
  //  @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;
    //private AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor;

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Override
    public User getUserInfo(String emailAddress)
    {
       // Criteria criteria = sessionFactory.openSession().createCriteria(User.class);
        //criteria.add(Restrictions.eq("emailaddress", emailAddress));

        //List<User> list = criteria.list();

        //System.out.println(list);

        return new User();
    }
}