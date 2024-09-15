public class Dragon extends Creature {

    // Symbolic Constants
    private static final int    MAX_FIRE_POWER              = 100;
    private static final int    MIN_FIRE_POWER              = 0;
    private static final int    DRAGON_MAX_HP               = 500;
    private static final int    FIRE_POWER_COST             = 10;
    private static final int    DRAGON_ATTACK_DAMAGE        = 20;
    private static final int    FIRE_POWER_RESTORE_AMOUNT   = 20;
    private static final String DRAGON_ATTACK_NAME          = "Breath Fire";

    // Instance variable
    private int firePower;

    /**
     * Constructs a Dragon Object.
     *
     * @param dragonName
     * @param dragonBirthDate
     * @param dragonHP
     * @param firePower
     */
    public Dragon(final String  dragonName,
                  final Date    dragonBirthDate,
                  final int     dragonHP,
                  final int     firePower)
    {

        super(dragonName, dragonBirthDate);

        firePowerInRange(firePower);
        this.firePower = firePower;
        this.setHealthPoints(DRAGON_MAX_HP);
    }

    /*
     <pre>
     Validator for firePower
     throws IllegalArgumentException if firePower is not within
     MIN_FIRE_POWER or MAX_FIRE_POWER
     <pre>
     */
    private void firePowerInRange(final int firePower)
    {
        if(firePower < MIN_FIRE_POWER || firePower > MAX_FIRE_POWER)
        {
            throw new IllegalArgumentException("Fire power out of range");
        }
    }

    /**
     *
     */
    @Override
    public void getDetails()
    {
        super.getDetails();

        System.out.printf("Fire Power: %d/%d\n", firePower, MAX_FIRE_POWER);
    }

    /*
     */
    protected void breathFire(final Creature targetCreature)
            throws LowFirePowerException
    {

        validateBreathFire(this.firePower);
        targetCreature.takeDamage(DRAGON_ATTACK_DAMAGE);
        System.out.printf("%s attacks %s with %s for %d damage\n", this.getName(), targetCreature.getName(),
                          DRAGON_ATTACK_NAME, DRAGON_ATTACK_DAMAGE);

        this.firePower -= FIRE_POWER_COST;
        attack(target);
        System.out.printf("Fire Power: %d/%d\n", firePower, MAX_FIRE_POWER);
    }

    /*
    Validate breathFire
    Checks if current firePower is less than FIRE_POWER_COST
     */
    private static void validateBreathFire(int firePower)
            throws LowFirePowerException
    {
        if(firePower < FIRE_POWER_COST)
        {
            throw new LowFirePowerException(
                    String.format("Unable to cast\nCurrent fire power is: %d\n",
                    firePower));
        }
    }

    protected void restoreFirePower()
    {
        this.firePower += FIRE_POWER_RESTORE_AMOUNT;
        validateRestoreFirePower();
        System.out.printf("%s used restore Fire Power: %d/%d\n", this.getName(), firePower, MAX_FIRE_POWER);
    }

    /*
    Validates restoreFirePower, if its over MAX_FIRE_POWER set it to MAX_FIRE_POWER
     */
    private void validateRestoreFirePower()
    {
        if(this.firePower > MAX_FIRE_POWER)
        {
            this.firePower = MAX_FIRE_POWER;
        }
    }

}
