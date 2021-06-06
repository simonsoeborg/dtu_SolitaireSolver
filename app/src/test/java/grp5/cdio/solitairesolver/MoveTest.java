package grp5.cdio.solitairesolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MoveTest {
//    @Test
//    public void testIsInverseMove() {
//
//        Pile pileTo = new BuildPile(1);
//        Pile pileToNew = new BuildPile(1);
//        Pile pileFrom = new BuildPile(1);
//
//        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
//        Card cardKingClubs = new Card(Suit.CLUBS, FaceValue.THIRTEEN);
//        Card cardKingSpades = new Card(Suit.SPADES, FaceValue.THIRTEEN);
//        pileTo.setCard(0,cardKingClubs);
//        pileFrom.setCard(0,cardKingSpades);
//        pileToNew.setCard(0, cardKingSpades);
//
//        Move newMove = new Move(pileFrom, pileTo, cardQueenHearts);
//        Move oldMove = new Move(pileTo, pileFrom, cardQueenHearts);
//        assertTrue(oldMove.isInverseMove(newMove));
//
//
//        newMove = new Move(pileFrom, pileToNew, cardQueenHearts);
//        oldMove = new Move(pileTo, pileFrom, cardQueenHearts);
//        assertFalse(oldMove.isInverseMove(newMove));
//    }
//
//    @Test
//    public void testScore() {
//
//        Pile pileTo = new BuildPile(1);
//        Pile pileFrom = new BuildPile(1);
//
//        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
//        Card cardKingClubs = new Card(Suit.CLUBS, FaceValue.THIRTEEN);
//        Card cardKingSpades = new Card(Suit.SPADES, FaceValue.THIRTEEN);
//        pileTo.setCard(0,cardKingClubs);
//        pileFrom.setCard(0,cardKingSpades);
//
//        Move newMove = new Move(pileFrom, pileTo, cardQueenHearts);
//        Move oldMove = new Move(pileTo, pileFrom, cardQueenHearts);
//        assertEquals(newMove.getScore(), oldMove.getScore());
//
//
//       pileTo = new GroundPile(0);
//       pileFrom = new BuildPile(1);
//
//        Card cardAceHearts = new Card(Suit.HEARTS, FaceValue.ONE);
//        Move move = new Move(pileFrom, pileTo, cardAceHearts);
//        assertEquals(96, move.getScore());
//
//        Card cardTwoHearts = new Card(Suit.HEARTS, FaceValue.TWO);
//        move = new Move(pileFrom, pileTo, cardTwoHearts);
//        assertEquals(86, move.getScore());
//
//        move = new Move(pileFrom, pileTo, cardQueenHearts);
//        assertEquals(53, move.getScore());
//
//
//        pileTo = new BuildPile(0);
//        pileFrom = new BuildPile(1);
//
//        move = new Move(pileFrom, pileTo, cardKingClubs);
//        assertEquals(76, move.getScore());
//
//        pileTo = new GroundPile(0);
//        pileFrom = new BasePile(1);
//        move = new Move(pileFrom, pileTo, cardKingClubs);
//        assertEquals(34, move.getScore());
//
//
//        pileTo = new BuildPile(0);
//        pileFrom = new BasePile(1);
//        move = new Move(pileFrom, pileTo, cardKingClubs);
//        assertEquals(24, move.getScore());
//
//
//
//    }
}