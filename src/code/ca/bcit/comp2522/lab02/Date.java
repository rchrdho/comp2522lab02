package ca.bcit.comp2522.lab02;

/**
 * <pre>
 * The Date class represents a specific calendar date, including day, month, and year, with validation and leap year handling.
 *
 * This class provides utility methods for:
 * - Validating the day, month, and year for a Date object based on a range of valid dates ({@value VALID_YEAR_MIN} to {@value CURRENT_YEAR}).
 * - Handling leap years in FEBRUARY.
 * - Calculating the day of the week for any valid date.
 * - Converting numeric representations of months into their string equivalents.
 *
 * The Date class relies on constants to represent day and month codes, month lengths,
 * and adjustments required for week code calculations depending on leap years and century-specific rules.
 *
 * Example usage:
 * Date date = new Date(1977, 10, 31);
 * System.out.println(date.toString()); // Outputs: Monday, October 31, 1977
 *</pre>
 * @author Bryson
 * @version 1.0
 */
public class Date
{
    private static final int SATURDAY                       = 0;
    private static final int SUNDAY                         = 1;
    private static final int MONDAY                         = 2;
    private static final int TUESDAY                        = 3;
    private static final int WEDNESDAY                      = 4;
    private static final int THURSDAY                       = 5;
    private static final int FRIDAY                         = 6;

    private static final int DAYS_IN_WEEK                   = 7;
    private static final int DAY_MIN                        = 1;

    private static final int JANUARY                        = 1;
    private static final int FEBRUARY                       = 2;
    private static final int MARCH                          = 3;
    private static final int APRIL                          = 4;
    private static final int MAY                            = 5;
    private static final int JUNE                           = 6;
    private static final int JULY                           = 7;
    private static final int AUGUST                         = 8;
    private static final int SEPTEMBER                      = 9;
    private static final int OCTOBER                        = 10;
    private static final int NOVEMBER                       = 11;
    private static final int DECEMBER                       = 12;

    private static final int MONTH_MIN                      = 1;
    private static final int MONTH_MAX                      = 12;
    private static final int MONTH_DAYS_31                  = 31;
    private static final int MONTH_DAYS_30                  = 30;
    private static final int FEBRUARY_LONG                  = 29;
    private static final int FEBRUARY_SHORT                 = 28;

    private static final int MONTH_CODE_0                   = 0;
    private static final int MONTH_CODE_1                   = 1;
    private static final int MONTH_CODE_2                   = 2;
    private static final int MONTH_CODE_3                   = 3;
    private static final int MONTH_CODE_4                   = 4;
    private static final int MONTH_CODE_5                   = 5;
    private static final int MONTH_CODE_6                   = 6;

    private static final int VALID_YEAR_MIN                 = 1800;

    private static final int WEEK_CODE_LEAP_YEAR_ADJUSTOR   = 6;
    private static final int WEEK_CODE_1800S_ADJUSTOR       = 2;
    private static final int WEEK_CODE_2000S_ADJUSTOR       = 6;

    private static final int YEARS_BETWEEN_LEAP_YEAR        = 4;
    private static final int YEARS_BETWEEN_CENTURY_LEAP     = 100;
    private static final int YEARS_BETWEEN_QUAD_CENTURY_LEAP= 400;

    private static final int MIN_YEAR_1800S                 = 1800;
    private static final int MAX_YEAR_1800S                 = 1899;
    private static final int MIN_YEAR_2000S                 = 2000;
    private static final int MAX_YEAR_2000S                 = 2099;

    /**
     * Constant representing the current year.
     */
    public static final int CURRENT_YEAR                    = 2024;
    private static final int MONTHS_IN_YEAR                 = 12;
    private static final int EXTRACT_YEAR_DIGITS            = 100;
    private static final int FOURS_IN_REMAINDER             = 4;

    private final int day;
    private final int month;
    private final int year;

    /**
     * <pre>Constructs a Date object with a year, month and day.
     *
     * Validates each parameter by validating the year, month, and day
     * Checks if the year is between VALID_YEAR_MIN and CURRENT_YEAR (validateYear)
     * Checks if the month is between MONTH_MIN and MONTH_MAX (validateMonth)
     * Checks if the day is valid for the given month/year by using a
     * switch. Certain months always have {@value MONTH_DAYS_31} days, certain months always have
     * {@value MONTH_DAYS_30} days. February returns {@value FEBRUARY_LONG} or {@value FEBRUARY_SHORT} depending on the outcome of isLeapYear
     * (validateDay)</pre>
     *
     *
     * @param year int between VALID_YEAR_MIN and CURRENT_YEAR
     * @param month int between MONTH_MIN and MONTH_MAX representing Jan - Dec
     * @param day int between DAY_MIN and MONTH_DAYS_31
     */
    public Date(final int year,
                final int month,
                final int day)
    {
        validateYear(year);
        validateMonth(month);
        validateDay(day, month, year);

        this.year    = year;
        this.month   = month;
        this.day     = day;
    }

    /**
     * Returns a copy of year instance field.
     * @return int year between VALID_YEAR_MIN and CURRENT_YEAR
     */
    public int getYear()
    {
        return this.year;
    }

    /**
     * Returns a copy of the month instance field.
     * @return int month between MONTH_MIN and MONTH_MAX
     */
    public int getMonth()
    {
        return this.month;
    }

    /**
     * Returns a copy of the day instance field.
     * @return int day between DAY_MIN and MONTH_DAYS_31
     */
    public int getDay()
    {
        return this.day;
    }

    /*
    If the passed year is less than VALID_YEAR_MIN or greater than CURRENT_YEAR, throw an exception.
     */
    private static void validateYear(final int year) throws IllegalArgumentException
    {
        if (year < VALID_YEAR_MIN || year > CURRENT_YEAR)
        {
            throw new IllegalArgumentException("Year: " + year + " must be between " + VALID_YEAR_MIN + " and " + CURRENT_YEAR);
        }
    }

    /*
    If the passed month is less than MONTH_MIN or greater than MONTH_MAX, throw an exception.
     */
    private static void validateMonth(final int month) throws IllegalArgumentException
    {
        if (month < MONTH_MIN || month > MONTH_MAX)
        {
            throw new IllegalArgumentException("Month: " + month + " must be between " + MONTH_MIN + " and " + MONTH_MAX);
        }
    }

    /*
    If the passed day is less than DAY_MIN or not appropriate for the given month, throw an exception.
     */
    private static void validateDay(final int day,
                                    final int month,
                                    final int year) throws IllegalArgumentException
    {
        if (day < DAY_MIN || day > getDaysInMonth(month, year))
        {
            throw new IllegalArgumentException("Day: " + day + " doesn't exist for month " + month
                    + " in year " + year);
        }
    }

    /*
    Switches the passed month against symbolic constants representing the months of the year. Returns the appropriate
    amount of days corresponding to month and status of leap year.
     */
    private static int getDaysInMonth(final int month,
                                      final int year) throws IllegalArgumentException
    {
        switch (month) {
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                return MONTH_DAYS_31;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return MONTH_DAYS_30;

            case FEBRUARY:
                if (isLeapYear(year))
                {
                    return FEBRUARY_LONG;
                }
                else
                {
                    return FEBRUARY_SHORT;
                }
            default:
                throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    /**
     * Gets the day of the week for the current Date object by calculating
     * a weekCode number and switching on it to return a String corresponding
     * to the appropriate day of the week.
     *
     * <p>weekCode is incremented according to three checks prior to the algorithm:</p>
     * <ul>
     *   <li>If the year of the Date is a leap year AND falls in Feb OR Jan, add {@value WEEK_CODE_LEAP_YEAR_ADJUSTOR}</li>
     *   <li>If the year falls in the 1800s, add {@value WEEK_CODE_1800S_ADJUSTOR}</li>
     *   <li>If the year falls in the 2000s, add {@value WEEK_CODE_2000S_ADJUSTOR}</li>
     * </ul>
     *
     * <p>Each step of the following algorithm produces a number that is added to weekCode:</p>
     *
     * <pre>
     * Example: October 31, 1977
     *
     * 1. Calculate the number of twelves in the last two digits of the year.
     *      year == 1977
     *      (1977 % 100) = 77
     *      77 / 12 = 6                                     weekCode += 6
     *
     * 2. Calculate the remainder from step one.
     *      77 / 12 = 6
     *      6 * 12 = 72
     *      77 - 72 = 5                                     weekCode += 5
     *
     * 3. Calculate the number of fours in step two.
     *      5 / 4 = 1                                       weekCode += 1
     *
     * 4. Add the day of the month to weekCode.
     *      day == 31                                       weekCode += 31
     *
     * 5. Add the month code to weekCode according to the following table:
     *      Month        [ J F M A M J J A S O N D ]
     *      Month Code   [ 1 4 4 0 2 5 0 3 6 1 4 6 ]
     *      October = O = 1                                 weekCode += 1
     *
     * 6. Final calculation:
     *      weekCode = 44
     *      weekCode % 7 = 2                                weekCode %= 7
     *
     * Result:
     *      weekCode == 2
     * </pre>
     *
     * <p>These six steps result in an int between 0-6.
     * Each digit corresponds to a day of the week according to the following table:</p>
     *
     * <pre>
     * Day of the Week [ S  S  M  T  W  T  F ]
     * Week Code       [ 0  1  2  3  4  5  6 ]
     * </pre>
     *
     * Since the final weekCode == 2, October 31, 1977 was a Monday.
     *
     * @return String of the day of the week for the Date object
     */
    public String getDayOfWeek()
    {
        int weekCode;

        weekCode = getWeekCode();

        switch (weekCode)
        {
            case SATURDAY:
                return "Saturday";
            case SUNDAY:
                return "Sunday";
            case MONDAY:
                return "Monday";
            case TUESDAY:
                return "Tuesday";
            case WEDNESDAY:
                return "Wednesday";
            case THURSDAY:
                return "Thursday";
            case FRIDAY:
                return "Friday";
            default:
                return "Error";
        }
    }

    /*
    Prepares the weekCode for getWeekCode() by adjusting the number according to some checks.
    If the year is a leap year AND the month is JANUARY or FEBRUARY, adjust accordingly.
    If the year is in the 1800s, adjust accordingly.
    If the year is in the 2000s, adjust accordingly.
     */
    private int getWeekCode() {
        int weekCode;

        weekCode = 0;

        if (isLeapYear(this.year) && (this.month == JANUARY || this.month == FEBRUARY))
        {
            weekCode += WEEK_CODE_LEAP_YEAR_ADJUSTOR;
        }
        if (this.year <= MAX_YEAR_1800S && this.year >= MIN_YEAR_1800S)
        {
            weekCode += WEEK_CODE_1800S_ADJUSTOR;
        }
        if (this.year <= MAX_YEAR_2000S && this.year >= MIN_YEAR_2000S)
        {
            weekCode += WEEK_CODE_2000S_ADJUSTOR;
        }

        weekCode += (this.year % EXTRACT_YEAR_DIGITS) / MONTHS_IN_YEAR;
        weekCode += (this.year % EXTRACT_YEAR_DIGITS) % MONTHS_IN_YEAR;
        weekCode += ((this.year % EXTRACT_YEAR_DIGITS) % MONTHS_IN_YEAR) / FOURS_IN_REMAINDER;
        weekCode += this.day;
        weekCode += this.getMonthCode();
        weekCode %= DAYS_IN_WEEK;
        return weekCode;
    }

    /*
    A leap year needs to be divisible by DIVISIBLE_BY_FOUR_LEAP_YEAR, but not divisible by DIVISIBLE_BY_100_LEAP_YEAR
    unless it's also divisible by DIVISIBLE_BY_400_LEAP_YEAR.
     */
    private static boolean isLeapYear(final int year)
    {
        return (year % YEARS_BETWEEN_LEAP_YEAR == 0 && year % YEARS_BETWEEN_CENTURY_LEAP != 0)
                || (year % YEARS_BETWEEN_QUAD_CENTURY_LEAP == 0);
    }

    /*
    Returns the appropriate month code according to
        Letter of Month [ J F M A M J J A S O N D ]
        Month Code      [ MONTH_CODE_1 MONTH_CODE_4 MONTH_CODE_4 MONTH_CODE_0 ... ]
     */
    private int getMonthCode()
    {
        int monthCode;

        monthCode = 0;

        switch (this.month)
        {
            case APRIL:
            case JULY:
                monthCode = MONTH_CODE_0;
                break;
            case JANUARY:
            case OCTOBER:
                monthCode = MONTH_CODE_1;
                break;
            case MAY:
                monthCode = MONTH_CODE_2;
                break;
            case AUGUST:
                monthCode = MONTH_CODE_3;
                break;
            case FEBRUARY:
            case MARCH:
            case NOVEMBER:
                monthCode = MONTH_CODE_4;
                break;
            case JUNE:
                monthCode = MONTH_CODE_5;
                break;
            case SEPTEMBER:
            case DECEMBER:
                monthCode = MONTH_CODE_6;
                break;
            default:
                break;
        }
        return monthCode;
    }

    /**
     * <pre>
     * Get the String of a month from the int that represents a month via a switch case.
     *
     * The month of each Date is stored as an int instance field. This function accesses
     * the int month and switches on it according to the month constants. It returns the
     * appropriate String corresponding to the int month.
     * </pre>
     *
     * @return String of the month
     */
    public String getMonthAsString()
    {
        switch (this.getMonth()) {
            case JANUARY:
                return "January";
            case FEBRUARY:
                return "February";
            case MARCH:
                return "March";
            case APRIL:
                return "April";
            case MAY:
                return "May";
            case JUNE:
                return "June";
            case JULY:
                return "July";
            case AUGUST:
                return "August";
            case SEPTEMBER:
                return "September";
            case OCTOBER:
                return "October";
            case NOVEMBER:
                return "November";
            case DECEMBER:
                return "December";
            default:
                return "Error getting the month";
        }
    }

    /**
     * Prints the details of a Date object.
     * @return String containing day of the week, month, day of the year and the year
     */
    @Override
    public String toString()
    {
        StringBuilder sb;

        sb = new StringBuilder();

        sb.append(getDayOfWeek());
        sb.append(", ");
        sb.append(getMonthAsString());
        sb.append(" ");
        sb.append(getDay());
        sb.append(", ");
        sb.append(getYear());

        return sb.toString();
    }
}
