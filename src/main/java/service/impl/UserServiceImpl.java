package service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;
import views.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * @author tmblount
 */
@Service("userService")
public class UserServiceImpl implements UserService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public User getUserInfo(String emailAddress)
    {
        User user = null;

        try
        {
            // Must define a "User" object to be returned from the query by using the select [objName] from [table] [objName]... syntax
            // because otherwise the TypedQuery won't be able to convert the result into the User object.
            user = entityManager.createQuery("select u from User u where email = '" + emailAddress + "'", User.class).getSingleResult();
        }
        catch (NoResultException norex)
        {
            // This is okay! There might not be a user to return and we will handle it.
        }

        return user;
    }

    @Override
    public User registerUser(String emailAddress, String username, String password)
    {
        return null;
    }
}