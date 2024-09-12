public class Elf extends Creature
{
    //TODO: Constructor (String name, Date dateOfBirth, int health, int mana)
    //TODO: Override getDetails() : Add mana to output
    //TODO: castSpell() Reduces mana by 5/Deals 10 damage to creature/If mana < 5, throw a checked LowManaException
    //TODO: restoreMana(int amount): Increases mana but cannot exceed 50

    private static final int ELF_MIN_MANA = 0;
    private static final int ELF_MAX_MANA = 50;

    private static final int ELF_DEFAULT_HP = 80;
    private static final int ELF_MIN_HP = 0;

    private static final int ELF_MANA_REDUCTION  = 5;
    private static final int CREATURE_DAMAGE_NUM = 10;

    private static final int ELF_LOWEST_MANA = 5;

    private int elfMana;
    private int elfHealth;

    public Elf(final String name,
               final Date   dateOfBirth,
               final int    elfHealth,
               final int    elfMana)
    {
        super(name, dateOfBirth);

        validateMana(elfMana);

        this.elfMana   = elfMana;
        this.elfHealth = elfHealth;
    }

    private static void validateMana(final int elfMana)
    {
        if(elfMana < ELF_MIN_MANA || elfMana > ELF_MAX_MANA)
        {
            throw new IllegalArgumentException(String.format("Elf Mana is less than %d or higher than %d ",
                    ELF_MIN_MANA, ELF_MAX_MANA));
        }
    }

    //TODO: castSpell() to check logic
    public void castSpell() throws LowManaException {

        this.elfMana -= ELF_MANA_REDUCTION;

        if(this.elfMana < ELF_LOWEST_MANA)
        {
            throw new LowManaException(String.format("Mana is lower than %d. Cannot use it right now.",
                    ELF_LOWEST_MANA));
        }
    }






}
