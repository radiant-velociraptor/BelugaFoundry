package service.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

import java.util.ArrayList;
import java.util.List;

@Service("userPetAdoptionService")
public class UserPetAdoptionServiceImpl implements UserPetAdoptionService
{
    public static final int MAX_PETS = 10;

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
    public List<Pet> adopt(int petId, int userId, String petNickname)
    {
        User adopter = null;

        try
        {
            adopter = (User) writeOnlySession.get(User.class, new Integer(userId));

            if (adopter.getPets().size() < MAX_PETS)
            {
                // In order to save something to the db, you need to open a transaction.
                writeOnlySession.beginTransaction();

                Pet adopted = writeOnlySession.get(Pet.class, new Integer(petId));

                adopted.setNickname(petNickname);

                adopter.getPets().add(adopted);

                // Re-use a session object -- don't spin up a new one or save/update doesn't work!
                writeOnlySession.saveOrUpdate(adopter);

                writeOnlySession.getTransaction().commit();
            }
            else
            {
                LOGGER.info("User " + adopter.getEmailAddress() + " has the max number of allowed pets.");
            }
        }
        catch (HibernateException hibex)
        {
            LOGGER.warn("Error saving pets on user " + adopter.getEmailAddress());
        }

        if (adopter != null)
        {
            return adopter.getPets();
        }
        else
        {
            return new ArrayList<>();
        }
    }
}
