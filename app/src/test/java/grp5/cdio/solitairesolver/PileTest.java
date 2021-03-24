package grp5.cdio.solitairesolver;

import org.junit.Test;

import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Pile;
import grp5.cdio.solitairesolver.Model.Suit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PileTest {
    @Test
    public void testToString() {
        Pile pile = new Pile(2);
        Card cardOneHearts = new Card(Suit.HEARTS, FaceValue.ONE);
        pile.setCard(0,cardOneHearts);
        assertEquals("[HEARTS-ONE][X-X]", pile.toString());

    }

    @Test
    public void testIsEqual() {
        Pile pile = new Pile(2);
        Card cardOneHearts = new Card(Suit.HEARTS, FaceValue.ONE);
        pile.setCard(1,cardOneHearts);
        Card cardOneHeartsv2 = new Card(Suit.HEARTS, FaceValue.ONE);
        assertTrue(pile.isEqual(cardOneHeartsv2));
    }


    @Test
    public void testIsLegalMove() {
        Pile pileTo = new Pile(1);
        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardKingClubs = new Card(Suit.CLUBS, FaceValue.THIRTEEN);
        Card cardOneHearts = new Card(Suit.HEARTS, FaceValue.ONE);
        pileTo.setCard(0,cardKingClubs);

        assertTrue(pileTo.isLegalMove(cardQueenHearts));
        assertFalse(pileTo.isLegalMove(cardOneHearts));
    }
}
