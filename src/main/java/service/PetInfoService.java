package service;

import views.Pet;

import java.util.List;

public interface PetInfoService
{
    /**
     * Get all available pets.
     *
     * @return a list of all available pets
     */
    List<Pet> getAllPets();

    /**
     * Get a pet by the pet id
     *
     * @param petId the id of the pet to get
     *
     * @return the pet corresponding to the petId
     */
    Pet getPetByPetId(int petId);

    /**
     * Get a list of pets owned by a user corresponding to the userId.
     *
     * @param userId the user id
     *
     * @return a list of pets belonging to this user
     */
    List<Pet> getPetsByUserId(int userId);
}
