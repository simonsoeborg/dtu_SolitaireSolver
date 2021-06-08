package grp5.cdio.solitairesolver.Model;

import java.util.ArrayList;

public class BuildPileModel {
    // King is the lowest card in the pile, Ace is the highest Card in the pile (or 2)
    // Structure is Binary, with each new height, rotate colors from (Black to Red)
    public ArrayList<CardModel> BuildPile;

    public BuildPileModel() {
        BuildPile = new ArrayList<>();
    }

    // Get the build pile Id
    public String GetBuildTypeId() {
        if(!BuildPile.isEmpty()) {
            return BuildPile.get(0).getId();
        } else {
            return null;
        }
    }

    // Inserts a card to a build pile
    public void insertCard(CardModel card) {
        BuildPile.add(card);
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
