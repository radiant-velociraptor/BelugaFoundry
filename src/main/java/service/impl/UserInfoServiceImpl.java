package service.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserInfoService;
import views.User;

import javax.persistence.NoResultException;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService
{
    @Autowired
    private Session readOnlySession;

    @Override
    public User getUserInfo(String emailAddress)
    {
        User user = null;

        try
        {
            // Must define a "User" object to be returned from the query by using the select [objName] from [table] [objName]... syntax
            // because otherwise the TypedQuery won't be able to convert the result into the User object.
            user = readOnlySession.createQuery("select u from User u where email = '" + emailAddress + "'", User.class).getSingleResult();
        }
        catch (NoResultException norex)
        {
            // This is okay! There might not be a user to return and we will handle it.
        }

        return user;
    }
}
