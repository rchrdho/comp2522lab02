public class Main
{
    public static void main(final String[] args) throws LowFirePowerException, LowManaException {
        Date today = new Date(1989, 06,25);
        Creature creature = new Creature("hello", today);
        Orc Azog = new Orc("Azog", today);
        Dragon Smog = new Dragon("Smog",today,100, 100);

        creature.getDetails();
        Azog.getDetails();

        Azog.attack(creature);

        Smog.getDetails();

        Smog.breathFire(creature);

        Smog.restoreFirePower();


        Elf Legolas = new Elf("Legolas", today, 50);
        Legolas.getDetails();

        Legolas.castSpell(creature);
        Legolas.getDetails();


    }
}
