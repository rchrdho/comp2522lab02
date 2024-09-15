public class CreatureTest {

    public static void main(final String[] args)
            throws LowFirePowerException, LowManaException, LowRageException
    {

        Creature creature;
        Creature smaugg;
        Creature azogg;
        Creature legolass;

        Date defaultCreatureBirthday;
        Date azogBirthday;
        Date smaugBirthday;
        Date legolasBirthday;

        defaultCreatureBirthday = new Date(1989, 06,25);
        smaugBirthday = new Date(1800, 1, 23);
        azogBirthday = new Date(1894, 3, 4);
        legolasBirthday = new Date(1984, 3, 4);
        azogg = new Creature("Azog", azogBirthday);
        smaugg = new Creature("Smaug", smaugBirthday);
        creature = new Creature("Orc", defaultCreatureBirthday);
        legolass = new Creature("Legolas", legolasBirthday);

        System.out.println(smaugg.getClass().getSimpleName().equals("Creature"));
        if(smaugg instanceof Creature)
        {
            final Dragon smaug;
            smaug = (Dragon)creature;
            smaug.breathFire(creature);
            smaug.getDetails();
        }

        System.out.println(legolass.getClass().getSimpleName().equals("Creature"));

        if(legolass instanceof Creature)
        {
            final Elf legolas;

            legolas.castSpell(creature);
            legolas.getDetails();
        }


    }
}
