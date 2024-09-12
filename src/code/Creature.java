public class Creature
{
    private static final int CREATURE_DEFAULT_HP = 100;
    private static final int CREATURE_MIN_HP     = 0;

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

    private boolean isAlive()
    {
        return (this.healthPoints <= CREATURE_MIN_HP);
    }

    private void takeDamage(final int damage)
    {
        this.healthPoints -= damage;

        if(this.healthPoints < 0)
        {
            this.healthPoints = 0;
        }

        if(damage < 0)
        {
            throw new DamageException("Damage cannot be negative");
        }

    }

}
