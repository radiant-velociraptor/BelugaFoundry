package service.impl;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.PetInfoService;
import views.Pet;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * A service used to get information about pets by pet or user id.
 *
 * @author tmblount
 */
@Service("petInfoService")
public class PetInfoServiceImpl implements PetInfoService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PetInfoServiceImpl.class);

    @Autowired
    private Session readOnlySession;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pet> getAllPets()
    {
        List<Pet> allPets = new ArrayList<>();

        try
        {
            allPets = readOnlySession.createQuery("select pet from Pet pet", Pet.class).getResultList();
        }
        catch (NoResultException norex)
        {
            // It's okay if there are no pets. Just need to know if there's not.
            LOGGER.warn("No pets were loaded when calling getAllPets.");
        }

        return allPets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pet getPetByPetId(int petId)
    {
        Pet pet = null;

        try
        {
           pet = readOnlySession.createQuery("select pet from Pet pet where pet.petId = :petId", Pet.class).setParameter("petId", petId).getSingleResult();
        }
        catch (NoResultException norex)
        {
            LOGGER.debug("The pet with id {} was not found.", petId);
        }

        return pet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pet> getPetsByUserId(int userId)
    {
        List<Pet> userPets = new ArrayList<>();

        try
        {
            userPets = readOnlySession.createQuery("select new Pet(userPet.petId, pet.name, pet.hp, pet.strength, pet.defense, pet.speed, userPet.nickname) from Pet pet, UserPet userPet, User user where (user.id = :userId and userPet.id = :userId and pet.id = userPet.petId)", Pet.class).setParameter("userId", userId).getResultList();
        }
        catch (NoResultException norex)
        {
            LOGGER.debug("The pets owned by the user with id {} was not found.", userId);
        }

        return userPets;
    }
}
