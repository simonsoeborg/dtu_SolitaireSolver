package grp5.cdio.solitairesolver.Controller;

import java.util.ArrayList;

import grp5.cdio.solitairesolver.CardControl;
import grp5.cdio.solitairesolver.Model.BuildPileModel;
import grp5.cdio.solitairesolver.Model.CardColor;
import grp5.cdio.solitairesolver.Model.CardModel;
import grp5.cdio.solitairesolver.Model.FoundationPileModel;
import grp5.cdio.solitairesolver.Model.PossibleCardId;

public class CardController {

    public static CardController cardController;
    private BuildPileModel buildPile;
    private FoundationPileModel foundationPile;
    private ArrayList<BuildPileModel> buildPiles; // THESE NEEDS TO BE UPDATED IN A CONTROLLER BEFORE RUNNING
    private ArrayList<FoundationPileModel> foundationPiles; // THESE NEEDS TO BE UPDATED IN A CONTROLLER BEFORE RUNNING

    public static void Init() {
        cardController = GetInstance();
        cardController.buildPile = new BuildPileModel();
        cardController.foundationPile = new FoundationPileModel();
        cardController.foundationPiles = new ArrayList<>();
        cardController.buildPiles = new ArrayList<>();
    }

    public void OnAddCardToFoundationPile(CardModel card) {
        // Check Card ID (Hjerter, Ruder, Spar, Klør)
        if(card.getId().equals(PossibleCardId.Ids.HJERTER.toString())) {
            HandleAdditionToPiles(card);
        }

        if(card.getId().equals(PossibleCardId.Ids.RUDER.toString())) {
            HandleAdditionToPiles(card);
        }

        if(card.getId().equals(PossibleCardId.Ids.SPAR.toString())) {
            HandleAdditionToPiles(card);
        }

        if(card.getId().equals(PossibleCardId.Ids.KLØR.toString())) {
            HandleAdditionToPiles(card);
        }

    }

    private void HandleAdditionToPiles(CardModel card) {
        // Check if we have a pile for this Id
        FoundationPileModel currentFoundationPile = GetFoundationPileWithSameId(card);
        // If there are no cards in foundation pile, then add card to foundation pile
        if(currentFoundationPile.FoundationPile.isEmpty()) {
            // Check if Card is Ace, if true, create Pile
            if(PossibleCardId.convertIdToPoints(card.getValue()) == 1) {
                currentFoundationPile.FoundationPile.add(card);
            }
        } else {
            // Check predecessor and Successor and then add to pile
            int cardValue = PossibleCardId.convertIdToPoints(card.getValue());
            // Get the precessor
            int pre = currentFoundationPile.Predecessor(card);
            // Get the successor
            int succ = currentFoundationPile.Successor(card);
            // If cardValue is lesser than or greater than pre and successorm then add card to pile.
            if(cardValue >= pre && cardValue <= succ) {
                currentFoundationPile.insertCard(card, pre+1);
            }
        }
    }

    public void OnAddCardToBuildPile(CardModel card) {
        String currentColor = CardColor.GetCardColor(card);
        int currentValue = PossibleCardId.convertIdToPoints(card.getValue());
        // Check all piles
        for (int i = 0; i < buildPiles.size(); i++) {
            // 2 Rules
            // Card must only be placed on a card with a different color
            if(!currentColor.equals(buildPiles.get(i).GetBuildTypeId())) {
                // Card must only be placed on a card with a higher value
                // Get the current BuildPile
                BuildPileModel currentBuildPile = buildPiles.get(i);
                // Set the size for the current Buildpile
                int size = currentBuildPile.BuildPile.size();
                // If current Card is lesser than the last card in the buildpile, then add to build pile
                if(currentValue < PossibleCardId.convertIdToPoints(currentBuildPile.BuildPile.get(size-1).getValue())) {
                    currentBuildPile.insertCard(card);
                }
            }
        }
    }

    private FoundationPileModel GetFoundationPileWithSameId(CardModel card) {
        // Iterate through the foundation piles and check if we have a pile that already has the same id as the card.
        for(int i = 0; i < foundationPiles.size(); i++) {
            if(foundationPiles.get(i).equals(card.getId())) {
                return foundationPiles.get(i);
            }
        }
        return new FoundationPileModel();
    }

    // Singleton method
    public static CardController GetInstance() {
        Init();
        if(cardController == null) {
            return new CardController();
        } else {
            return cardController;
        }
    }

}
