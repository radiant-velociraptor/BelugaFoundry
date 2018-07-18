package views;

public class UserBattleInfo
{
    private User userInBattle;

    private Pet petInBattle;

    private Action attackAction = new AttackAction();

    private UserBattleInfo() {}

    public UserBattleInfo(User userInBattle, Pet petInBattle)
    {
        this.userInBattle = userInBattle;

        this.petInBattle = petInBattle;
    }

    public int attack(int opponentPetDef)
    {
        return attackAction.performAction(petInBattle.getStrength(), opponentPetDef);
    }
}
