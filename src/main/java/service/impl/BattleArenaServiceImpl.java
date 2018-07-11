package service.impl;

import org.springframework.stereotype.Service;
import service.BattleArenaService;
import views.Action;

@Service("battleArenaService")
public class BattleArenaServiceImpl implements BattleArenaService
{
    @Override
    public void takeAction(Action actionToTake)
    {

    }

    @Override
    public int calculateHP()
    {
        return 0;
    }

    @Override
    public String declareWinner()
    {
        return null;
    }
}
