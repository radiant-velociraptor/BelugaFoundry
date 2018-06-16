package immutable;

public enum Level
{
    Sock("A stockaded fool watches from the sidelines."),
    Neophyte("An awkward, bright-eyed character appears on the scene!"),
    Tamer("A person stands at the center, proud of their power."),
    Paladin("A powerful figure enters their midst, astride a shining steed."),
    Wizard("An enigmatic figure observes from the sidelines, missing nothing."),
    Satori("A bespectacled creature looks upon the riff-raff."),
    Zen("A wizened warrior stands at attention.");

    private String levelDesc;

    Level(String levelDesc)
    {
        this.levelDesc = levelDesc;
    }

    public String getLevelDesc()
    {
        return this.levelDesc;
    }
}
