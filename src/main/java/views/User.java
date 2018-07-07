package views;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @author tmblount
 */
@Entity
@Table(name = "users")
@Transactional
public class User implements Serializable
{
    private static final long serialVersionUID = 86736718389076781L;

    @Id
    @Column(name = "user_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "username", unique = true)
    private String username;

    @Column
    private String password;

    @Column(name = "email", unique = true)
    private String emailAddress;

    @Column
    private String banned;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "User_Pets",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "pet_id")
            }
    )
    private List<Pet> pets;

    public User() {}

    public User(String username, String emailAddress, String isBanned, List<Pet> pets)
    {
        this.username = username;
        this.emailAddress = emailAddress;
        this.banned = isBanned;
        this.pets = pets;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getBanned()
    {
        return banned;
    }

    public void setBanned(String banned)
    {
        this.banned = banned;
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
                ", password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", banned='" + banned + '\'' +
                ", pets=" + pets +
                '}';
    }
}
