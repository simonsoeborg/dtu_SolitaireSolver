package grp5.cdio.solitairesolver;

import org.junit.Test;

import java.util.ArrayList;

import grp5.cdio.solitairesolver.Controller.Controller;
import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Move;
import grp5.cdio.solitairesolver.Model.Suit;
import grp5.cdio.solitairesolver.Model.Table;

public class TableTest {

    @Test
    // Test simple table setup
    public void testSimpleToBuildPile() {
        Table table = setUpSimpleTable();
        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardKingClubs = new Card(Suit.CLUBS, FaceValue.THIRTEEN);

        Card cardTenHearts = new Card(Suit.HEARTS, FaceValue.TEN);
        Card cardJackClubs = new Card(Suit.CLUBS, FaceValue.ELEVEN);

        table.buildPile.get(0).setCard(0, cardQueenHearts);
        table.buildPile.get(1).setCard(1, cardKingClubs);
        table.buildPile.get(2).setCard(2, cardTenHearts);
        table.buildPile.get(3).setCard(3, cardJackClubs);

        ArrayList<Move> moves = table.getLegalMoves();
        Move move = table.getBestMove(moves);
        System.out.println(table);
        System.out.println(move);

    }
    @Test
    // Test simple table setup, move king
    public void testSimpleKingToBuildPile() {
        Table table = setUpSimpleTable();
        Card cardKingClubs = new Card(Suit.CLUBS, FaceValue.THIRTEEN);

        Card cardTenHearts = new Card(Suit.HEARTS, FaceValue.TEN);
        Card cardJackClubs = new Card(Suit.CLUBS, FaceValue.ELEVEN);

        table.buildPile.get(0).removeCard();
        table.buildPile.get(1).setCard(1, cardKingClubs);
        table.buildPile.get(2).setCard(2, cardTenHearts);
        table.buildPile.get(3).setCard(3, cardJackClubs);

        ArrayList<Move> moves = table.getLegalMoves();
        Move move = table.getBestMove(moves);
        System.out.println(table);
        System.out.println(move);
    }
    @Test
    // Test simple table setup, move Ace
    public void testSimpleToGroundPile() {
        Table table = setUpSimpleTable();
        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardKingClubs = new Card(Suit.CLUBS, FaceValue.THIRTEEN);

        Card cardTenHearts = new Card(Suit.HEARTS, FaceValue.TEN);
        Card cardJackClubs = new Card(Suit.CLUBS, FaceValue.ELEVEN);

        table.buildPile.get(0).setCard(0, cardQueenHearts);
        table.buildPile.get(1).setCard(1, cardKingClubs);
        table.buildPile.get(2).setCard(2, cardTenHearts);
        table.buildPile.get(3).setCard(3, cardJackClubs);

        ArrayList<Move> moves = table.getLegalMoves();
        Move move = table.getBestMove(moves);
        System.out.println(table);
        System.out.println(move);
    }
    @Test
    // Test simple table setup, move Ace
    public void testSimpleGame() {
        Table table = setUpSimpleTable();

        Card cardAceHearts = new Card(Suit.HEARTS, FaceValue.ONE);
        Card cardTwoHearts = new Card(Suit.HEARTS, FaceValue.TWO);
        table.buildPile.get(6).setCard(6, cardAceHearts);

        ArrayList<Move> moves = table.getLegalMoves();
        Move move = table.getBestMove(moves);
        System.out.println(table);
        System.out.println(move);
        move.makeMove();
        System.out.println(table);

        table.buildPile.get(6).setCard(5, cardTwoHearts);
        moves = table.getLegalMoves();
        move = table.getBestMove(moves);
        System.out.println(table);
        System.out.println(move);

    }
    private Table setUpSimpleTable(){
        Table table = new Table();
        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardKingHearts = new Card(Suit.HEARTS, FaceValue.THIRTEEN);

        Card cardTenHearts = new Card(Suit.HEARTS, FaceValue.TEN);
        Card cardJackHearts = new Card(Suit.HEARTS, FaceValue.ELEVEN);

        Card cardFiveHearts = new Card(Suit.HEARTS, FaceValue.FIVE);
        Card cardFiveClubs = new Card(Suit.CLUBS, FaceValue.FIVE);
        Card cardFiveSpades = new Card(Suit.SPADES, FaceValue.FIVE);

        table.buildPile.get(0).setCard(0, cardQueenHearts);
        table.buildPile.get(1).setCard(1, cardKingHearts);
        table.buildPile.get(2).setCard(2, cardTenHearts);
        table.buildPile.get(3).setCard(3, cardJackHearts);
        table.buildPile.get(4).setCard(4, cardFiveClubs);
        table.buildPile.get(5).setCard(5, cardFiveHearts);
        table.buildPile.get(6).setCard(6, cardFiveSpades);
        return table;
    }
}
