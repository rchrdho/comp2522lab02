public class Orc extends Creature
{
    private static final int ORC_MAX_HP             = 150;
    private static final int ORC_ATTACK_DAMAGE      = 15;
    private static final String ORC_ATTACK_NAME     = "berserk";

    private static final int ORC_DEFAULT_RAGE       = 0;
    private static final int RAGE_DAMAGE_THRESHOLD  = 20;
    private static final int ORC_RAGE_INCREMENT     = 5;

    private static final int RAGE_DAMAGE_MULTIPLIER = 2;

    private int rage;

    public Orc(final String name,
               final Date   birthday)
    {
        super(name, birthday);
        this.rage = ORC_DEFAULT_RAGE;
        this.setHealthPoints(ORC_MAX_HP);
    }

    public void berserk(final Creature targetCreature)
    {
        rage += ORC_RAGE_INCREMENT;

        if(rage >= RAGE_DAMAGE_THRESHOLD)
        {
            targetCreature.takeDamage(ORC_ATTACK_DAMAGE * RAGE_DAMAGE_MULTIPLIER);
            System.out.printf("OH MY GOD! %s has entered a berserk rage and is dealing double damage!\n", this.getName());
            System.out.printf("%s attacks %s with %s for %d damage\n",
                    this.getName(),
                    targetCreature.getName(),
                    ORC_ATTACK_NAME,
                    ORC_ATTACK_DAMAGE * RAGE_DAMAGE_MULTIPLIER);
        }
        else
        {
            targetCreature.takeDamage(ORC_ATTACK_DAMAGE);
            System.out.printf("%s attacks %s with %s for %d damage\n",
                    this.getName(),
                    targetCreature.getName(),
                    ORC_ATTACK_NAME,
                    ORC_ATTACK_DAMAGE);
        }
    }

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
}
