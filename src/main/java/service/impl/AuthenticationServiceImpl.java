package service.impl;

import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import service.AuthenticationService;
import views.AuthenticationView;

/**
 * @author tmblount
 */
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService
{
    @Override
    public AuthenticationView logIn(String username, String password)
    {
        return new AuthenticationView();
    }

    @Override
    public AuthenticationView logout(Subject user)
    {
        return null;
    }
}
