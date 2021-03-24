package grp5.cdio.solitairesolver;

import org.junit.Test;

import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Suit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CardTest {
    @Test
    public void testToString() {
        Card cardOneHearts = new Card(Suit.HEARTS, FaceValue.ONE);
        Card cardUnknown = new Card();
        assertEquals("[HEARTS-ONE]", cardOneHearts.toString());
        assertEquals("[X-X]", cardUnknown.toString());
    }

    @Test
    public void testIsEqual() {
        Card cardOneHeartsv1 = new Card(Suit.HEARTS, FaceValue.ONE);
        Card cardOneHeartsv2 = new Card(Suit.HEARTS, FaceValue.ONE);
        Card cardTwoHearts = new Card(Suit.HEARTS, FaceValue.TWO);
        Card cardUnknown = new Card();

        assertTrue(cardOneHeartsv1.isEqual(cardOneHeartsv2));
        assertFalse(cardOneHeartsv1.isEqual(cardTwoHearts));
        assertFalse(cardOneHeartsv1.isEqual(cardUnknown));

    }

    @Test
    public void testSuitValue() {
        Card cardOneHearts = new Card(Suit.HEARTS, FaceValue.ONE);
        Card cardTwoClubs = new Card(Suit.CLUBS, FaceValue.TWO);
        Card cardTwoHearts = new Card(Suit.HEARTS, FaceValue.TWO);
        Card cardUnknown = new Card();


        assertTrue(cardOneHearts.isRed());
        assertTrue(cardTwoClubs.isBlack());
        assertFalse(cardTwoClubs.isRed());
        assertFalse(cardTwoHearts.isBlack());
        assertFalse(cardUnknown.isBlack());
        assertFalse(cardUnknown.isRed());

        assertEquals(0, cardUnknown.getIntValue());
        assertEquals(1, cardOneHearts.getIntValue());
        assertEquals(2, cardTwoClubs.getIntValue());

    }


    @Test
    public void testIsLegalMove() {
        Card cardOneHearts = new Card(Suit.HEARTS, FaceValue.ONE);
        Card cardTwoClubs = new Card(Suit.CLUBS, FaceValue.TWO);
        Card cardTwoHearts = new Card(Suit.HEARTS, FaceValue.TWO);
        Card cardUnknown = new Card();

        assertTrue(cardOneHearts.isLegalMove(cardTwoClubs));
        assertFalse(cardOneHearts.isLegalMove(cardTwoHearts));
        assertFalse(cardOneHearts.isLegalMove(cardUnknown));
    }
}
