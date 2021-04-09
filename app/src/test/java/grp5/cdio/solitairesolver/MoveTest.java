package grp5.cdio.solitairesolver;

import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Move;
import grp5.cdio.solitairesolver.Model.Suit;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MoveTest {
    @Test
    public void addition_isCorrect() {

        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardKingClubs = new Card(Suit.CLUBS, FaceValue.THIRTEEN);
        Card cardKingSpades = new Card(Suit.SPADES, FaceValue.THIRTEEN);


        //Move move = new Move();
        //Move newMove = new Move(cardKingClubs,cardKingSpades, 0, cardQueenHearts);
        //Move oldMove = new Move(cardKingSpades, cardKingClubs, 0, cardQueenHearts);


        //assertTrue(oldMove.isInverseMove(newMove));
    }
}