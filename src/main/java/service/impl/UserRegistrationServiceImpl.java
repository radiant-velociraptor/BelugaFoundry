package service.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserRegistrationService;
import views.User;

/**
 * @author tmblount
 */
@Service("userRegistrationService")
public class UserRegistrationServiceImpl implements UserRegistrationService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User registerUser(String emailAddress, String username, String password)
    {
        User user = createUserToRegister(emailAddress, username, password);

        Session session = sessionFactory.openSession();

        try
        {
            // TODO -- check that username is unique(? Do on front end? Do both? -- Do both)

            // TODO -- check that emailAddress is unique

            session.save(user);
        }
        catch (HibernateException hibex)
        {
            LOGGER.warn("Error saving user " + emailAddress);
        }
        finally
        {
            session.close();
        }

        return user;
    }

    /**
     * Create a user object for registration with appropriate default fields.
     *
     * @param emailAddress the user's email address
     * @param username the user's username/nickname
     * @param password the user's password
     *
     * @return a new User object to be saved in the db
     */
    private User createUserToRegister(String emailAddress, String username, String password)
    {
        User user = new User();
        user.setEmailAddress(emailAddress);
        user.setUsername(username);
        user.setPassword(password);
        user.setBanned("F");

        return user;
    }
}