package views;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author tmblount
 */
@Entity
@Table(name = "users")
public class User implements Serializable
{
    private static final long serialVersionUID = 86736718389076781L;

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", unique = true)
    private String username;

    @Column
    private String password;

    @Column(name = "email", unique = true)
    private String emailAddress;

    @Column
    private String banned;

    // TODO make work in Hibernate once you get to pets
    //private List<Pet> pets;

    public User() {}

    public User(String username, String emailAddress, String isBanned, List<Pet> pets)
    {
        this.username = username;
        this.emailAddress = emailAddress;
        this.banned = isBanned;
        //this.pets = pets;
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

    /*public List<Pet> getPets()
    {
        return pets;
    }

    public void setPets(List<Pet> pets)
    {
        this.pets = pets;
    }*/

    @Override
    public String toString()
    {
        return "User{" +
                "username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", isBanned=" + banned +
                '}';
    }
}
