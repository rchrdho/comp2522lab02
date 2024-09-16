/**
 * The Orc class is a subclass of Creature, representing a specific type of creature with unique attributes such as rage.
 * Orcs have higher maximum HP than regular creatures and a special "berserk" attack that increases in damage as their rage builds.
 */
public class Orc extends Creature
{
    // Orc-specific constants for health and attack properties.
    private static final int ORC_MAX_HP             = 150;
    private static final int ORC_ATTACK_DAMAGE      = 15;
    private static final String ORC_ATTACK_NAME     = "berserk";

    // Orc-specific rage attributes to control damage escalation.
    private static final int ORC_DEFAULT_RAGE       = 0;
    private static final int ORC_RAGE_INCREMENT     = 5;
    private static final int RAGE_BONUS_TIER_ONE    = 10;
    private static final int RAGE_BONUS_TIER_TWO    = 20;
    private static final int RAGE_DOUBLE_DAMAGE     = 2;
    private static final int RAGE_TRIPLE_DAMAGE     = 3;

    // Orc's rage level
    private int rage;

    /**
     * Constructs a new Orc with a specified name and birthdate. The Orc starts with {@value ORC_MAX_HP} health and {@value ORC_DEFAULT_RAGE} rage.
     *
     * @param name The name of the Orc.
     * @param birthday The Orc's birthdate.
     */
    public Orc(final String name, final Date birthday)
    {
        super(name, birthday);
        this.rage = ORC_DEFAULT_RAGE;
        this.setHealthPoints(ORC_MAX_HP);
    }

    /**
     * Executes the Orc's berserk attack on the target creature.
     * As the Orc attacks, its rage increases. If the rage reaches or exceeds {@value RAGE_BONUS_TIER_ONE},
     * the attack deals {@value RAGE_DOUBLE_DAMAGE} times damage.
     *
     * @param targetCreature The creature that is being attacked.
     */
    protected final void berserk(final Creature targetCreature)
    {
        rage += ORC_RAGE_INCREMENT; // Increase rage after each attack.

        if (rage < RAGE_BONUS_TIER_ONE) // "normal" attack
        {
            targetCreature.takeDamage(ORC_ATTACK_DAMAGE);
            System.out.printf("%s attacks %s with %s for %d damage\n",
                    this.getName(),
                    targetCreature.getName(),
                    ORC_ATTACK_NAME,
                    ORC_ATTACK_DAMAGE);
        }
        else if (rage <= RAGE_BONUS_TIER_TWO) // rage boosted attack first tier
        {
            targetCreature.takeDamage(ORC_ATTACK_DAMAGE * RAGE_DOUBLE_DAMAGE);

            System.out.printf("OH MY GOD! %s has entered a berserk rage and is dealing double damage!\n", this.getName());
            System.out.printf("%s attacks %s with %s for %d damage\n",
                    this.getName(),
                    targetCreature.getName(),
                    ORC_ATTACK_NAME,
                    ORC_ATTACK_DAMAGE * RAGE_DOUBLE_DAMAGE);
        }
        else // rage boosted attack second tier
        {
            targetCreature.takeDamage(ORC_ATTACK_DAMAGE * RAGE_TRIPLE_DAMAGE);
            this.takeDamage(ORC_ATTACK_DAMAGE);

            System.out.printf("%s entered a blinding rage. Damage greatly increased, but also hurts itself\n", this.getName());
            System.out.printf("%s attacks %s with %s for %d damage\n",
                    this.getName(),
                    targetCreature.getName(),
                    ORC_ATTACK_NAME,
                    ORC_ATTACK_DAMAGE * RAGE_TRIPLE_DAMAGE);
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
}
