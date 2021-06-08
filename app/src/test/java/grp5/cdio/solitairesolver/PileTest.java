/*
package grp5.cdio.solitairesolver;

import org.junit.Test;

import grp5.cdio.solitairesolver.Model.CardModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PileTest {
    @Test
    public void testToString() {
        Pile pile = new BuildPile(2);
        CardModel cardOneHearts = new CardModel(Suit.HEARTS, FaceValue.ONE);
        pile.setCard(0,cardOneHearts);
        assertEquals("[HEARTS-ONE][X-X]", pile.toString());

    }

    @Test
    public void testIsEqual() {
        Pile pile = new BuildPile(2);
        CardModel cardOneHearts = new CardModel(Suit.HEARTS, FaceValue.ONE);
        pile.setCard(1,cardOneHearts);
        CardModel cardOneHeartsv2 = new CardModel(Suit.HEARTS, FaceValue.ONE);
        assertTrue(pile.isEqual(cardOneHeartsv2));
    }


    @Test
    public void testIsLegalMove() {
        Pile pileTo = new BuildPile(1);
        CardModel cardQueenHearts = new CardModel(Suit.HEARTS, FaceValue.TWELVE);
        CardModel cardKingClubs = new CardModel(Suit.CLUBS, FaceValue.THIRTEEN);
        CardModel cardOneHearts = new CardModel(Suit.HEARTS, FaceValue.ONE);
        pileTo.setCard(0,cardKingClubs);

        assertTrue(pileTo.isLegalMove(cardQueenHearts));
        assertFalse(pileTo.isLegalMove(cardOneHearts));


        pileTo = new GroundPile(1);
        cardQueenHearts = new CardModel(Suit.HEARTS, FaceValue.TWELVE);
        CardModel cardKingHearts = new CardModel(Suit.HEARTS, FaceValue.THIRTEEN);
        cardOneHearts = new CardModel(Suit.HEARTS, FaceValue.ONE);
        pileTo.setCard(0,cardQueenHearts);

        assertTrue(pileTo.isLegalMove(cardKingHearts));
        assertFalse(pileTo.isLegalMove(cardOneHearts));

    }
}
*/
