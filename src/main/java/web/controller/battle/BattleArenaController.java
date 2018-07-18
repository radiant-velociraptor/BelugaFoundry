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
import views.*;

@Controller
public class BattleArenaController
{
    @Autowired
    private BattleArenaService battleArenaService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PetInfoService petInfoService;

    /**
     * Calculate attack
     *
     * @param userId the user id
     * @param petId the pet id
     * @param opponentPetDef the opponent's pet's defense
     *
     * @return a damage view with the damage caused to the opponent
     */
    @RequestMapping("/calculateAttkDamage")
    @RequiresAuthentication
    @ResponseBody
    public DamageView attack(
            @RequestParam(value = "userId", required = true) int userId,
            @RequestParam(value = "petId", required = true) int petId,
            @RequestParam(value = "opponentPetDef", required = true) int opponentPetDef
    )
    {
        User userInBattle = userInfoService.getUserInfoByUserId(userId);

        Pet petInBattle = petInfoService.getPetByPetId(petId);

        UserBattleInfo userBattleInfo = new UserBattleInfo(userInBattle, petInBattle);

        return new DamageView(userBattleInfo.attack(opponentPetDef));
    }

    /**
     * Declare a winner
     *
     * @param username the username of the winner
     *
     * @return a winner view with the winner's name
     */
    @RequestMapping("/winner")
    @RequiresAuthentication
    @ResponseBody
    public WinnerView win(
            @RequestParam(value = "username", required = true) String username
    )
    {
        return new WinnerView(username);
    }
}
