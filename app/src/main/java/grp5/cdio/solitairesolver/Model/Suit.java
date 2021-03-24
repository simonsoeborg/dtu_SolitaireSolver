package grp5.cdio.solitairesolver.Model;

public enum Suit {
    UNKNOWN,
    HEARTS,
    CLUBS,
    DIAMONDS,
    SPADES;

    public static boolean isRed(Suit suit){
        switch(getValue(suit)) {
            case 1:
            case 3:
                return true;
            default:
                return false;
        }
    }
    public static boolean isBlack(Suit suit){
        switch(getValue(suit)) {
            case 2:
            case 4:
                return true;
            default:
                return false;
        }
    }
    private static int getValue(Suit suit){
        return suit.ordinal();
    }


}
