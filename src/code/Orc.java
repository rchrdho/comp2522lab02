/**
 * The Orc class is a subclass of Creature, representing a specific type of creature with unique attributes such as rage.
 * Orcs have higher maximum HP than regular creatures and a special "berserk" attack that increases in damage as their rage builds.
 */
public class Orc extends Creature
{
    // Orc-specific constants for health and attack properties.
    private static final int ORC_MAX_HP              = 150;
    private static final int ORC_ATTACK_DAMAGE       = 15;
    private static final String ORC_ATTACK_NAME      = "berserk";

    // Orc-specific rage attributes to control damage escalation.
    private static final int ORC_MIN_RAGE            = 0;
    private static final int ORC_RAGE_INCREMENT      = 5;
    private static final int RAGE_BONUS_TIER_ONE     = 15;
    private static final int RAGE_BONUS_TIER_TWO     = 25;
    private static final int RAGE_DOUBLE_DAMAGE      = 2;
    private static final int RAGE_TRIPLE_DAMAGE      = 3;
    private static final int BERSERK_MIN_RAGE        = 5;

    private static final int MEDITATE_RAGE_REDUCTION = 10;

    // Orc's rage level
    private int rage;

    /**
     * Constructs a new Orc with a specified name and birthdate. The Orc starts with {@value ORC_MAX_HP} health and {@value ORC_MIN_RAGE} rage.
     *
     * @param name The name of the Orc.
     * @param birthday The Orc's birthdate.
     * @throws IllegalArgumentException If name is null or empty
     */
    public Orc(final String name,
               final Date birthday)
            throws IllegalArgumentException
    {
        super(name, birthday, ORC_MAX_HP);
        this.rage = ORC_MIN_RAGE;
    }

    /**
     * Overrides the default takeDamage to add the unique Orc feature of incrementing
     * rage by {@value ORC_RAGE_INCREMENT} whenever it takes damage.
     *
     * @param damage The amount of damage to apply.
     */
    @Override
    void takeDamage(final int damage)
    {
        super.takeDamage(damage);
        this.rage += ORC_RAGE_INCREMENT;
    }

    /**
     * Executes the Orc's berserk attack on the target creature.
     * If the Orc healthpoints == 0, it cannot act and a message will be printed instead.
     * As the Orc attacks, its rage increases. If the rage reaches or exceeds {@value RAGE_BONUS_TIER_ONE},
     * the attack deals {@value RAGE_DOUBLE_DAMAGE} times damage.
     *
     * @param targetCreature The creature that is being attacked.
     * @throws LowRageException if rage is less than {@value BERSERK_MIN_RAGE}
     */
    void attack(final Creature targetCreature)
            throws LowRageException
    {
        if(this.isAlive())
        {
            try {
                this.validateRage();

                rage += ORC_RAGE_INCREMENT; // Increase rage after each attack.

                if (rage < RAGE_BONUS_TIER_ONE) // "normal" attack
                {
                    targetCreature.takeDamage(ORC_ATTACK_DAMAGE);
                    System.out.printf("%s attacks %s with %s for %d damage\n",
                            this.getName(),
                            targetCreature.getName(),
                            ORC_ATTACK_NAME,
                            ORC_ATTACK_DAMAGE);
                } else if (rage <= RAGE_BONUS_TIER_TWO) // rage boosted attack first tier
                {
                    targetCreature.takeDamage(ORC_ATTACK_DAMAGE * RAGE_DOUBLE_DAMAGE);

                    System.out.printf("%s is enraged and dealing double damage!\n", this.getName());
                    System.out.printf("%s attacks %s with %s for %d damage\n",
                            this.getName(),
                            targetCreature.getName(),
                            ORC_ATTACK_NAME,
                            ORC_ATTACK_DAMAGE * RAGE_DOUBLE_DAMAGE);
                } else // rage boosted attack second tier (costs RAGE_BONUS_TIER_TWO rage)
                {
                    targetCreature.takeDamage(ORC_ATTACK_DAMAGE * RAGE_TRIPLE_DAMAGE);
                    this.takeDamage(ORC_ATTACK_DAMAGE); // self damage

                    this.rage = Math.min(this.rage - RAGE_BONUS_TIER_TWO, ORC_MIN_RAGE);

                    System.out.printf("%s is in a blinding rage. Damage tripled, but also hurts itself\n", this.getName());
                    System.out.printf("%s wildly attacks %s with %s for %d damage. %s takes %d damage in its rage.\n",
                            this.getName(),
                            targetCreature.getName(),
                            ORC_ATTACK_NAME,
                            ORC_ATTACK_DAMAGE * RAGE_TRIPLE_DAMAGE,
                            this.getName(),
                            ORC_ATTACK_DAMAGE);
                }
            } catch (LowRageException e) {
                System.out.println(e.getMessage());
            }
        }
        else // this.isAlive == false
        {
            System.out.printf("%s is dead and cannot act!\n", this.getName());
        }
    }

    /**
     * Checks if rage is above BERSERK_MIN_RAGE. Throws an exception if it's not.
     * @throws LowRageException
     */
    private void validateRage()
            throws LowRageException
    {
        if (rage < BERSERK_MIN_RAGE) {
            throw new LowRageException("Not enough rage to go berserk");
        }
    }

    /**
     * The Orc enters a bloodlust, increasing its rage by {@value ORC_RAGE_INCREMENT}
     * Helpful if the Orc doesn't have enough rage to use berserk.
     * Checks if the Orc is alive and allows the action or prints a message accordingly.
     */
    protected final void bloodlust()
    {
        if(this.isAlive())
        {
            this.rage += ORC_RAGE_INCREMENT;
            System.out.printf("%s enters a bloodlust. Rage increases\n", this.getName());
        }
        else
        {
            System.out.printf("%s is dead and cannot act!\n", this.getName());
        }
    }

    /**
     * The Orc meditates to calm its rage, decreasing its rage by {@value MEDITATE_RAGE_REDUCTION}
     * Helpful if the Orc doesn't want to inflict self-damage with higher tiers of rage bonus
     */
    protected final void meditate()
    {
        if(this.isAlive()) {
            this.rage = Math.max(this.rage - MEDITATE_RAGE_REDUCTION, ORC_MIN_RAGE);
            System.out.printf("%s starts to meditate, reducing rage by %d.\nRage: %d\n",
                    this.getName(),
                    MEDITATE_RAGE_REDUCTION,
                    this.getRage());
        }
        else
        {
            System.out.printf("%s is dead and cannot act!\n", this.getName());
        }
    }

    /**
     * Overrides the getDetails() method from Creature to display additional information about the Orc's rage level.
     */
    @Override
    public void getDetails()
    {
        final StringBuilder sb;

        sb = new StringBuilder();

        super.getDetails();

        sb.append("Rage: ");
        sb.append(rage);
        System.out.println(sb);
    }

    /**
     * Retrieves the current rage level of the Orc.
     *
     * @return The Orc's current rage level.
     */
    public int getRage()
    {
        return this.rage;
    }

    /* package private for testing, unavailable outside the package */
    void setRage(int newRage)
    {
        this.rage = newRage;
    }

}
