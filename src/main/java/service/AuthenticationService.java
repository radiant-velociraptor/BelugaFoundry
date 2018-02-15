package service;

import views.authorization.AuthenticationView;

/**
 * @author tmblount
 */
public interface AuthenticationService
{
    AuthenticationView logIn(String username, String password);

    AuthenticationView logout(String username);
}
