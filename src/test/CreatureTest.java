public class CreatureTest {

    public static void main(final String[] args)
            throws LowFirePowerException, LowManaException, LowRageException
    {

        Creature creature;
        Creature smaugg;
        Creature azogg;
        Creature legolis;

        Date defaultCreatureBirthday;
        Date azogBirthday;
        Date smaugBirthday;

        defaultCreatureBirthday = new Date(1989, 06,25);
        smaugBirthday = new Date(1800, 1, 23);
        azogBirthday = new Date(1894, 3, 4);
        azogg = new Creature("Azog", azogBirthday);
        smaugg = new Creature("Smaug", smaugBirthday);
        creature = new Creature("Orc", defaultCreatureBirthday);

        System.out.println(smaugg.getClass().getSimpleName().equals("Creature"));
        if(smaugg instanceof Creature)
        {
            final Dragon smaug;
            smaug = (Dragon)creature;
            smaug.breathFire(creature);
            smaug.getDetails();
        }

    }
}
