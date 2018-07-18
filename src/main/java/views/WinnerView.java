package views;

public class WinnerView
{
    private String winner;

    private WinnerView() {}

    public WinnerView(String username)
    {
        this.winner = username;
    }

    public String getWinner()
    {
        return winner;
    }
}
