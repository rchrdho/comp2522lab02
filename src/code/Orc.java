public class Orc extends Creature
{
    private static final int ORC_MAX_HP = 150;

    private int rage;

    public Orc(final String name,
               final Date   birthday,
               final int    rage)
    {
        super(name, birthday);
        this.rage = rage;
        this.setHealthPoints(ORC_MAX_HP);
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
