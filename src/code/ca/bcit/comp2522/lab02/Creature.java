package ca.bcit.comp2522.lab02;

/**
 * The Creature class models a basic creature with a name, date of birth, and health points (HP).
 * It provides methods to handle damage, healing, and basic stats information.
 *
 * @author Bryson Lindy
 * @author Phyo Thu Kha
 * @author Richard Ho
 * @version 1.0
 */
public abstract class Creature
{
    // Constants to define health boundaries for creatures.
    private static final int CREATURE_MAX_HP = 500;
    private static final int CREATURE_MIN_HP = 0;

    // Constants for validation of heal and damage amounts.
    private static final int MIN_HEALTH_AMOUNT = 0;
    private static final int MIN_DAMAGE_AMOUNT = 0;

    private static final int MIN_HEAL_AMOUNT   = 0;

    private static final int HEAL_UPPER_BOUND = 100;
    private static final int HEAL_OFFSET      = 10;
    private static final int RANDOM_OFFSET    = 1;

    // Creature's unique attributes.
    private final String name;
    private final Date   dateOfBirth;
    private int          healthPoints;

    /**
     * Constructs a new Creature with a specified name and date of birth.
     * The creature is instanced with {@value CREATURE_MAX_HP} health.
     *
     * @param name The name of the creature.
     * @param dateOfBirth The creature's date of birth.
     * @throws IllegalArgumentException If the name is null or empty.
     */
    public Creature(final String name,
                    final Date dateOfBirth,
                    final int healthPoints)
        throws IllegalArgumentException
    {
        validateName(name);
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.healthPoints = validateHealthPoints(healthPoints);
    }

    /**
     * Validates that the name is not null or empty.
     *
     * @param name The name to validate.
     * @throws IllegalArgumentException If the name is invalid.
     */
    private static void validateName(final String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Creature name cannot be null or empty");
        }
    }

    /*
    Validates health points are a positive value.
     */
    private static int validateHealthPoints(final int healthPoints)
    {
        if (healthPoints <= CREATURE_MIN_HP) {
            throw new IllegalArgumentException("Health points must be greater than 0.");
        }
        return healthPoints;
    }

    /**
     * Abstract method representing a Creature's attack. To be implemented according to a subclass requirements.
     * @param targetCreature creature targeted by attack
     * @throws Exception subclass implements custom Exception depending on resource
     */
    abstract void attack(Creature targetCreature) throws Exception;

    /**
     * Reduces the creature's health points by the specified damage amount.
     * If damage is negative, throws an exception. Health cannot be negative.
     *
     * @param damage The amount of damage to apply.
     * @throws DamageException If the damage amount is negative.
     */
    void takeDamage(final int damage)
    {
        if (damage < MIN_DAMAGE_AMOUNT)
        {
            throw new DamageException("Damage cannot be negative");
        }

        /* Ensure health doesn't drop below MIN_HEALTH_AMOUNT by selecting the larger between
        (current health - incoming damage) OR MIN_HEALTH_AMOUNT */
        this.healthPoints = Math.max(this.healthPoints - damage, MIN_HEALTH_AMOUNT);
    }

    /**
     * Heals the creature by the specified amount.
     * Health cannot exceed {@value CREATURE_MAX_HP}.
     *
     * @throws HealingException If the heal amount is invalid.
     */
    void heal(final Creature target) throws HealingException
    {
        int healAmount;

        healAmount = (int)(Math.random() * (HEAL_UPPER_BOUND - HEAL_OFFSET + RANDOM_OFFSET)) + HEAL_OFFSET;
        validateHealAmount(healAmount);

        // choose the lesser of (current health points + heal amount) OR CREATURE_MAX_HP
        this.healthPoints = Math.min(this.healthPoints + healAmount, CREATURE_MAX_HP);

        System.out.printf("%s heals %s for %d.\n",
                this.getName(),
                target.getName(),
                healAmount);
    }

    /*
     * Validates the heal amount to ensure it is not negative. Otherwise, throws Exception.
     */
    private static void validateHealAmount(final int healAmount) throws HealingException
    {
        if (healAmount < MIN_HEAL_AMOUNT)
        {
            throw new HealingException("Heal amount must be positive");
        }
    }

    /**
     * Checks if the creature is still alive (HP greater than 0).
     *
     * @return true if the creature is alive, false otherwise.
     */
    final boolean isAlive()
    {
        return this.healthPoints > CREATURE_MIN_HP;
    }

    /**
     * Retrieves the name of the creature.
     *
     * @return The creature's name.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Retrieves the creature's age in years based on its birthdate.
     * Note: This is a simplified age calculation based on year and month difference.
     *
     * @return The creature's age in years.
     */
    public int getAgeYears()
    {
        return this.dateOfBirth.getYear() - this.dateOfBirth.getMonth();
    }

    /**
     * Retrieves the current health points of the creature.
     *
     * @return The current health points.
     */
    public int getHealthPoints()
    {
        return this.healthPoints;
    }

    /**
     * Displays details about the creature, including its name, health, and age.
     */
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

    /**
     * Sets the creature's health points to a specified value.
     * This method should be used cautiously as it directly manipulates HP.
     *
     * @param healthAmount The amount to set the creature's health to.
     */
    protected final void setHealthPoints(final int healthAmount)
    {
        this.healthPoints = healthAmount;
    }

    /**
     * Returns a string representation of the creature (its name).
     *
     * @return The name of the creature.
     */
    @Override
    public String toString()
    {
        return this.name;
    }
}
