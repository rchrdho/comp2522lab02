public class CreatureTest
{
    public static void main(final String[] args)
    {
        Date birthday;

        Creature greg;
        Dragon smaug;
        Elf legolas;
        Orc azog;

        birthday        = new Date(1800, 2, 22);

        legolas         = new Elf("Legolas", birthday);

        greg            = new Creature("greg", birthday);

        System.out.println("Dragon Tests");
        try
        {
            smaug           = new Dragon("Smaug", birthday);
            if (smaug instanceof Dragon)
            {
                System.out.println("\nDragon Details:");
                smaug.getDetails();
                smaug.breatheFire(greg);
                smaug.setFirePower(0);
                smaug.getDetails();
                smaug.restoreFirePower();
                smaug.getDetails();
                smaug.setFirePower(0);
                smaug.getDetails();
                smaug.breatheFire(greg);
            }
        }
        catch (LowFirePowerException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        try
        {
            Dragon glaurung = new Dragon(null, birthday);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        try
        {
            Dragon encalagon = new Dragon("", birthday);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println("Orc Tests");
        try
        {
            azog = new Orc("Azog", birthday);
            if (azog instanceof Orc)
            {
                System.out.println("\nOrc Details:");
                azog.getDetails();
                azog.bloodlust();
                azog.berserk(greg);
                azog.berserk(greg);
                azog.berserk(greg);
                azog.berserk(greg);
                azog.getDetails();
                azog.meditate();
                azog.berserk(greg);
                azog.berserk(greg);
                azog.berserk(greg);
                azog.getDetails();
                azog.meditate();
                azog.berserk(greg);
            }
        }
        catch (LowRageException e)
        {
            System.out.println("Orc couldn't perform berserk attack: " + e.getMessage());
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Name cannot be null or empty");
        }

        try
        {
            Orc gothmaw = new Orc(null, birthday);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        try
        {
            Orc lurtz = new Orc("", birthday);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println("\nElf Tests\n");
        try
        {
            if (legolas instanceof Elf)
            {
                System.out.println("Elf Details:");
                legolas.getDetails();
                legolas.arcaneBolt(greg);
                legolas.getDetails();
                legolas.setMana(0);
                legolas.getDetails();
                legolas.restoreMana();
                legolas.arcaneBolt(greg);
                legolas.setMana(0);
                legolas.getDetails();
                legolas.arcaneBolt(greg);
            }
        } catch (LowManaException e)
        {
            System.out.println("Elf couldn't cast spell: " + e.getMessage());
        }

        try
        {
            Elf elrond = new Elf(null, birthday);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        if (greg instanceof Creature)
        {
            System.out.println("\nCreature Details:");
            greg.getDetails();
        }
    }
}
