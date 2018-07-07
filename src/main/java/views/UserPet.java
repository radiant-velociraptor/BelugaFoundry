package views;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user_pets")
@Transactional
public class UserPet implements Serializable
{
    @Id
    @Column(name = "user_pets_id")
    private int userPetsId;

    @Column(name = "pet_id")
    private int petId;

    @Column(name = "user_id")
    private int userId;

    @Column
    private String nickname;

    @OneToMany(targetEntity = Pet.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumns(value = {
            @JoinColumn(name = "pet_id", referencedColumnName = "pet_id"),
            @JoinColumn(name = "nickname", referencedColumnName = "nickname")
    })
    private List<Pet> pets;

    public UserPet()
    {
    }

    public UserPet(int petId, int userId, String nickname)
    {
        this.petId = petId;
        this.userId = userId;
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

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public List<Pet> getPets()
    {
        return pets;
    }

    public void setPets(List<Pet> pets)
    {
        this.pets = pets;
    }
}
