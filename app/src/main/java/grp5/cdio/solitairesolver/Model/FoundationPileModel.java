package grp5.cdio.solitairesolver.Model;

import java.util.ArrayList;

public class FoundationPileModel {
    // Ace lowest card in the pile, King highest card in the pile
    public ArrayList<CardModel> FoundationPile;

    public FoundationPileModel() {
        FoundationPile = new ArrayList<>();
    }

    public String GetFoundationTypeId() {
        if(!FoundationPile.isEmpty()) {
            return FoundationPile.get(0).getId();
        } else {
            return null;
        }
    }

    public void insertCard(CardModel card, int i) {
        FoundationPile.add(i, card);
    }

    // In build pile, we go from High to low
    // Predecessor, needs to be higher than current
    public int Predecessor(CardModel cardModel) {
        int val = -1;
        for (CardModel element : FoundationPile) {
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
        for (CardModel element : FoundationPile) {
            if(PossibleCardId.convertIdToPoints(element.getValue()) <= PossibleCardId.convertIdToPoints(cardModel.getValue())) {
                val = PossibleCardId.convertIdToPoints(element.getValue());
            }
        }
        return val;
    }
}
