package service.impl;

import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AuthenticationService;
import service.SubjectService;
import views.authorization.AuthenticationView;

/**
 * @author tmblount
 */
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private static final String SUCCESS_OK = "OK";

    @Autowired
    private SubjectService subjectService;

    @Override
    public AuthenticationView logIn(String username, String password)
    {
        return this.attemptLogin(username, password);
    }

    private AuthenticationView attemptLogin(String username, String password)
    {
        AuthenticationView loginView = null;

        String failedLoginMsg = SUCCESS_OK;

        Subject currentUser = subjectService.getLoginSubject();

        // Check if user is authenticated
        if (!currentUser.isAuthenticated())
        {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);

            // Support the "Remember Me" function
            usernamePasswordToken.setRememberMe(true);

            try
            {
                currentUser.login(usernamePasswordToken);

                LOGGER.debug("Login for [{}] was successful.", username);

                // TODO retrieve user info

                return new AuthenticationView(username);
            }
            catch (UnknownAccountException unaex)
            {
                failedLoginMsg = "This username isn't in our system.";
            }
            catch (IncorrectCredentialsException inex)
            {
                failedLoginMsg = "The username/password combination is incorrect.";
            }
            catch (LockedAccountException laex)
            {
                failedLoginMsg = "This account is locked. Contact your admin for more info.";
            }
            catch (AuthenticationException authex)
            {
                failedLoginMsg = "Something went wrong during login. Contact your admin for assistance.";
            }

            loginView = new AuthenticationView(username);

            loginView.setLoginMessage(failedLoginMsg);

            LOGGER.info("Login for [{}] has failed. Error is [{}].", username, failedLoginMsg);
        }

        return loginView;
    }

    @Override
    public AuthenticationView logout(String username)
    {
        // Get the current subject
        Subject currentUser = subjectService.getLoginSubject();

        AuthenticationView logoutView = new AuthenticationView(username);

        logoutView.setLoginMessage("Logout success");

        currentUser.logout();

        return logoutView;
    }
}
