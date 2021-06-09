package grp5.cdio.solitairesolver;

import org.junit.Test;

import grp5.cdio.solitairesolver.Controller.CardController;
import grp5.cdio.solitairesolver.Model.BuildPileModel;
import grp5.cdio.solitairesolver.Model.CardModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SSPilesTest {
    @Test
    public void TestFoundationPiles() {
        CardController cardController = CardController.GetInstance();
        cardController.Init();
        System.out.println("Size of FoundationPiles: " + cardController.fList.size());

        // Simulate the data coming from the tensorflow code:
        cardController.newCard("A", "HJERTER");
        cardController.newCard("A", "SPAR");
        cardController.newCard("A", "KLØR");
        cardController.newCard("A", "RUDER");
        cardController.newCard("2", "HJERTER");
        cardController.newCard("2", "SPAR");

        System.out.println("Size of first foundationpile: " + cardController.fList.get(0).getPile().size());
        System.out.println("Size of second foundationpile: " + cardController.fList.get(1).getPile().size());
        System.out.println("Size of third foundationpile: " + cardController.fList.get(2).getPile().size());
        System.out.println("Size of fourth foundationpile: " + cardController.fList.get(3).getPile().size());

        System.out.println("Elements in First FoundationPile: " + cardController.fList.get(0).getPile().get(0).getId() +
                cardController.fList.get(0).getPile().get(0).getValue() +
                " & " + cardController.fList.get(0).getPile().get(1).getId() + cardController.fList.get(0).getPile().get(1).getValue());
    }

    @Test
    public void TestBuildPiles() {
        CardController cardController = CardController.GetInstance();
        cardController.Init();
        System.out.println("Size of Build piles: " + cardController.bList.size());

        cardController.newCard("K", "HJERTER");
        cardController.newCard("Q", "HJERTER");
        cardController.newCard("Q", "SPAR");
        cardController.newCard("J", "RUDER");
        cardController.newCard("K", "SPAR");
        cardController.newCard("Q", "HJERTER");
        cardController.newCard("J", "KLØR");
        cardController.newCard("10", "RUDER");
        cardController.newCard("9", "SPAR");

        int i = 0;
        for (BuildPileModel var : cardController.bList) {
            System.out.println("Size of " + (i+1) + " build pile: " + cardController.bList.get(i).getPile().size());
            i++;
        }

        for (BuildPileModel var : cardController.bList) {
            if (!var.isEmpty) {
                for (CardModel item : var.getPile()) {
                    System.out.println(item.getValue() + " " + item.getId());
                }
            }
        }
    }
}
