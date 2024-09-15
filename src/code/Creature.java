public class Creature
{
    private static final int CREATURE_MAX_HP = 100;
    private static final int CREATURE_MIN_HP = 0;

    private static final int MIN_HEAL_AMOUNT = 0;
    private static final int MIN_HEALTH_AMOUNT = 0;
    private static final int MIN_DAMAGE_AMOUNT = 0;

    private static final int CREATURE_ATTACK_DAMAGE = 5;

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

    public void attack(final Creature targetCreature)
    {
        targetCreature.takeDamage(CREATURE_ATTACK_DAMAGE);

        System.out.printf("%s attacks %s\n", this.name, targetCreature.name);
    }

    private void takeDamage(final int damage)
    {
        this.healthPoints -= damage;

        if(this.healthPoints < MIN_HEALTH_AMOUNT)
        {
            this.healthPoints = MIN_HEALTH_AMOUNT;
        }

        if(damage < MIN_DAMAGE_AMOUNT)
        {
            throw new DamageException("Damage cannot be negative");
        }

    }

    private void heal(final int healAmount)
    {
        validateHealAmount(healAmount);

        this.healthPoints += healAmount;

        if(this.healthPoints > CREATURE_MAX_HP)
        {
            this.healthPoints = CREATURE_MAX_HP;
        }
    }

    private void validateHealAmount(final int healAmount)
    {
        if(healAmount < MIN_HEAL_AMOUNT)
        {
            throw new HealingException("Heal amount must be positive");
        }
    }

    private boolean isAlive()
    {
        return (this.healthPoints <= CREATURE_MIN_HP);
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

    protected void setHealthPoints(final int healthChange)
    {
        this.healthPoints += healthChange;
    }

    public String toString()
    {
        return this.name;
    }


}
