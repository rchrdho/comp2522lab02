/**
 * Elf class represents an Elf, which is a subclass of the Creature class.
 * Elf class has additional attributes such as mana, its own health, and can cast spells and restore mana.
 *
 * @author Phyo, Bryson, Richard
 * @version 1.0
 */
public class Elf extends Creature
{
    // TODO: To revisit comments
    private static final int ELF_MIN_MANA            = 0;
    private static final int ELF_MAX_MANA            = 50;

    private static final int ELF_MAX_HP              = 80;

    private static final int ELF_MANA_COST           = 5;
    private static final int ELF_ATTACK_DAMAGE       = 10;
    private static final int ELF_LOWEST_MANA_USAGE   = 5;
    private static final int ELF_MANA_RESTORE_AMOUNT = 5;

    private int mana;

    /**
     * Constructs an Elf with the specified name, date of birth, health, and mana.
     *
     * @param elfName      name of the elf
     * @param elfBirthDate birthdate of the elf
     * @param mana         mana points of the elf (should be between ELF_MIN_MANA and ELF_MAX_MANA)
     */
    public Elf(final String elfName,
               final Date   elfBirthDate,
               final int    mana)
    {
        super(elfName, elfBirthDate);

        validateMana(mana);

        this.mana   = mana;
        this.setHealthPoints(ELF_MAX_HP);
    }

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
     * damage to the target creature. Throws a LowManaException when elf's mana reaches ELF_LOWEST_MANA_USAGE
     *
     * @param target Creature to receive damage
     * @throws LowManaException If the elf's mana is less than ELF_LOWEST_MANA_USAGE
     */
    public void castSpell(final Creature target)
            throws LowManaException
    {
        validateManaUsage(this.mana);

        this.mana -= ELF_MANA_COST;

        target.takeDamage(ELF_ATTACK_DAMAGE);
    }

    private void validateManaUsage(final int mana)
            throws LowManaException
    {
        if (mana < ELF_LOWEST_MANA_USAGE)
        {
            throw new LowManaException(String.format("Mana is lower than %d. Cannot use it right now.",
                    ELF_LOWEST_MANA_USAGE));
        }
    }

    /**
     * Restores the elf's mana by the specified amount, ensuring that the mana does not exceed ELF_MAX_MANA
     */
    public void restoreMana()
    {
        mana += ELF_MANA_RESTORE_AMOUNT;
        validateRestoreMana();
    }

    private void validateRestoreMana()
    {
        if (this.mana > ELF_MAX_MANA)
        {
            throw new IllegalArgumentException(String.format("Cannot restore Mana. It has reached max capacity of %d",
                    ELF_MAX_MANA));
        }
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
}
