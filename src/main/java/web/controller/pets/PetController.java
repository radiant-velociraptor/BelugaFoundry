package web.controller.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.PetInfoService;
import service.UserPetAdoptionService;
import views.Pet;

import java.util.List;

/**
 * @author tmblount
 */
@Controller
@RequestMapping("/pet")
public class PetController
{
    @Autowired
    private PetInfoService petInfoService;

    @Autowired
    private UserPetAdoptionService userPetAdoptionService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Pet> getAll()
    {
        return petInfoService.getAllPets();
    }

    @RequestMapping(value = "/byId", method = RequestMethod.GET)
    @ResponseBody
    public Pet getPetById(
            @RequestParam(value = "petId", required = true) int petId
    )
    {
        return petInfoService.getPetByPetId(petId);
    }

    @RequestMapping(value = "/user/pets", method = RequestMethod.GET)
    @ResponseBody
    public List<Pet> getUserPetsByUserId(
            @RequestParam(value = "userId", required = true) int userId
    )
    {
        return petInfoService.getPetsByUserId(userId);
    }

    @RequestMapping(value = "/adopt", method = RequestMethod.POST)
    @ResponseBody
    public String adoptPet(
            @RequestParam(value = "userId", required = true) int userId,
            @RequestParam(value = "petId", required = true) int petId,
            @RequestParam(value = "petName", required = false) String petName
    )
    {
        userPetAdoptionService.adopt(petId, userId, petName);

        return "";
    }
}
