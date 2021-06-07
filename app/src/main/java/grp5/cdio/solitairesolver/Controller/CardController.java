package grp5.cdio.solitairesolver.Controller;

import java.util.ArrayList;

import grp5.cdio.solitairesolver.CardControl;
import grp5.cdio.solitairesolver.Model.BuildPileModel;
import grp5.cdio.solitairesolver.Model.CardModel;
import grp5.cdio.solitairesolver.Model.FoundationPileModel;
import grp5.cdio.solitairesolver.Model.PossibleCardId;

public class CardController {

    public static CardController cardController;
    private BuildPileModel buildPile;
    private FoundationPileModel foundationPile;
    private ArrayList<BuildPileModel> buildPiles;
    private ArrayList<FoundationPileModel> foundationPiles;

    public CardController() {
    }

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
            // Check if we have a pile for this Id
            if(CheckPilesForId(card) == null) {
                // Check if Card is Ace, if true, create Pile
            } else {
                // Check predecessor and Successor and then add to pile
            }
        }

        if(card.getId().equals(PossibleCardId.Ids.RUDER.toString())) {

        }

        if(card.getId().equals(PossibleCardId.Ids.SPAR.toString())) {

        }

        if(card.getId().equals(PossibleCardId.Ids.KLØR.toString())) {

        }

    }

    public void OnAddCardToBuildPile(CardModel card) {

    }

    public ArrayList<CardModel> CheckPilesForId(CardModel card) {
        for (FoundationPileModel item : cardController.foundationPiles) {
            if (item.GetFoundationTypeId().equals(card.getId())) {
                return item.FoundationPile;
            }
        }

        return null;
    }

    public static CardController GetInstance() {
        if(cardController == null) {
            return new CardController();
        } else {
            return cardController;
        }
    }

}
