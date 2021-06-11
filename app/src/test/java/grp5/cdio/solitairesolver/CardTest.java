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
        Card cardThreeDiamonds = new Card(Suit.DIAMONDS, FaceValue.THREE);
        Card cardFourSpades = new Card(Suit.SPADES, FaceValue.FOUR);
        Card cardFiveClubs = new Card(Suit.CLUBS, FaceValue.FIVE);
        Card cardSixDiamonds = new Card(Suit.DIAMONDS, FaceValue.SIX);
        Card cardSevenSpades = new Card(Suit.SPADES, FaceValue.SEVEN);
        Card cardEightClubs = new Card(Suit.CLUBS, FaceValue.EIGHT);
        Card cardNineHearts = new Card(Suit.HEARTS, FaceValue.NINE);
        Card cardTenDiamonds = new Card(Suit.DIAMONDS, FaceValue.TEN);
        Card cardJackHearts = new Card(Suit.HEARTS, FaceValue.ELEVEN);
        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardKingHearts = new Card(Suit.HEARTS, FaceValue.THIRTEEN);
        Card cardUnknown = new Card();


        assertTrue(cardOneHearts.isRed());
        assertTrue(cardTwoClubs.isBlack());
        assertTrue(cardThreeDiamonds.isRed());
        assertTrue(cardFourSpades.isBlack());
        assertTrue(cardFiveClubs.isBlack());

        assertFalse(cardTwoClubs.isRed());
        assertFalse(cardTwoHearts.isBlack());
        assertFalse(cardThreeDiamonds.isBlack());
        assertFalse(cardFourSpades.isRed());
        assertFalse(cardFiveClubs.isRed());

        assertFalse(cardUnknown.isBlack());
        assertFalse(cardUnknown.isRed());

        assertEquals(0, cardUnknown.getIntValue());
        assertEquals(1, cardOneHearts.getIntValue());
        assertEquals(2, cardTwoClubs.getIntValue());
        assertEquals(3, cardThreeDiamonds.getIntValue());
        assertEquals(4, cardFourSpades.getIntValue());
        assertEquals(5, cardFiveClubs.getIntValue());
        assertEquals(6, cardSixDiamonds.getIntValue());
        assertEquals(7, cardSevenSpades.getIntValue());
        assertEquals(8, cardEightClubs.getIntValue());
        assertEquals(9, cardNineHearts.getIntValue());
        assertEquals(10, cardTenDiamonds.getIntValue());
        assertEquals(11, cardJackHearts.getIntValue());
        assertEquals(12, cardQueenHearts.getIntValue());
        assertEquals(13, cardKingHearts.getIntValue());

    }



}