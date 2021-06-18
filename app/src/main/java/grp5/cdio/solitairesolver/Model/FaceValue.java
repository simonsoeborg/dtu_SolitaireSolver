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


    /**
     * Override toString
     */
    @Override
    public String toString(){
        if (this.ordinal() == 0){
            return "Ukendt";
        }
        else if (this.ordinal() == 1){
            return "Es";
        }
        else if (this.ordinal() == 2){
            return "To";
        }
        else if (this.ordinal() == 3){
            return "Tre";
        }
        else if (this.ordinal() == 4){
            return "Fire";
        }
        else if (this.ordinal() == 5){
            return "Fem";
        }
        else if (this.ordinal() == 6){
            return "Seks";
        }
        else if (this.ordinal() == 7){
            return "Syv";
        }
        else if (this.ordinal() == 8){
            return "Otte";
        }
        else if (this.ordinal() == 9){
            return "Ni";
        }
        else if (this.ordinal() == 10){
            return "Ti";
        }
        else if (this.ordinal() == 11){
            return "Knægt";
        }
        else if (this.ordinal() == 12){
            return "Dame";
        }
        else if (this.ordinal() == 13){
            return "Konge";
        }
        return "Ukendt";
    }

}



