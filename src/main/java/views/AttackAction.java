package views;

import java.util.Random;

public class AttackAction implements Action
{
    @Override
    public int performAction(int ... params)
    {
        if (params.length < 2)
        {
            throw new IllegalArgumentException("Action requires at least two parameters");
        }

        return (params[0] / params[1]) + new Random().nextInt(5);
    }
}
