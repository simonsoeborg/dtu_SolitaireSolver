package grp5.cdio.solitairesolver.Model;

import java.util.ArrayList;
import java.util.BitSet;

public class BuildPileModel {
    // King is the lowest card in the pile, Ace is the highest Card in the pile (or 2)
    // Structure is Binary, which each new height, rotate colors from (Black to Red)
    ArrayList<CardModel> BuildPile;

    public BuildPileModel() {
        BuildPile = new ArrayList<>();

    }

    public void insertCard(CardModel card) {

        BuildPile.add(card);
    }

    public int Predecessor(CardModel cardModel) {
        for (CardModel element : BuildPile) {
            // Check if the
            if(PossibleCardId.convertIdToPoints(element.getValue()) <= PossibleCardId.convertIdToPoints(cardModel.getValue())) {
                return BuildPile.indexOf(element);
            }
        }
        return BuildPile.indexOf(BuildPile.size()-1);
    }
}
