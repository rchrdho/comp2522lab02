public class CreatureTest
{
    public static void main(final String[] args)
    {
        Date birthday;

        Creature defaultCreature;
        Dragon smaug;
        Elf legolas;
        Orc azog;


        birthday        = new Date(1800, 2, 22);

        smaug           = new Dragon("Smaug", birthday);
        azog            = new Orc("Azog", birthday);
        legolas         = new Elf("Legolas", birthday);

        defaultCreature = new Creature("Unnamed Creature", birthday);

        try
        {
            if (smaug instanceof Dragon)
            {
                System.out.println("\nDragon Details:");
                smaug.getDetails();
                smaug.breathFire(defaultCreature);
                smaug.getDetails();
                smaug.restoreFirePower();
            }
        } catch (LowFirePowerException e)
        {
            System.out.println("Dragon couldn't breathe fire: " + e.getMessage());
        }

        try
        {
            if (azog instanceof Orc)
            {
                System.out.println("\nOrc Details:");
                azog.getDetails();
                azog.berserk(defaultCreature);
                azog.getDetails();
                azog.getRage();
            }
        } catch (LowRageException e)
        {
            System.out.println("Orc couldn't perform rage attack: " + e.getMessage());
        }

        try
        {
            if (legolas instanceof Elf)
            {
                System.out.println("\nElf Details:");
                legolas.getDetails();
                legolas.castSpell(defaultCreature);
                legolas.getDetails();
                legolas.restoreMana();
                legolas.getDetails();
            }
        } catch (LowManaException e)
        {
            System.out.println("Elf couldn't cast spell: " + e.getMessage());
        }

        if (defaultCreature instanceof Creature)
        {
            System.out.println("\nCreature Details:");
            defaultCreature.getDetails();
        }
    }
}
