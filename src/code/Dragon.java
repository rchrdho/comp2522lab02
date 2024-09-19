public class Dragon extends Creature
{
    // Symbolic Constants
    private static final int    MIN_FIRE_POWER              = 0;
    private static final int    MAX_FIRE_POWER              = 100;
    private static final int    DRAGON_MAX_HP               = 500;
    private static final int    FIRE_POWER_COST             = 10;
    private static final int    DRAGON_ATTACK_DAMAGE        = 20;
    private static final int    FIRE_POWER_RESTORE_AMOUNT   = 20;
    private static final String DRAGON_ATTACK_NAME          = "breathe fire";

    // Instance variable
    private int firePower;

    /**
     * Constructs a Dragon creature.
     *
     * @param dragonName                Name of the Dragon.
     * @param dragonBirthDate           The birthdate of the Dragon.
     * @throws IllegalArgumentException If name is null or empty
     */
    public Dragon(final String  dragonName,
                  final Date    dragonBirthDate)
            throws IllegalArgumentException
    {
        super(dragonName, dragonBirthDate);

        this.firePower = MAX_FIRE_POWER;
        this.setHealthPoints(DRAGON_MAX_HP);
    }

    /**
     * Overrides the Creature class' getDetails method to include the Dragon's firePower.
     */
    @Override
    public void getDetails()
    {
        super.getDetails();

        System.out.printf("Fire Power: %d/%d\n",
                firePower,
                MAX_FIRE_POWER);
    }

    /**
     * Breathes fire on the target creature, dealing damage and reducing the caster's firePower.
     *
     * @param targetCreature         The target creature to attack.
     * @throws LowFirePowerException If the Dragon's firePower is too low to attack.
     */
    void attack(final Creature targetCreature)
            throws LowFirePowerException
    {
        if(this.isAlive()) {
            this.validateBreatheFire();

            targetCreature.takeDamage(DRAGON_ATTACK_DAMAGE);

            System.out.printf("%s attacks %s with %s for %d damage\n",
                    this.getName(),
                    targetCreature.getName(),
                    DRAGON_ATTACK_NAME,
                    DRAGON_ATTACK_DAMAGE);

            // Reduce firePower by FIRE_POWER_COST after breathFire or set to 0 if firePower would be negative
            this.firePower = Math.max(this.firePower - FIRE_POWER_COST, MIN_FIRE_POWER);
            System.out.printf("Fire Power: %d/%d\n",
                    firePower,
                    MAX_FIRE_POWER);
        }
        else
        {
            System.out.printf("%s is dead and cannot act!", this.getName());
        }
    }

    /**
     * Validates the Dragon's firePower before attempting to breathe fire.
     *
     * @throws LowFirePowerException    if the firePower is below the required threshold for breathing fire.
     */
    private void validateBreatheFire()
            throws LowFirePowerException
    {
        if(this.firePower < FIRE_POWER_COST)
        {
            throw new LowFirePowerException(
                    String.format("Unable to cast. Current fire power is: %d\n",
                    this.firePower));
        }
    }

    /**
     * Restores the Dragon's firePower by a fixed amount.
     * Validates that the firePower does not exceed the maximum allowed value using Math.min
     * Made final it cannot be overridden by potential subclasses.
     */
    protected final void restoreFirePower()
    {
        if(this.isAlive())
        {
            this.firePower = Math.min(this.firePower + FIRE_POWER_RESTORE_AMOUNT, MAX_FIRE_POWER);

            System.out.printf("%s used restore Fire Power\n",
                    this.getName());
        }
        else
        {
            System.out.printf("%s is dead and cannot act!", this.getName());
        }
    }

    /* package private for testing, unavailable outside the package */
    void setFirePower(int newFirePower)
    {
        firePower = newFirePower;
    }

}
