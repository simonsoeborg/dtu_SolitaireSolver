package grp5.cdio.solitairesolver;

import org.junit.Test;

import java.util.ArrayList;

import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Move;
import grp5.cdio.solitairesolver.Model.Suit;
import grp5.cdio.solitairesolver.Model.Table;

public class TableTest {

    @Test
    // Test simple table setup
    public void testSimple() {
        Table table = new Table();
        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardKingClubs = new Card(Suit.CLUBS, FaceValue.THIRTEEN);

        Card cardTenHearts = new Card(Suit.HEARTS, FaceValue.TEN);
        Card cardJackClubs = new Card(Suit.CLUBS, FaceValue.ELEVEN);

        Card cardFiveHearts = new Card(Suit.HEARTS, FaceValue.FIVE);
        Card cardFiveClubs = new Card(Suit.CLUBS, FaceValue.FIVE);

        table.buildPile.get(0).setCard(0, cardQueenHearts);
        table.buildPile.get(1).setCard(1, cardKingClubs);
        table.buildPile.get(2).setCard(2, cardTenHearts);
        table.buildPile.get(3).setCard(3, cardJackClubs);
        table.buildPile.get(4).setCard(4,cardFiveClubs);
        table.buildPile.get(5).setCard(5, cardFiveHearts);

        ArrayList<Move> moves = table.getLegalMoves();
        Move move = table.getBestMove(moves);
        System.out.println(move);
//        TODO setup table(ALL cards)


    }
}
