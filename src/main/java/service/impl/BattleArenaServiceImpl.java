package service.impl;

import org.springframework.stereotype.Service;
import service.BattleArenaService;
import views.Action;
import views.WinnerView;

@Service("battleArenaService")
public class BattleArenaServiceImpl implements BattleArenaService
{
    @Override
    public void takeAction(Action actionToTake)
    {
        actionToTake.performAction();
    }

    @Override
    public int calculateHP(int damage)
    {
        return -damage;
    }

    @Override
    public WinnerView declareWinner(String username)
    {
        return new WinnerView(username);
    }
}
