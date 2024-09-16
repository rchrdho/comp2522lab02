/**
 * Elf class represents an Elf, which is a subclass of the Creature class.
 * Elf class has additional attributes such as mana, its own health, and can cast spells and restore mana.
 *
 * @author Phyo, Bryson, Richard
 * @version 1.0
 */
public class Elf extends Creature
{
    private static final int ELF_MIN_MANA            = 0;
    private static final int ELF_MAX_MANA            = 50;

    private static final int ELF_MAX_HP              = 80;

    private static final int ELF_MANA_COST           = 5;
    private static final int ELF_ATTACK_DAMAGE       = 10;
    private static final int ELF_MANA_RESTORE_AMOUNT = 5;

    private static final String ELF_ATTACK_NAME      = "spell";

    private int mana;

    /**
     * Constructs an Elf with the specified name, date of birth, health, and mana.
     *
     * @param elfName      name of the elf
     * @param elfBirthDate birthdate of the elf
     */
    public Elf(final String elfName,
               final Date   elfBirthDate)
    {
        super(elfName, elfBirthDate);

        validateMana(mana);

        this.mana   = ELF_MAX_MANA;
        this.setHealthPoints(ELF_MAX_HP);
    }

    /**
     * Validates mana; checks whether it is lower than ELF_MIN_MANA or higher than ELF_MAX_MANA.
     * Throws an IllegalArgumentException when mana is not valid.
     *
     * @param mana Mana to be checked
     */
    private static void validateMana(final int mana)
    {
        if(mana < ELF_MIN_MANA || mana > ELF_MAX_MANA)
        {
            throw new IllegalArgumentException(String.format("Elf mana cannot be less than %d or higher than %d ",
                    ELF_MIN_MANA, ELF_MAX_MANA));
        }
    }

    /**
     * Casts a spell on the target creature, reducing the elf's mana by ELF_MANA_REDUCTION and dealing ELF_TARGET_DAMAGE
     * damage to the target creature. Prevents elf from casting spell when it's mana is lower
     * than ELF_LOWEST_MANA_USAGE.
     * Protected final is used because we don't want any subclasses of Elf to be able to change the logic of castSpell()
     *
     * @param targetCreature Creature to receive damage
     * @throws LowManaException If the elf's mana is less than ELF_LOWEST_MANA_USAGE
     */
    protected final void castSpell(final Creature targetCreature)
            throws LowManaException
    {
        validateManaUsage();

        this.mana = Math.max(this.mana - ELF_MANA_COST, ELF_MIN_MANA);

        targetCreature.takeDamage(ELF_ATTACK_DAMAGE);
        System.out.printf("%s attacks %s with %s for %d damage\n",
                this.getName(),
                targetCreature.getName(),
                ELF_ATTACK_NAME,
                ELF_ATTACK_DAMAGE);
    }

    /**
     * Validates mana usage; checks whether it is lower than ELF_LOWEST_MANA_USAGE.
     *
     * @throws LowManaException If the elf's mana is less than ELF_LOWEST_MANA_USAGE
     */
    private void validateManaUsage()
            throws LowManaException
    {
        if (this.mana < ELF_MANA_COST)
        {
            throw new LowManaException(String.format("Mana is lower than %d. Cannot use it right now.",
                    ELF_MANA_COST));
        }
    }

    /**
     * Restores the elf's mana by the specified amount, ensuring that the mana does not exceed ELF_MAX_MANA
     */
    public void restoreMana()
    {
        this.mana = Math.min(this.mana + ELF_MANA_RESTORE_AMOUNT, ELF_MAX_MANA);
    }

    /**
     * Override getDetails method in Creature class by adding mana to the output.
     */
    @Override
    public void getDetails()
    {
        super.getDetails();

        System.out.printf("Mana: %d/%d\n", mana, ELF_MAX_MANA);
    }

    public void setMana(int mana)
    {
        this.mana = mana;
    }
}
