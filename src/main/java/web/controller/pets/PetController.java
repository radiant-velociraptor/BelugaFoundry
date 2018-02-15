package web.controller.pets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tmblount
 */
@Controller
@RequestMapping("/pet")
public class PetController
{
    @RequestMapping(value = "/adopt", method = RequestMethod.POST)
    @ResponseBody
    public String adoptPet(
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "petId", required = true) String petId,
            @RequestParam(value = "petName", required = false) String petName
    )
    {


        return "";
    }
}
