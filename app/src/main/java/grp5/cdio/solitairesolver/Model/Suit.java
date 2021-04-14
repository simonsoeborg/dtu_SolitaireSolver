package grp5.cdio.solitairesolver.Model;
public enum Suit {
    /**
     * Suit enum.
     * <p>
     * Used to mange the {@link Card} suits
     * Values - UNKNOWN, HEARTS, CLUBS, DIAMONDS, SPADES;
     */
    UNKNOWN,
    HEARTS,
    CLUBS,
    DIAMONDS,
    SPADES;


    /**
     * Test if suit is red
     *
     * @param suit, Suit to test
     * @return boolean, true = red
     */
    public static boolean isRed(Suit suit){
        switch(getValue(suit)) {
            case 1:
            case 3:
                return true;
            default:
                return false;
        }
    }

    /**
     * Test if suit is black
     *
     * @param suit, Suit to test
     * @return boolean, true = black
     */
    public static boolean isBlack(Suit suit){
        switch(getValue(suit)) {
            case 2:
            case 4:
                return true;
            default:
                return false;
        }
    }


    /**
     * get int value of enum
     *
     * @param suit, Suit to get value from
     * @return int UNKNOWN = 0, HEARTS = 1, CLUBS = 2, DIAMONDS = 3, SPADES = 4
     */
    private static int getValue(Suit suit){
        return suit.ordinal();
    }


}
