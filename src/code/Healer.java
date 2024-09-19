public class Healer extends Creature
{
    private static final int HEALER_MAX_HP        = 100;
    private static final int HEALER_MAX_MANA      = 100;
    private static final int HEALER_ATTACK_DAMAGE = 1;

    private static final int RESTORE_MANA_AMOUNT  = 50;
    private static final int HEAL_MANA_COST       = 10;

    private int mana;

    /**
     * Calls the super method to instance a healer with the added instance field of mana.
     *
     * By default, health is set to {@value HEALER_MAX_HP} and mana is set to {@value HEALER_MAX_MANA}.
     * @param name
     * @param birthday
     */
    public Healer(String name, Date birthday)
    {
        super(name, birthday, HEALER_MAX_HP);

        this.mana = HEALER_MAX_MANA;
    }

    /**
     * The healer attempts a pitiful attack that does a measly {@value HEALER_ATTACK_DAMAGE} damage.
     * @param targetCreature creature targeted by attack
     */
    void attack(final Creature targetCreature)
    {
        targetCreature.takeDamage(HEALER_ATTACK_DAMAGE);
    }

    /**
     * Heals the creature by the specified amount.
     * Health cannot exceed {@value HEALER_MAX_HP}.
     *
     * @throws HealingException If the heal amount is invalid.
     */
    @Override
    void heal(final Creature target)
            throws HealingException
    {
        super.heal(target);
        this.mana -= HEAL_MANA_COST;
    }

    /**
     * Restores mana up to a maximum. Selects the appropriate value using Math.min to choose
     * between the lesser value, {@value HEALER_MAX_MANA} or the total of current mana + {@value RESTORE_MANA_AMOUNT}
     */
    void restore()
    {
        this.mana = Math.min(this.mana + RESTORE_MANA_AMOUNT, HEALER_MAX_MANA);

        System.out.printf("%s restores %d mana.\nMana: %d / %d\n",
                this.getName(),
                RESTORE_MANA_AMOUNT,
                this.mana,
                HEALER_MAX_MANA);
    }

    /**
     * Overrides getDetails from Creature to add the current mana of the Healer object.
     */
    @Override
    public void getDetails()
    {
        super.getDetails();
        System.out.println("Mana: " + this.mana);
    }
}
