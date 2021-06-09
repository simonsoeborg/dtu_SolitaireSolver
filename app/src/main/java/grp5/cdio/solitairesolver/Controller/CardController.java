package grp5.cdio.solitairesolver.Controller;

import java.util.ArrayList;

import grp5.cdio.solitairesolver.Model.BuildPileModel;
import grp5.cdio.solitairesolver.Model.CardModel;
import grp5.cdio.solitairesolver.Model.FoundationPileModel;

public class CardController {

    public static int BPILES = 7;
    public static int FPILES = 4;
    public static CardController cardController;
    public ArrayList<FoundationPileModel> fList;
    public ArrayList<BuildPileModel> bList; // THESE NEEDS TO BE UPDATED IN A CONTROLLER BEFORE RUNNING

    public void Init() {
        cardController = GetInstance();
        fList = InitializeFoundationPiles();
        bList = InitializeBuildPiles();
    }

    public void newCard(String Value, String Id) {
        // Value == A, 2, 3 .. Q, K
        // Id == { HJERTER, SPAR, RUDER, KLØR }

        // If Value == A / 1
        if(CardSpecifications.convertValueToPoints(Value) == 1) {
            for (FoundationPileModel element : fList) {
                if(element.isEmpty) {
                    AddToFoundationPile(new CardModel(Id, Value), element);
                    element.isEmpty = false;
                    break;
                }
            }
        }
        // If Value == K
        else if(CardSpecifications.convertValueToPoints(Value) == 13) {
            for (BuildPileModel element : bList) {
                if(element.isEmpty) {
                    AddToBuildPile(new CardModel(Id, Value), element);
                    element.isEmpty = false;
                    break;
                }
            }
        }
        // Else
        else {
            // Look if possible to add to Build pile
            for (BuildPileModel element : bList) {
                // If the list is not empty
                if(!element.isEmpty) {
                    // Get the last card in the pile
                    int listSize = element.getPile().size();
                    CardModel lastCardInPile = element.getPile().get(listSize-1);
                    // If the current card has opposit card color than last Card in pile
                    if(!CardColor.GetCardColor(new CardModel(Id, Value)).equals(CardColor.GetCardColor(lastCardInPile))) {
                        // If the current card has a lesser value than the last Card in pile
                        if(CardSpecifications.convertValueToPoints(Value) == CardSpecifications.convertValueToPoints(lastCardInPile.getValue()) - 1) {
                            AddToBuildPile(new CardModel(Id, Value), element);
                            break;
                        }
                    }
                }
            }
            // Check for possibility to add to foundation pile
            for (FoundationPileModel element : fList) {
                if(!element.isEmpty) {
                    // We always add the newest card to a foundation pile on index 0, meaning we can always check index 0, for color and value
                    if(CardColor.GetCardColor(new CardModel(Id, Value)).equals(CardColor.GetCardColor(element.getPile().get(0)))) {
                        if(CardSpecifications.convertValueToPoints(Value) > CardSpecifications.convertValueToPoints(element.getPile().get(0).getValue())) {
                            AddToFoundationPile(new CardModel(Id, Value), element);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void AddToBuildPile(CardModel card, BuildPileModel buildPile) {
        buildPile.getPile().add(card);
    }

    private void AddToFoundationPile(CardModel card, FoundationPileModel foundationPile) {
        // Add to the top of the pile = index 0
        if(foundationPile.isEmpty) {
            foundationPile.getPile().add(card);
        } else {
            foundationPile.getPile().add(0, card);
        }
    }

    private static ArrayList<BuildPileModel> InitializeBuildPiles() {
        ArrayList<BuildPileModel> buildPiles = new ArrayList<>();
        for (int i = 0; i < BPILES; i++) {
            buildPiles.add(new BuildPileModel());
            buildPiles.get(i).setPile(new ArrayList<>());
        }
        return buildPiles;
    }

    private static ArrayList<FoundationPileModel> InitializeFoundationPiles() {
        ArrayList<FoundationPileModel> foundationPiles = new ArrayList<>();
        for (int i = 0; i < FPILES; i++) {
            foundationPiles.add(new FoundationPileModel());
            foundationPiles.get(i).setPile(new ArrayList<>());
        }
        return foundationPiles;
    }

    // Singleton method
    public static CardController GetInstance() {
        if(cardController == null) {
            return new CardController();
        } else {
            return cardController;
        }
    }



}

class CardSpecifications {
    // Convert Cards to int Values. Specificly the cards that has letters instead of numeric values
    public static int convertValueToPoints(String cardValue) {
        switch (cardValue) {
            case "K": return 13;
            case "Q": return 12;
            case "J": return 11;
            case "A": return 1;
            default:
                return Integer.parseInt(cardValue);
        }
    }

    public enum Ids {
        HJERTER,
        RUDER,
        SPAR,
        KLØR
    }
}

class CardColor {
    // Define colors
    private final static String RED = "RED";
    private final static String BLACK = "BLACK";

    // Return the card color based on the Card Id
    public static String GetCardColor(CardModel card) {

        if(card.getId().equals(CardSpecifications.Ids.HJERTER.toString())){
            return RED;
        }

        if(card.getId().equals(CardSpecifications.Ids.RUDER.toString())){
            return RED;
        }

        if(card.getId().equals(CardSpecifications.Ids.SPAR.toString())){
            return BLACK;
        }

        if(card.getId().equals(CardSpecifications.Ids.KLØR.toString())){
            return BLACK;
        }

        return null;
    }
}
