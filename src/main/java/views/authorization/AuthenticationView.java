package views.authorization;

/**
 * @author tmblount
 */
public class AuthenticationView
{
    private String loginMessage = "OK";

    private String userName = "";

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
}
