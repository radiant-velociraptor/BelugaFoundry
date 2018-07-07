package views;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author tmblount
 */
@Entity
@Table(name = "pets")
@Transactional
public class Pet
{
    @Id
    @Column(name = "pet_id")
    private int petId;

    @Column(name = "name")
    private String name;

    @Column(name = "hp")
    private int hp;

    @Column(name = "strength")
    private int strength;

    @Column(name = "defense")
    private int defense;

    @Column(name = "speed")
    private int speed;

    @Transient
    private String nickname;

    @ManyToMany(mappedBy = "pets", cascade = CascadeType.ALL)
    private List<User> users;

    public Pet()
    {

    }

    public Pet(int id, String name, int hp, int strength, int defense, int speed, String nickname)
    {
        this.petId = id;
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.defense = defense;
        this.speed = speed;
        this.nickname = nickname;
    }

    public int getPetId()
    {
        return petId;
    }

    public void setPetId(int petId)
    {
        this.petId = petId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getHp()
    {
        return hp;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public int getStrength()
    {
        return strength;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public int getDefense()
    {
        return defense;
    }

    public void setDefense(int defense)
    {
        this.defense = defense;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    @Override
    public String toString()
    {
        return "Pet{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", strength=" + strength +
                ", defense=" + defense +
                ", speed=" + speed +
                ", nickname='" + nickname +
                '}';
    }
}
