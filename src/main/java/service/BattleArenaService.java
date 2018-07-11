package service;

import views.Action;

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
    int calculateHP();

    /**
     * Declare the winner
     *
     * @return a formatted string declaring the winner
     */
    String declareWinner();
}
