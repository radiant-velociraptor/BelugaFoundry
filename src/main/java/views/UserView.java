package views;

import java.util.ArrayList;
import java.util.List;

/**
 * A view encapsulating some useful user info.
 */
public class UserView
{
    private String emailAddress;
    private String username;
    private List<Pet> pets;
    // private Inventory inventory; // TODO
    private boolean isBanned;
    // private Level level; // TODO enumeration? MySQL?
    private boolean isRegistered = true;

    public UserView()
    {

    }

    public UserView(User user)
    {
        this.emailAddress = user.getEmailAddress();
        this.username = user.getUsername();
        this.pets = new ArrayList<>();
        //this.pets = user.getPets();
        this.isBanned = Boolean.valueOf(user.getBanned());
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public List<Pet> getPets()
    {
        return pets;
    }

    public void setPets(List<Pet> pets)
    {
        this.pets = pets;
    }

    public boolean isBanned()
    {
        return isBanned;
    }

    public void setBanned(boolean banned)
    {
        isBanned = banned;
    }

    public boolean isRegistered()
    {
        return isRegistered;
    }

    public void setRegistered(boolean registered)
    {
        isRegistered = registered;
    }

    @Override
    public String toString()
    {
        return "UserView{" +
                "emailAddress='" + emailAddress + '\'' +
                ", username='" + username + '\'' +
                ", pets=" + pets +
                ", isBanned=" + isBanned +
                '}';
    }
}
