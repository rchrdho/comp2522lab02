public class Creature
{
    private static final int CREATURE_DEFAULT_HP = 100;

    private final String name;
    private final Date   dateOfBirth;
    private int          healthPoints;

    public Creature(final String name, final Date dateOfBirth)
    {
        validateName(name);

        this.name          = name;
        this.dateOfBirth   = dateOfBirth;
        this.healthPoints  = CREATURE_DEFAULT_HP;
    }

    private static void validateName(final String name)
    {
        if(name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("Creature name cannot be null or empty");
        }
    }

}
