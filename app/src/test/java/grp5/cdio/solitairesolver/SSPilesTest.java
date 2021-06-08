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
        CardModel cardModel = new CardModel("HEARTS", "A");
        cardController.AddCardToAFoundationPile(cardModel);
        System.out.println(CardController.foundationPiles.get(0).getPile());
    }
}
