package views;

import java.util.List;

/**
 * @author tmblount
 */
public class User
{
    private String username;
    private String emailAddress;
    private String level;
    private boolean isBanned;
    private List<Pet> pets;

    public User(String username, String emailAddress, String level, List<Pet> pets)
    {
        this.username = username;
        this.emailAddress = emailAddress;
        this.level = level;
        this.pets = pets;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public boolean isBanned()
    {
        return isBanned;
    }

    public void setBanned(boolean banned)
    {
        isBanned = banned;
    }

    public List<Pet> getPets()
    {
        return pets;
    }

    public void setPets(List<Pet> pets)
    {
        this.pets = pets;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", level='" + level + '\'' +
                ", isBanned=" + isBanned +
                ", pets=" + pets +
                '}';
    }
}
