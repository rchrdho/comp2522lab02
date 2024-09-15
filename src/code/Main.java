public class Main
{
    public static void main(final String[] args) throws LowFirePowerException, LowManaException {
        Date today = new Date(1989, 06, 25);
        Creature creature = new Creature("hello", today);
        Orc Azog = new Orc("Azog", today);
        Dragon Smaug = new Dragon("Smaug", today);
    }
}
