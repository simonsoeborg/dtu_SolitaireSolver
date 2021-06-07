package grp5.cdio.solitairesolver.Model;

import java.util.ArrayList;

public class BuildPileModel {
    // King is the lowest card in the pile, Ace is the highest Card in the pile (or 2)
    // Structure is Binary, which each new height, rotate colors from (Black to Red)
    ArrayList<CardModel> BuildPile;

    public BuildPileModel() {
        BuildPile = new ArrayList<>();
    }

    public void insertCard(CardModel card, int i) {
        BuildPile.add(i, card);
    }

    // In build pile, we go from High to low
    // Predecessor, needs to be higher than current
    public int Predecessor(CardModel cardModel) {
        int val = -1;
        for (CardModel element : BuildPile) {
            if(PossibleCardId.convertIdToPoints(element.getValue()) >= PossibleCardId.convertIdToPoints(cardModel.getValue())) {
                val = PossibleCardId.convertIdToPoints(element.getValue());
            }
        }
        return val;
    }

    // In build pile, we go from High to low
    // Successor needs to be lower than current
    public int Successor(CardModel cardModel) {
        int val = -1;
        for (CardModel element : BuildPile) {
            if(PossibleCardId.convertIdToPoints(element.getValue()) <= PossibleCardId.convertIdToPoints(cardModel.getValue())) {
                val = PossibleCardId.convertIdToPoints(element.getValue());
            }
        }
        return val;
    }
}
