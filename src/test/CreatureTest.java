public class CreatureTest
{
    public static void main(final String[] args)
            throws LowFirePowerException, LowManaException, LowRageException
    {

        Creature defaultCreature;
        Dragon smaug;
        Date defaultCreatureBirthday;
        Date azogBirthday;
        Date smaugBirthday;

        smaugBirthday = new Date(1800, 2, 22);
        smaug = new Dragon("Smaug", smaugBirthday);
        defaultCreatureBirthday = new Date(1989, 06,25);
        defaultCreature = new Creature("Smaug", defaultCreatureBirthday);

        System.out.println(defaultCreature.getClass().getName());

        if(smaug instanceof Dragon)
        {
            Creature newCreature;
            newCreature = (Dragon)smaug;
            newCreature.getDetails();
            smaug.breathFire(defaultCreature);

        }

    }
}
