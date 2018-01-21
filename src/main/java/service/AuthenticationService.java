package service;

import org.apache.shiro.subject.Subject;
import views.AuthenticationView;

/**
 * @author tmblount
 */
public interface AuthenticationService
{
    AuthenticationView logIn(String username, String password);

    AuthenticationView logout(Subject user);
}
