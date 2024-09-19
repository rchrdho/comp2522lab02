public class CreatureTest
{
    public static void main(final String[] args)
    {
        Date birthday;

        Orc gregg;
        Dragon smaug;
        Elf legolas;
        Orc azog;

        birthday = new Date(1800, 2, 22);
        gregg = new Orc("Gregg", birthday);


        System.out.println("Dragon Tests");
        // successfully execute each method, then catch LowFirePowerException
        try
        {
            smaug           = new Dragon("Smaug", birthday);
            if (smaug instanceof Dragon)
            {
                System.out.println("\nDragon Details:");
                smaug.getDetails();
                smaug.attack(gregg);
                smaug.setFirePower(0);
                smaug.getDetails();
                smaug.restoreFirePower();
                smaug.getDetails();
                smaug.setFirePower(0);
                smaug.getDetails();
                smaug.attack(gregg);
            }
        }
        catch (LowFirePowerException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Catch null birthday
        try
        {
            Dragon glaurung = new Dragon(null, birthday);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        // Catch empty birthday
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
                azog.attack(gregg);
                azog.attack(gregg);
                azog.attack(gregg);
                azog.attack(gregg);
                azog.getDetails();
                azog.meditate();
                azog.attack(gregg);
                azog.attack(gregg);
                azog.attack(gregg);
                azog.getDetails();
                azog.meditate();
                azog.attack(gregg);
            }
        }
        catch (LowRageException e)
        {
            System.out.println("Orc couldn't perform berserk attack: " + e.getMessage());
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Name cannot be null or empty");
        } catch (Exception e) {
            throw new RuntimeException(e);
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
                legolas.attack(gregg);
                legolas.getDetails();
                legolas.setMana(0);
                legolas.getDetails();
                legolas.restoreMana();
                legolas.attack(gregg);
                legolas.setMana(0);
                legolas.getDetails();
                legolas.attack(gregg);
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
        try {
            shagrat.attack(gregg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        gregg.getDetails();

        shagrat.setHealthPoints(0);
        try {
            shagrat.attack(gregg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
