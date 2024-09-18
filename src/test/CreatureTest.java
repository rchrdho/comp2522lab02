import javax.swing.plaf.synth.SynthTextAreaUI;

public class CreatureTest
{
    public static void main(final String[] args)
    {
        Date birthday;

        Creature greg;
        Creature gregg;
        Dragon smaug;
        Elf legolas;
        Orc azog;

        birthday = new Date(1800, 2, 22);
        gregg = new Creature("gregg", birthday);

        System.out.println("Creature Tests");
        // Successfully instance a Creature and execute its methods
        try
        {
            greg = new Creature("greg", birthday);
            if (greg instanceof Creature)
            {
                System.out.println("\nCreature Details:");
                greg.getDetails();
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        // Catch null name
        try
        {
            Creature steve = new Creature(null, birthday);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        // Catch empty name
        try
        {
            Creature mike = new Creature("", birthday);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println("Dragon Tests");
        // successfully execute each method, then catch LowFirePowerException
        try
        {
            smaug           = new Dragon("Smaug", birthday, 500);
            if (smaug instanceof Dragon)
            {
                System.out.println("\nDragon Details:");
                smaug.getDetails();
                smaug.breatheFire(gregg);
                smaug.setFirePower(0);
                smaug.getDetails();
                smaug.restoreFirePower();
                smaug.getDetails();
                smaug.setFirePower(0);
                smaug.getDetails();
                smaug.breatheFire(gregg);
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

        // Catch null birthday
        try
        {
            Dragon glaurung = new Dragon(null, birthday, 500);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        // Catch empty birthday
        try
        {
            Dragon encalagon = new Dragon("", birthday,300);
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
                azog.berserk(gregg);
                azog.berserk(gregg);
                azog.berserk(gregg);
                azog.berserk(gregg);
                azog.getDetails();
                azog.meditate();
                azog.berserk(gregg);
                azog.berserk(gregg);
                azog.berserk(gregg);
                azog.getDetails();
                azog.meditate();
                azog.berserk(gregg);
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
        // Successfully instance object and execute methods. Then catch LowManaException
        try
        {
            legolas = new Elf("Legolas", birthday);

            if (legolas instanceof Elf)
            {
                System.out.println("Elf Details:");
                legolas.getDetails();
                legolas.arcaneBolt(gregg);
                legolas.getDetails();
                legolas.setMana(0);
                legolas.getDetails();
                legolas.restoreMana();
                legolas.arcaneBolt(gregg);
                legolas.setMana(0);
                legolas.getDetails();
                legolas.arcaneBolt(gregg);
            }
        } catch (LowManaException e)
        {
            System.out.println("Elf couldn't cast spell: " + e.getMessage());
        }

        // Catch null birthday
        try
        {
            Elf elrond = new Elf(null, birthday);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        try
        {
            Elf galadriel = new Elf("", birthday);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        Orc shagrat = new Orc("Shagrat", birthday);
        gregg.setHealthPoints(10);
        shagrat.berserk(gregg);
        gregg.getDetails();

        shagrat.setHealthPoints(0);
        shagrat.berserk(gregg);
    }
}
