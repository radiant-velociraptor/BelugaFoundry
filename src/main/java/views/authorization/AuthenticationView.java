package views.authorization;

import views.User;

/**
 * @author tmblount
 */
public class AuthenticationView
{
    private String loginMessage = "OK";

    private String userName = "";
    private User user;

    public AuthenticationView()
    {
        userName = "Failed login";
    }

    public AuthenticationView(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setLoginMessage(String loginMessage)
    {
        this.loginMessage = loginMessage;
    }

    public String getLoginMessage()
    {
        return this.loginMessage;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
