package grp5.cdio.solitairesolver.Controller;

import java.util.ArrayList;

import grp5.cdio.solitairesolver.CardControl;
import grp5.cdio.solitairesolver.Model.BuildPileModel;
import grp5.cdio.solitairesolver.Model.CardColor;
import grp5.cdio.solitairesolver.Model.CardModel;
import grp5.cdio.solitairesolver.Model.FoundationPileModel;
import grp5.cdio.solitairesolver.Model.PossibleCardId;

public class CardController {

    public static int BPILES = 7;
    public static CardController cardController;
    public static ArrayList<FoundationPileModel> foundationPiles;
    public static ArrayList<BuildPileModel> buildPiles; // THESE NEEDS TO BE UPDATED IN A CONTROLLER BEFORE RUNNING

    public static void Init() {
        cardController = GetInstance();
        foundationPiles = InitializeFoundationPiles();
        buildPiles = InitializeBuildPiles();
    }



    public void AddCardToAFoundationPile(CardModel card) {
        if(card.getId().equals(PossibleCardId.Ids.HJERTER.toString())) {
            HandleAdditionToFoundationPiles(card);
        }

        if(card.getId().equals(PossibleCardId.Ids.RUDER.toString())) {
            HandleAdditionToFoundationPiles(card);
        }

        if(card.getId().equals(PossibleCardId.Ids.SPAR.toString())) {
            HandleAdditionToFoundationPiles(card);
        }

        if(card.getId().equals(PossibleCardId.Ids.KLØR.toString())) {
            HandleAdditionToFoundationPiles(card);
        }
    }

    public void HandleAdditionToFoundationPiles(CardModel card) {

        // In case We get an Ace, then add it to a foundation pile
        if(PossibleCardId.convertIdToPoints(card.getValue()) == 1) {
            AddCardToEmptyFoundationPile(card);
        } else {
            // Create an object equal to a foundation pile that exists.
            FoundationPileModel pileWithRightId = GetFoundationPileWithSameId(card);
            // Assert that our new pile is not null
            assert pileWithRightId != null;
            // If our pile is not empty, then add our card to index 0 (Top of the Tree), Pushes the other cards down
            if(!pileWithRightId.isEmpty) {
                pileWithRightId.getPile().add(0, card);
            }
        }
    }

    private void AddCardToEmptyFoundationPile(CardModel card) {
        for (int i = 0; i < foundationPiles.size(); i++) {
            if(foundationPiles.get(i).isEmpty) {
                foundationPiles.get(i).getPile().add(card);
                foundationPiles.get(i).isEmpty = false;
                foundationPiles.get(i).setThisFoundationPile(card.getId());
            }
        }
    }

    public void AddCardToBuildPile(CardModel card) {
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
        // If there aren't any piles containing this Id, then return the Id as Name
        return null;
    }

    public static ArrayList<BuildPileModel> InitializeBuildPiles() {
        ArrayList<BuildPileModel> buildPiles = new ArrayList<>();
        for (int i = 0; i < BPILES-1; i++) {
            buildPiles.add(new BuildPileModel());
        }
        return buildPiles;
    }

    public static ArrayList<FoundationPileModel> InitializeFoundationPiles() {
        ArrayList<FoundationPileModel> foundationPiles = new ArrayList<>();
        FoundationPileModel foundationPile1 = new FoundationPileModel();
        FoundationPileModel foundationPile2 = new FoundationPileModel();
        FoundationPileModel foundationPile3 = new FoundationPileModel();
        FoundationPileModel foundationPile4 = new FoundationPileModel();
        foundationPiles.add(foundationPile1);
        foundationPiles.add(foundationPile2);
        foundationPiles.add(foundationPile3);
        foundationPiles.add(foundationPile4);
        return foundationPiles;
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
