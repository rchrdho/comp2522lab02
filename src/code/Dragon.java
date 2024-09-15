public class Dragon extends Creature
{
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
     * Constructs a Dragon creature.
     *
     * The Dragon starts with a maximum of {@value DRAGON_MAX_HP} and firePower between {@value MIN_FIRE_POWER} and
     * {@value MAX_FIRE_POWER}
     *
     * @param dragonName                Name of the Dragon.
     * @param dragonBirthDate           The birthdate of the Dragon.
     * @throws IllegalArgumentException If the firePower is out of range.
     */
    public Dragon(final String  dragonName,
                  final Date    dragonBirthDate)
    {

        super(dragonName, dragonBirthDate);

        firePowerInRange(this.firePower);
        setMaxFirePower();
        this.setHealthPoints(DRAGON_MAX_HP);
    }

    /**
     * Ensures that the Dragon's firePower is above MIN_FIRE_POWER and below MAX_FIRE_POWER.
     *
     * @param firePower                 The current firePower of the Dragon.
     * @throws IllegalArgumentException if firePower is out of range.
     */
    private void firePowerInRange(final int firePower)
    {
        if(firePower < MIN_FIRE_POWER || firePower > MAX_FIRE_POWER)
        {
            throw new IllegalArgumentException("Fire power out of range");
        }
    }

    /**
     * Overrides the Creature class' getDetails method to include the Dragon's firePower.
     */
    @Override
    public void getDetails()
    {
        super.getDetails();

        System.out.printf("Fire Power: %d/%d\n", firePower, MAX_FIRE_POWER);
    }

    /**
     * Breathes fire on the target creature, dealing damage and reducing the caster's firePower.
     *
     * @param targetCreature         The target creature to attack.
     * @throws LowFirePowerException If the Dragon's firePower is too low to attack.
     */
    protected final void breathFire(final Creature targetCreature)
            throws LowFirePowerException
    {

        validateBreathFire(this.firePower);

        targetCreature.takeDamage(DRAGON_ATTACK_DAMAGE);

        System.out.printf("%s attacks %s with %s for %d damage\n", this.getName(), targetCreature.getName(),
                          DRAGON_ATTACK_NAME, DRAGON_ATTACK_DAMAGE);

        // Reduce firePower by FIRE_POWER_COST after breathFire
        this.firePower -= FIRE_POWER_COST;
        System.out.printf("Fire Power: %d/%d\n", firePower, MAX_FIRE_POWER);
    }

    /**
     * Validates the Dragon's firePower before attempting to breathe fire.
     *
     * @param firePower                 The current firePower of the Dragon.
     * @throws LowFirePowerException    if the firePower is below the required threshold for breathing fire.
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

    /**
     * Restores the Dragon's firePower by a fixed amount.
     * Validates that the firePower does not exceed the maximum allowed value.
     * Made final it cannot be modified.
     */
    protected final void restoreFirePower()
    {
        this.firePower += FIRE_POWER_RESTORE_AMOUNT;

        validateRestoreFirePower();

        System.out.printf("%s used restore Fire Power: %d/%d\n", this.getName(), firePower, MAX_FIRE_POWER);
    }

    /**
     *  Validates restoreFirePower, if its over MAX_FIRE_POWER set it to MAX_FIRE_POWER.
     */
    private void validateRestoreFirePower()
    {
        if(this.firePower > MAX_FIRE_POWER)
        {
            this.firePower = MAX_FIRE_POWER;
        }
    }

    private void setMaxFirePower()
    {
        this.firePower = MAX_FIRE_POWER;
    }

}
