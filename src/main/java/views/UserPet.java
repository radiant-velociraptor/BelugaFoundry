package views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_pets")
public class UserPet implements Serializable
{
    @Id
    @Column(name = "pet_id")
    private int petId;

    @Id
    @Column
    private int id;

    @Column
    private String nickname;

    public UserPet(int petId, int id, String nickname)
    {
        this.petId = petId;
        this.id = id;
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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }
}
