public class Main
{
    public static void main(final String[] args) throws LowFirePowerException {
        Date today = new Date(1989, 06,25);
        Creature creature = new Creature("hello", today);
        Orc Azog = new Orc("Azog", today);
        Dragon Smaug = new Dragon("Smaug",today,100, 100);

        creature.getDetails();
        Azog.getDetails();

        Azog.attack(creature);

        Smaug.getDetails();

        Smaug.breathFire(creature);

        Smaug.getDetails();

        Smaug.restoreFirePower();

        System.out.println(Smaug.getHealthpoints());
        Azog.attack(Smaug);
        System.out.println(Smaug.getHealthpoints());
    }
}
