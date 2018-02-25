package views;

import javax.persistence.*;
import java.util.List;

/**
 * @author tmblount
 */
@Entity
@Table(name = "users")
public class User
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String level;

    @Column
    private String isBanned;

    public User() {}

    public User(String username, String emailAddress, String level, List<Pet> pets)
    {
        this.username = username;
        this.email = emailAddress;
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
        return email;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.email = emailAddress;
    }

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public String isBanned()
    {
        return isBanned;
    }

    public void setBanned(String banned)
    {
        isBanned = banned;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "username='" + username + '\'' +
                ", emailAddress='" + email + '\'' +
                ", level='" + level + '\'' +
                ", isBanned=" + isBanned +
                '}';
    }
}
