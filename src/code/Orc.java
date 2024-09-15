public class Orc extends Creature
{
    private static final int ORC_MAX_HP       = 150;
    private static final int ORC_DEFAULT_RAGE = 0;

    private int rage;

    public Orc(final String name,
               final Date   birthday)
    {
        super(name, birthday);
        this.rage = ORC_DEFAULT_RAGE;
        this.setHealthPoints(ORC_MAX_HP);
    }

    private void berserk()
    {

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
