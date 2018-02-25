package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;
import views.User;

import javax.persistence.EntityManager;

/**
 * @author tmblount
 */
@Service("userService")
public class UserServiceImpl implements UserService
{
    @Autowired
    private EntityManager entityManager;

    @Override
    public User getUserInfo(String emailAddress)
    {
        // Must define a "User" object to be returned from the query by using the select [objName] from [table] [objName]... syntax
        // because otherwise the TypedQuery won't be able to convert the result into the User object.
        return entityManager.createQuery("select u from User u where email = '" + emailAddress + "'", User.class).getSingleResult();
    }
}