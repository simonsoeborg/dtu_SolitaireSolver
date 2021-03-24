package grp5.cdio.solitairesolver.Model;


public enum FaceValue {
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

    public static int getValue(FaceValue value){
        return value.ordinal();
    }
}
