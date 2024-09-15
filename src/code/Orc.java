public class Orc extends Creature
{
    private static final int ORC_MAX_HP         = 150;
    private static final int ORC_ATTACK_DAMAGE  = 15;

    private static final int ORC_DEFAULT_RAGE   = 0;
    private static final int ORC_MAX_RAGE       = 30;
    private static final int ORC_RAGE_INCREMENT = 5;

    private int rage;

    public Orc(final String name,
               final Date   birthday)
    {
        super(name, birthday);
        this.rage = ORC_DEFAULT_RAGE;
        this.setHealthPoints(ORC_MAX_HP);
    }

    @Override
    public void attack(final Creature targetCreature)
    {
        int attackDamage = ORC_ATTACK_DAMAGE;
        targetCreature.takeDamage(attackDamage);

        System.out.printf("%s attacks %s with %s for %d damage\n", this.getName(), targetCreature.getName(), "berserk", attackDamage);
    }

    private void berserk()
    {
        rage += ORC_RAGE_INCREMENT;

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
