package grp5.cdio.solitairesolver;

import org.junit.Test;

import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Suit;

import static org.junit.Assert.assertEquals;

public class CardTest {
    @Test
    public void testToString() {
        Card cardOneHearts = new Card(Suit.HEARTS, FaceValue.ONE);
        Card cardUnknown = new Card();
        System.out.println(cardOneHearts.toString());
        System.out.println(cardOneHearts.toString());
        //assertEquals(4, 2 + 2);
    }

    @Test
    public void testIsEqual() {

        //assertEquals(4, 2 + 2);
    }

    @Test
    public void testIsLegalMove() {

        //assertEquals(4, 2 + 2);
    }

}
