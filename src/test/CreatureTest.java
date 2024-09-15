public class CreatureTest
{

    public static void main(final String[] args)
            throws LowFirePowerException, LowManaException, LowRageException
    {

        Creature creature;
        Creature smaugg;
        Creature azogg;
        Creature legolis;
        Creature defaultCreature;

        Date defaultCreatureBirthday;
        Date azogBirthday;
        Date smaugBirthday;

        defaultCreatureBirthday = new Date(1989, 06,25);
        smaugBirthday = new Date(1800, 1, 23);
        azogBirthday = new Date(1894, 3, 4);
        azogg = new Creature("Azog", azogBirthday);
        smaugg = new Creature("Smaug", smaugBirthday);
        defaultCreature = new Creature("Smaug", defaultCreatureBirthday);

        if(defaultCreature instanceof Creature)
        {
            Dragon smaug;
            smaug = (Dragon)defaultCreature;
            smaug.getDetails();
        }

    }
}
