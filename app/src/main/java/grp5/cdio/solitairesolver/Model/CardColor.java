package grp5.cdio.solitairesolver.Model;

public class CardColor {
    // Define colors
    private String RED = "RED";
    private String BLACK = "BLACK";

    // Return the card color based on the Card Id
    public static String GetCardColor(CardModel card) {
        CardColor cardColor = new CardColor();
        if(card.getId().equals(PossibleCardId.Ids.HJERTER.toString())){
            return cardColor.RED;
        }

        if(card.getId().equals(PossibleCardId.Ids.RUDER.toString())){
            return cardColor.RED;
        }

        if(card.getId().equals(PossibleCardId.Ids.SPAR.toString())){
            return cardColor.BLACK;
        }

        if(card.getId().equals(PossibleCardId.Ids.KLØR.toString())){
            return cardColor.BLACK;
        }

        return null;
    }
}
