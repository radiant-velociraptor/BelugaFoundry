package web.controller.battle;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.BattleArenaService;
import service.PetInfoService;
import service.UserInfoService;
import views.Pet;
import views.User;
import views.UserBattleInfo;

@Controller
public class BattleArenaController
{
    @Autowired
    private BattleArenaService battleArenaService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PetInfoService petInfoService;

    // TODO create a view for this
    // TODO should include pet info
    @RequestMapping("/attack")
    @RequiresAuthentication
    @ResponseBody
    public String battle(
            @RequestParam(value = "userId", required = true) int userId,
            @RequestParam(value = "petId", required = true) int petId,
            @RequestParam(value = "currentHP", required = true) int currentHP
    )
    {
        User userInBattle = userInfoService.getUserInfoByUserId(userId);

        Pet petInBattle = petInfoService.getPetByPetId(petId);

        UserBattleInfo userBattleInfo = new UserBattleInfo(userInBattle, petInBattle);

        battleArenaService.takeAction(userBattleInfo.getAttackAction());

        return "";
    }
}
