package ca.bcit.comp2522.lab02;

public class Main
{
    public static void main(final String[] args) throws LowFirePowerException, LowManaException {
        Date today = new Date(1989, 06, 25);
        Orc Azog = new Orc("Azog", today);
        Dragon Smaug = new Dragon("Smaug", today);
    }
}
