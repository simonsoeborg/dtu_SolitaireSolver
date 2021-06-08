package grp5.cdio.solitairesolver;

import org.junit.Test;

import grp5.cdio.solitairesolver.Controller.CardController;
import grp5.cdio.solitairesolver.Model.CardModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SSPilesTest {
    @Test
    public void TestFoundationPiles() {
        CardController cardController = CardController.GetInstance();
        cardController.Init();
        CardModel cardModel = new CardModel("HJERTER", "A");
        cardController.AddCardToAFoundationPile(cardModel);
        for (int i = 0; i < CardController.foundationPiles.size(); i++) {
            if(!CardController.foundationPiles.get(i).isEmpty) {
                System.out.println(CardController.foundationPiles.get(i).getPile().get(0).getId());
                System.out.println(CardController.foundationPiles.get(i).getPile().get(0).getValue());
            }
        }
    }

    @Test
    public void TestBuildPiles() {
        CardModel cardModel = new CardModel("HJERTER", "K");
        CardController cardController = CardController.GetInstance();
        CardController.Init();
        cardController.AddCardToBuildPile(cardModel);
        for (int i = 0; i < CardController.buildPiles.size(); i++) {
            if(!CardController.buildPiles.get(i).BuildPile.isEmpty()) {
                System.out.println(CardController.buildPiles.get(i).BuildPile.get(0).getId());
                System.out.println(CardController.buildPiles.get(i).BuildPile.get(0).getValue());
            }
        }
    }
}
