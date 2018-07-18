package service;

import views.Action;
import views.WinnerView;

public interface BattleArenaService
{
    /**
     * The action to take. Currently only attack is available.
     *
     * @param actionToTake the action to take
     */
    void takeAction(Action actionToTake);

    /**
     * Calculate HP
     *
     * @return the remaining HP
     */
    int calculateHP(int damage);

    /**
     * Declare the winner
     *
     * @param username the username of the winner
     *
     * @return a winner view
     */
    WinnerView declareWinner(String username);
}
