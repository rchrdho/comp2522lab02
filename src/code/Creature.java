public class Creature
{
    private static final int CREATURE_MAX_HP = 100;
    private static final int CREATURE_MIN_HP     = 0;

    private final String name;
    private final Date   dateOfBirth;
    private int          healthPoints;

    public Creature(final String name, final Date dateOfBirth)
    {
        validateName(name);

        this.name          = name;
        this.dateOfBirth   = dateOfBirth;
        this.healthPoints  = CREATURE_MAX_HP;
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

    private void heal(final int healAmount)
    {
        this.healthPoints += healAmount;

        if(this.healthPoints > CREATURE_MAX_HP)
        {
            this.healthPoints = CREATURE_MAX_HP;
        }

        if(healAmount < 0)
        {
            throw new HealingException("Heal amount must be positive");
        }
    }

    public int getAgeYears()
    {
        return this.dateOfBirth.getYear() - this.dateOfBirth.getMonth();
    }

    public void getDetails()
    {
        final StringBuilder sb;

        sb = new StringBuilder();

        sb.append(this.name);
        sb.append(" HP: ");
        sb.append(this.healthPoints);
        sb.append(" Years: ");
        sb.append(this.getAgeYears());
        System.out.println(sb);
    }

}
