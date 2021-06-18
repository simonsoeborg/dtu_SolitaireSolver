package grp5.cdio.solitairesolver;

import org.junit.Test;

import grp5.cdio.solitairesolver.Model.BasePile;
import grp5.cdio.solitairesolver.Model.BuildPile;
import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.GroundPile;
import grp5.cdio.solitairesolver.Model.Pile;
import grp5.cdio.solitairesolver.Model.Suit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PileTest {
    @Test
    public void testToString() {
        Pile pile = new BuildPile(2);
        Card cardOneHearts = new Card(Suit.HEARTS, FaceValue.ONE);
        pile.setCard(0,cardOneHearts);
        assertEquals("Hjerter Es, Ukendt Ukendt, ", pile.toString());

    }

    @Test
    public void testIsEqual() {
        Pile pile = new BuildPile(2);
        Card cardOneHearts = new Card(Suit.HEARTS, FaceValue.ONE);
        pile.setCard(1,cardOneHearts);
        Card cardOneHeartsv2 = new Card(Suit.HEARTS, FaceValue.ONE);
        assertTrue(pile.isEqual(cardOneHeartsv2));
    }


    @Test
    public void testIsLegalMoveBuild() {
        Pile pileTo = new BuildPile(1);
        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardKingClubs = new Card(Suit.CLUBS, FaceValue.THIRTEEN);
        Card cardOneHearts = new Card(Suit.HEARTS, FaceValue.ONE);
        Card cardQueenClubs = new Card(Suit.CLUBS, FaceValue.TWELVE);
        pileTo.setCard(0,cardKingClubs);

        assertTrue(pileTo.isLegalMove(cardQueenHearts));
        assertFalse(pileTo.isLegalMove(cardOneHearts));
        assertFalse(pileTo.isLegalMove(cardQueenClubs));

        Pile emptypile = new BuildPile(0);

        assertFalse(emptypile.isLegalMove(cardOneHearts));
        assertTrue(emptypile.isLegalMove(cardKingClubs));




    }

    @Test
    public void testIsLegalMoveGround() {

        Pile pileTo = new GroundPile(1);
        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardKingHearts = new Card(Suit.HEARTS, FaceValue.THIRTEEN);
        Card cardOneHearts = new Card(Suit.HEARTS, FaceValue.ONE);
        Card cardKingClubs = new Card(Suit.CLUBS, FaceValue.THIRTEEN);
        Card cardKingDiamond = new Card(Suit.DIAMONDS, FaceValue.THIRTEEN);

        pileTo.setCard(0,cardQueenHearts);

        assertTrue(pileTo.isLegalMove(cardKingHearts));
        assertFalse(pileTo.isLegalMove(cardOneHearts));
        assertFalse(pileTo.isLegalMove(cardKingClubs));
        assertFalse(pileTo.isLegalMove(cardKingDiamond));

        Pile emptypile = new GroundPile(0);

        assertTrue(emptypile.isLegalMove(cardOneHearts));
        assertFalse(emptypile.isLegalMove(cardKingClubs));

    }

    @Test
    public void testIsLegalMoveBase(){
        Pile pileTo = new BasePile(1);

        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardKingHearts = new Card(Suit.HEARTS, FaceValue.THIRTEEN);
        Card cardJackClubs = new Card(Suit.CLUBS, FaceValue.ELEVEN);

        pileTo.setCard(0,cardQueenHearts);

        assertFalse(pileTo.isLegalMove(cardKingHearts));

        assertFalse(pileTo.isLegalMove(cardJackClubs));



    }

    @Test
    public void testIsLegalMove() {
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
        Card cardJackClubs = new Card(Suit.CLUBS, FaceValue.ELEVEN);
        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardKingClubs = new Card(Suit.CLUBS, FaceValue.THIRTEEN);
        Card cardUnknown = new Card();

        /*assertTrue(cardOneHearts.isLegalMove(cardTwoClubs));
        assertTrue(cardThreeDiamonds.isLegalMove(cardFourSpades));
        assertTrue(cardFiveClubs.isLegalMove(cardSixDiamonds));
        assertTrue(cardTenDiamonds.isLegalMove(cardJackClubs));
        assertTrue(cardJackClubs.isLegalMove(cardQueenHearts));
        assertTrue(cardQueenHearts.isLegalMove(cardKingClubs));
        assertTrue(cardKingClubs.isLegalMove(null));

        assertFalse(cardOneHearts.isLegalMove(cardTwoHearts));
        assertFalse(cardOneHearts.isLegalMove(cardUnknown));
        assertFalse(cardSevenSpades.isLegalMove(cardEightClubs));
        assertFalse(cardNineHearts.isLegalMove(cardTenDiamonds));*/
    }

}
