/*
Author: Elinor Mohr Mikkelsen
Collaborator(s): Simon Fridolf, Simon Søborg
 */
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

        table.buildPile.get(0).setCard(0,cardQueenHearts);
        table.buildPile.get(1).setCard(0,cardKingClubs);
        table.buildPile.get(2).setCard(0,cardTenHearts);
        table.buildPile.get(3).setCard(0,cardJackClubs);

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

        table.buildPile.get(1).addCard(cardKingClubs);
        table.buildPile.get(2).addCard(cardTenHearts);
        table.buildPile.get(3).addCard(cardJackClubs);

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

        table.buildPile.get(0).addCard(cardQueenHearts);
        table.buildPile.get(1).addCard(cardKingClubs);
        table.buildPile.get(2).addCard(cardTenHearts);
        table.buildPile.get(3).addCard(cardJackClubs);

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
        table.buildPile.get(6).addCard(cardAceHearts);

        ArrayList<Move> moves = table.getLegalMoves();
        Move move = table.getBestMove(moves);
        System.out.println(table);
        System.out.println(move);
        move.makeMove();
        System.out.println(table);

        table.buildPile.get(6).addCard(cardTwoHearts);
        moves = table.getLegalMoves();
        move = table.getBestMove(moves);
        System.out.println(table);
        System.out.println(move);

    }

    @Test
    public void printSimpleGame() {
        Table table = setUpSimpleTable();

        Card cardAceHearts = new Card(Suit.HEARTS, FaceValue.ONE);
        Card cardTwoHearts = new Card(Suit.HEARTS, FaceValue.TWO);
        table.buildPile.get(6).setCard(0,cardAceHearts);

        ArrayList<Move> moves = null;
        Move move = null;
        System.out.println(table);

        moves = table.getLegalMoves();
        move = table.getBestMove(moves);
        System.out.println(move);
        move.makeMove();


        moves = table.getLegalMoves();
        move = table.getBestMove(moves);
        System.out.println(move);
        move.makeMove();


        moves = table.getLegalMoves();
        move = table.getBestMove(moves);
        System.out.println(move);
        move.makeMove();

        moves = table.getLegalMoves();
        move = table.getBestMove(moves);
        System.out.println(move);

        System.out.println();
        System.out.println(table);



    }

    private Table setUpSimpleTable(){
        Table table = new Table();
        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardSixHearts = new Card(Suit.HEARTS, FaceValue.SIX);

        Card cardQueenClubs = new Card(Suit.CLUBS, FaceValue.TEN);
        Card cardJackHearts = new Card(Suit.HEARTS, FaceValue.ELEVEN);

        Card cardFiveHearts = new Card(Suit.HEARTS, FaceValue.FIVE);
        Card cardFiveClubs = new Card(Suit.CLUBS, FaceValue.FIVE);
        Card cardFiveSpades = new Card(Suit.SPADES, FaceValue.FIVE);

        table.buildPile.get(0).addCard(cardQueenHearts);
        table.buildPile.get(1).addCard(cardSixHearts);
        table.buildPile.get(2).addCard(cardQueenClubs);
        table.buildPile.get(3).addCard(cardJackHearts);
        table.buildPile.get(4).addCard(cardFiveClubs);
        table.buildPile.get(5).addCard( cardFiveHearts);
        table.buildPile.get(6).addCard(cardFiveSpades);
        return table;
    }
}
