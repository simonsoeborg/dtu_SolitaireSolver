package grp5.cdio.solitairesolver.Controller;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;

import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Move;
import grp5.cdio.solitairesolver.Model.Suit;
import grp5.cdio.solitairesolver.Model.Table;

public class Controller {
    /**
     * Controller class.
     * <p>
     * Used to mange the data model {@link Table}
     */
    private Table table;

    /**
     * Get best move based om image
     */
    public Move getMove(HashMap<String, Bitmap> map){
        createTableFromImage(map);
        return getBestMove();
    }

    /**
     * Set up game table {@link Table} from image obj
     */
    private void createTableFromImage(HashMap<String, Bitmap> map){
        table = runImageRecognition(map);
    }

    /**
     * Find best {@link Move} from table {@link Table}
     *
     * @return the Move with highest score fro m table
     */
    private Move getBestMove(){
        ArrayList<Move> moves = table.getLegalMoves();
        return table.getBestMove(moves);
    }

    /**
     * Create table {@link Table} based on image
     *
     * @return {@link Table} made from image
     */
    private Table runImageRecognition(HashMap<String, Bitmap> map){

        // TODO Replace with image
        // Mock data start
        Table table = new Table();
        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardKingClubs = new Card(Suit.CLUBS, FaceValue.THIRTEEN);
        Card cardTenHearts = new Card(Suit.HEARTS, FaceValue.TEN);
        Card cardJackClubs = new Card(Suit.CLUBS, FaceValue.ELEVEN);
        Card cardFiveHearts = new Card(Suit.HEARTS, FaceValue.FIVE);
        Card cardFiveClubs = new Card(Suit.CLUBS, FaceValue.FIVE);
        Card cardFiveSpades = new Card(Suit.SPADES, FaceValue.FIVE);

        table.buildPile.get(0).setCard(0, cardQueenHearts);
        table.buildPile.get(1).setCard(1, cardKingClubs);
        table.buildPile.get(2).setCard(2, cardTenHearts);
        table.buildPile.get(3).setCard(3, cardJackClubs);
        table.buildPile.get(4).setCard(4, cardFiveClubs);
        table.buildPile.get(5).setCard(5, cardFiveHearts);
        table.buildPile.get(6).setCard(6, cardFiveSpades);
        // Mock data end

        return table;
    }
}
