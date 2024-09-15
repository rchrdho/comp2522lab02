public class Main
{
    public static void main(final String[] args) throws LowFirePowerException {
        Date today = new Date(1989, 06,25);
        Creature creature = new Creature("hello", today);
        Orc Azog = new Orc("Azog", today);
        Dragon Smaug = new Dragon("Smaug",today,100, 100);

        creature.getDetails();
        Azog.getDetails();

        Smaug.getDetails();

        System.out.println(Smaug.getHealthpoints());
        Azog.berserk(Smaug);
        System.out.println(Smaug.getHealthpoints());
        Azog.berserk(Smaug);
        Azog.berserk(Smaug);
        Azog.berserk(Smaug);
        Azog.berserk(Smaug);
        Azog.berserk(Smaug);
        Azog.berserk(Smaug);
        Azog.berserk(Smaug);
    }
}
