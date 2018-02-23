package views;

import javax.persistence.*;
import java.util.List;

/**
 * @author tmblount
 */
@Entity
@Table
public class User
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String emailAddress;

    @Column
    private String level;

    @Column
    private boolean isBanned;

    public User() {}

    public User(String username, String emailAddress, String level, List<Pet> pets)
    {
        this.username = username;
        this.emailAddress = emailAddress;
        this.level = level;
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

    @Override
    public String toString()
    {
        return "User{" +
                "username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", level='" + level + '\'' +
                ", isBanned=" + isBanned +
                '}';
    }
}
