package grp5.cdio.solitairesolver;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import grp5.cdio.solitairesolver.Model.BasePile;
import grp5.cdio.solitairesolver.Model.BuildPile;
import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.GroundPile;
import grp5.cdio.solitairesolver.Model.Move;
import grp5.cdio.solitairesolver.Model.Suit;
import grp5.cdio.solitairesolver.Model.Table;

public class GameTest {
    private int lost = 0;
    private int won = 0;

    @Test
    public void testGame(){
        for (int j = 0 ; j < 1; j ++){
            runGame();
        }

        System.out.println("Won: " + won);
        System.out.println("Lost: " + lost);
    }


    public void runGame() {
        ArrayList<Card> cards = makeCards();
        Collections.shuffle(cards);
        ArrayList<Card> cardsOnBoard = new ArrayList<Card>(cards.subList(0, 7)); // 7 cards
        ArrayList<Card> cardsHiddenOnBoard = new ArrayList<Card>(cards.subList(7, 28)); // 21 cards
        ArrayList<Card> cardsInDraw = new ArrayList<Card>(cards.subList(28, 52)); // 24 cards
        ArrayList<Card> cardsInDiscard = new ArrayList<>();

        Table table = makeTestTable(cardsOnBoard);
            for (int i = 0; i < 10000; i++) {
                ArrayList<Move> legalMoves = table.getLegalMoves();
                Move move = table.getBestMove(legalMoves);

                Card cardToAddToBoard = null;
                if (move.getScore() == -1 || move.getScore() == 5) {
                    if (cardsInDraw.isEmpty()) {
                        lost++;
                        System.out.println("lost");
                        break;
                    }
                    cardToAddToBoard = cardsInDraw.get(0);
                    cardsInDiscard.add(cardToAddToBoard);
                    cardsInDraw.remove(cardToAddToBoard);
                } else {
                    if (move.moveFrom instanceof BasePile) {
                        cardsInDiscard.remove(cardsInDiscard.size() - 1);
                    } else if (move.moveFrom instanceof BuildPile && move.moveFrom.size() > 1 ) {
                        if (!move.moveFrom.getCards().get(move.moveFrom.getCards().size()-2).isVisible()){
                            cardToAddToBoard = cardsHiddenOnBoard.get(0);
                            cardsHiddenOnBoard.remove(cardToAddToBoard);
                        }
                    }
                }

                table.makeMove(move, cardToAddToBoard);

                for (BuildPile pile : table.getBuildPiles()){
                    if (!pile.isEmpty() && !pile.getTopCard().isVisible()){
                        cardToAddToBoard = cardsHiddenOnBoard.get(0);
                        cardsHiddenOnBoard.remove(cardToAddToBoard);
                        pile.setCard(pile.getCards().size()-1, cardToAddToBoard);
                    }
                }


                boolean done = true;
                for (GroundPile pile : table.getGroundPiles()) {
                    if (pile.size() != 13) {
                        done = false;
                    }
                }

                boolean buildPileIsEmpty = false;
                for (BuildPile pile : table.getBuildPiles()) {
                    if (!pile.isEmpty()) {
                        buildPileIsEmpty = true;
                    }
                }
                boolean discardAndDrawIsEmpty = false;
                if(cardsInDiscard.isEmpty() && cardsInDraw.isEmpty()){
                    discardAndDrawIsEmpty = true;
                }

                if (done || buildPileIsEmpty && discardAndDrawIsEmpty) {
                    won++;
                    System.out.println("won");
                    break;
                }

                if (cardsInDraw.isEmpty() && cardsInDiscard.size() > 1) {
                    cardsInDraw = cardsInDiscard;
                    cardsInDiscard.clear();
                    table.discardPile.getCards().clear();
                }
                if (i == 3999){
                    lost++;
                    System.out.println("lost");
                }
                //System.out.println(table);
                //System.out.println(move);
            }

        }
    public Table makeTestTable(List<Card> cards){
        Table table = new Table();
        table.buildPile.get(0).setCard(0, cards.get(0));
        table.buildPile.get(1).setCard(1, cards.get(1));
        table.buildPile.get(2).setCard(2, cards.get(2));
        table.buildPile.get(3).setCard(3, cards.get(3));
        table.buildPile.get(4).setCard(4, cards.get(4));
        table.buildPile.get(5).setCard(5, cards.get(5));
        table.buildPile.get(6).setCard(6, cards.get(6));
        return table;


    }

    public ArrayList<Card> makeCards(){
        ArrayList<Card> cards = new ArrayList<>();

        Card cardKingHearts = new Card(Suit.HEARTS, FaceValue.THIRTEEN);
        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardJackHearts = new Card(Suit.HEARTS, FaceValue.ELEVEN);
        Card cardTenHearts = new Card(Suit.HEARTS, FaceValue.TEN);
        Card cardNineHearts = new Card(Suit.HEARTS, FaceValue.NINE);
        Card cardEightHearts = new Card(Suit.HEARTS, FaceValue.EIGHT);
        Card cardSevenHearts = new Card(Suit.HEARTS, FaceValue.SEVEN);
        Card cardSixHearts = new Card(Suit.HEARTS, FaceValue.SIX);
        Card cardFiveHearts = new Card(Suit.HEARTS, FaceValue.FIVE);
        Card cardFourHearts = new Card(Suit.HEARTS, FaceValue.FOUR);
        Card cardThreeHearts = new Card(Suit.HEARTS, FaceValue.THREE);
        Card cardTwoHearts = new Card(Suit.HEARTS, FaceValue.TWO);
        Card cardOneHearts = new Card(Suit.HEARTS, FaceValue.ONE);
        cards.add(cardOneHearts);
        cards.add(cardTwoHearts);
        cards.add(cardThreeHearts);
        cards.add(cardFourHearts);
        cards.add(cardFiveHearts);
        cards.add(cardSixHearts);
        cards.add(cardSevenHearts);
        cards.add(cardEightHearts);
        cards.add(cardNineHearts);
        cards.add(cardTenHearts);
        cards.add(cardJackHearts);
        cards.add(cardQueenHearts);
        cards.add(cardKingHearts);

        Card cardKingDiamonds = new Card(Suit.DIAMONDS, FaceValue.THIRTEEN);
        Card cardQueenDiamonds = new Card(Suit.DIAMONDS, FaceValue.TWELVE);
        Card cardJackDiamonds = new Card(Suit.DIAMONDS, FaceValue.ELEVEN);
        Card cardTenDiamonds = new Card(Suit.DIAMONDS, FaceValue.TEN);
        Card cardNineDiamonds = new Card(Suit.DIAMONDS, FaceValue.NINE);
        Card cardEightDiamonds = new Card(Suit.DIAMONDS, FaceValue.EIGHT);
        Card cardSevenDiamonds = new Card(Suit.DIAMONDS, FaceValue.SEVEN);
        Card cardSixDiamonds = new Card(Suit.DIAMONDS, FaceValue.SIX);
        Card cardFiveDiamonds = new Card(Suit.DIAMONDS, FaceValue.FIVE);
        Card cardFourDiamonds = new Card(Suit.DIAMONDS, FaceValue.FOUR);
        Card cardThreeDiamonds = new Card(Suit.DIAMONDS, FaceValue.THREE);
        Card cardTwoDiamonds = new Card(Suit.DIAMONDS, FaceValue.TWO);
        Card cardOneDiamonds = new Card(Suit.DIAMONDS, FaceValue.ONE);
        cards.add(cardOneDiamonds);
        cards.add(cardTwoDiamonds);
        cards.add(cardThreeDiamonds);
        cards.add(cardFourDiamonds);
        cards.add(cardFiveDiamonds);
        cards.add(cardSixDiamonds);
        cards.add(cardSevenDiamonds);
        cards.add(cardEightDiamonds);
        cards.add(cardNineDiamonds);
        cards.add(cardTenDiamonds);
        cards.add(cardJackDiamonds);
        cards.add(cardQueenDiamonds);
        cards.add(cardKingDiamonds);

        Card cardKingSpades = new Card(Suit.SPADES, FaceValue.THIRTEEN);
        Card cardQueenSpades = new Card(Suit.SPADES, FaceValue.TWELVE);
        Card cardJackSpades = new Card(Suit.SPADES, FaceValue.ELEVEN);
        Card cardTenSpades = new Card(Suit.SPADES, FaceValue.TEN);
        Card cardNineSpades = new Card(Suit.SPADES, FaceValue.NINE);
        Card cardEightSpades = new Card(Suit.SPADES, FaceValue.EIGHT);
        Card cardSevenSpades = new Card(Suit.SPADES, FaceValue.SEVEN);
        Card cardSixSpades = new Card(Suit.SPADES, FaceValue.SIX);
        Card cardFiveSpades = new Card(Suit.SPADES, FaceValue.FIVE);
        Card cardFourSpades = new Card(Suit.SPADES, FaceValue.FOUR);
        Card cardThreeSpades = new Card(Suit.SPADES, FaceValue.THREE);
        Card cardTwoSpades = new Card(Suit.SPADES, FaceValue.TWO);
        Card cardOneSpades = new Card(Suit.SPADES, FaceValue.ONE);
        cards.add(cardOneSpades);
        cards.add(cardTwoSpades);
        cards.add(cardThreeSpades);
        cards.add(cardFourSpades);
        cards.add(cardFiveSpades);
        cards.add(cardSixSpades);
        cards.add(cardSevenSpades);
        cards.add(cardEightSpades);
        cards.add(cardNineSpades);
        cards.add(cardTenSpades);
        cards.add(cardJackSpades);
        cards.add(cardQueenSpades);
        cards.add(cardKingSpades);

        Card cardKingClubs = new Card(Suit.CLUBS, FaceValue.THIRTEEN);
        Card cardQueenClubs = new Card(Suit.CLUBS, FaceValue.TWELVE);
        Card cardJackClubs = new Card(Suit.CLUBS, FaceValue.ELEVEN);
        Card cardTenClubs = new Card(Suit.CLUBS, FaceValue.TEN);
        Card cardNineClubs = new Card(Suit.CLUBS, FaceValue.NINE);
        Card cardEightClubs = new Card(Suit.CLUBS, FaceValue.EIGHT);
        Card cardSevenClubs = new Card(Suit.CLUBS, FaceValue.SEVEN);
        Card cardSixClubs = new Card(Suit.CLUBS, FaceValue.SIX);
        Card cardFiveClubs = new Card(Suit.CLUBS, FaceValue.FIVE);
        Card cardFourClubs = new Card(Suit.CLUBS, FaceValue.FOUR);
        Card cardThreeClubs = new Card(Suit.CLUBS, FaceValue.THREE);
        Card cardTwoClubs = new Card(Suit.CLUBS, FaceValue.TWO);
        Card cardOneClubs = new Card(Suit.CLUBS, FaceValue.ONE);
        cards.add(cardOneClubs);
        cards.add(cardTwoClubs);
        cards.add(cardThreeClubs);
        cards.add(cardFourClubs);
        cards.add(cardFiveClubs);
        cards.add(cardSixClubs);
        cards.add(cardSevenClubs);
        cards.add(cardEightClubs);
        cards.add(cardNineClubs);
        cards.add(cardTenClubs);
        cards.add(cardJackClubs);
        cards.add(cardQueenClubs);
        cards.add(cardKingClubs);
        return cards;
    }
}

