package service;

public interface UserPetAdoptionService
{
    public boolean adopt(int petId, int userId, String petNickname);
}
