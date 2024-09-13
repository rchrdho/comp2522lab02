public class Dragon extends Creature {
    private static final int    MAX_FIRE_POWER              = 100;
    private static final int    MIN_FIRE_POWER              = 0;
    private static final int    DRAGON_MAX_HP               = 500;
    private static final int    DRAGON_MIN_HP               = 0;
    private static final int    FIRE_POWER_COST             = 10;
    private static final int    DRAGON_ATTACK_DAMAGE        = 20;
    private static final int    FIRE_POWER_RESTORE_AMOUNT   = 20;
    private static final String DRAGON_ATTACK_NAME          = "Breath Fire";

    private int firePower;

    /**
     *
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
    public void getDetails() {

        super.getDetails();

        System.out.printf("Fire Power: %d\n", firePower);
    }

    /*
     */
    protected void breathFire(final Creature target)
            throws LowFirePowerException
    {
        validateBreathFire(this.firePower);

        this.firePower -= FIRE_POWER_COST;
        attack(target);

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

    /**
     * Override attack method in Creature Class.
     *
     *
     * @param target Creature to receive damage
     */
    @Override
    public void attack(final Creature target)
    {
        super.attack(target);
        setHealthPoints(-DRAGON_ATTACK_DAMAGE);
    }

    /**
     * Override getAttackName method in Creature Class.
     *
     * @return
     */
    @Override
    public String getAttackName()
    {
        return DRAGON_ATTACK_NAME;
    }

    private int restoreFirePower(final int FIRE_POWER_RESTORE_AMOUNT)
    {
        validateRestoreFirePower();
        setRestoreFirePower();
        return this.firePower;
    }

    private void validateRestoreFirePower()
    {
        if(this.firePower > MAX_FIRE_POWER)
        {
            this.firePower = MAX_FIRE_POWER;
        }
    }

    private void setRestoreFirePower()
    {
        this.firePower += FIRE_POWER_RESTORE_AMOUNT;
    }
}
