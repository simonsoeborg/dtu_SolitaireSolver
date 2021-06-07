package grp5.cdio.solitairesolver.Model;

public class CardColor {
    private String RED = "RED";
    private String BLACK = "BLACK";

    public String GetCardColor(CardModel card) {
        if(card.getId().equals(PossibleCardId.Ids.HJERTER.toString())){
            return RED;
        }

        if(card.getId().equals(PossibleCardId.Ids.RUDER.toString())){
            return RED;
        }

        if(card.getId().equals(PossibleCardId.Ids.SPAR.toString())){
            return BLACK;
        }

        if(card.getId().equals(PossibleCardId.Ids.KLØR.toString())){
            return BLACK;
        }

        return null;
    }
}
