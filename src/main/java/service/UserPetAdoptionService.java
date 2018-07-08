package service;

import views.Pet;

import java.util.List;

public interface UserPetAdoptionService
{
    public List<Pet> adopt(int petId, int userId, String petNickname);
}
