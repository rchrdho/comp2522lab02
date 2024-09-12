public class Main
{
    public static void main(final String[] args) throws LowFirePowerException {
        Date today = new Date(1989, 06,25);
        Creature creature = new Creature("hello", today);
        Orc Azog = new Orc("Azog", today, 25);
        Dragon Smog = new Dragon("Smog",today,100, 100);

        creature.getDetails();
        Azog.getDetails();

        Azog.attack(creature);

        Smog.getDetails();

        Smog.breathFire(creature);
    }
}
