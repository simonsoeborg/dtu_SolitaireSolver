package grp5.cdio.solitairesolver.Model;

public enum FaceValue {
    /**
     * FaceValue enum.
     * <p>
     * Used to mange the {@link Card} face value
     * Values - UNKNOWN, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELVE, THIRTEEN
     */
    UNKNOWN,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    ELEVEN,
    TWELVE,
    THIRTEEN;


    /**
     * get int value of enum
     *
     * @param value face value to get value from
     * @return int UNKNOWN = 0, ONE = 1, TWO = 2, THREE = 3, FOUR = 4......
     */
    public static int getValue(FaceValue value){
        return value.ordinal();
    }
}



