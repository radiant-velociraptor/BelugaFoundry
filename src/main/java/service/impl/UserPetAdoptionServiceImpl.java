package service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import service.PetInfoService;
import service.UserInfoService;
import service.UserPetAdoptionService;
import views.Pet;
import views.User;

@Service("userPetAdoptionService")
public class UserPetAdoptionServiceImpl implements UserPetAdoptionService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPetAdoptionServiceImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier("writeOnlySession")
    private Session writeOnlySession;

    @Autowired
    private PetInfoService petInfoService;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public boolean adopt(int petId, int userId, String petNickname)
    {
        Pet adopted = petInfoService.getPetByPetId(petId);

        adopted.setNickname(petNickname);

        User adopter = userInfoService.getUserInfoByUserId(userId);

        adopter.getPets().add(adopted);

        adopter.setEmailAddress("Mel@Test.com");

        Session session = sessionFactory.openSession();

        try
        {
            Transaction tx = writeOnlySession.beginTransaction();

            adopted = (Pet) writeOnlySession.get(Pet.class, new Integer(petId));

            adopter = (User) writeOnlySession.get(User.class, new Integer(userId));

            adopter.getPets().add(adopted);

            writeOnlySession.saveOrUpdate(adopter);

            //session.save(adopter);

            writeOnlySession.getTransaction().commit();
        }
        catch (Exception ex)
        {

        }

        return false;
    }
}
