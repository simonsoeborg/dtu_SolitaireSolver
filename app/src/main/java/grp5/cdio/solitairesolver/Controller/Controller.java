package grp5.cdio.solitairesolver.Controller;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;

import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Move;
import grp5.cdio.solitairesolver.Model.Suit;
import grp5.cdio.solitairesolver.Model.Table;
import grp5.cdio.solitairesolver.Service.ObjectDetection.ObjectDetection;

public class Controller {
    /**
     * Controller class.
     * <p>
     * Used to mange the data model {@link Table}
     */
    private Table table;
    private HashMap<String, Bitmap> EmptyMap = new HashMap<>();
    private Move lastMove;
    private static Controller single_instance = null;

    private Controller() {
    }

    public static Controller getInstance(){
        if (single_instance == null){
            single_instance = new Controller();
        }
        return single_instance;
    }

    /**
     * Set up game table
     * Instansiate table from objectDectection {@link Table}
     */
    public void loadCards(Context context, HashMap<String, Bitmap> map){
        ObjectDetection dect = new ObjectDetection(context);
        Table currentTable = dect.analyzeImage(map);
        table = currentTable;
    }

    /**
     * get game table
     */
    public Table getTable(){
        Table mocktable = new Table();
        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardKingHearts = new Card(Suit.HEARTS, FaceValue.THIRTEEN);

        Card cardFourHearts= new Card(Suit.HEARTS, FaceValue.FOUR);
        Card cardJackHearts = new Card(Suit.HEARTS, FaceValue.ELEVEN);

        Card cardFiveHearts = new Card(Suit.HEARTS, FaceValue.FIVE);
        Card cardFiveClubs = new Card(Suit.CLUBS, FaceValue.FIVE);
        Card cardFiveSpades = new Card(Suit.SPADES, FaceValue.FIVE);

        mocktable.buildPile.get(0).setCard(0, cardQueenHearts);
        mocktable.buildPile.get(1).setCard(1, cardKingHearts);
        mocktable.buildPile.get(2).setCard(2, cardFourHearts);
        mocktable.buildPile.get(3).setCard(3, cardJackHearts);
        mocktable.buildPile.get(4).setCard(4, cardFiveClubs);
        mocktable.buildPile.get(5).setCard(5, cardFiveHearts);
        mocktable.buildPile.get(6).setCard(6, cardFiveSpades);
        return mocktable;

        // return table
    }

    /**
     * Find best {@link Move} from table {@link Table}
     *
     * @return the Move with highest score
     */
    public Move getMove(){
        ArrayList<Move> moves = testPossibleMoves();
        if (moves.isEmpty()) {
            return new Move(null,null,null);
        } else {
            Move move = bestMove(moves);
            return move;
        }
    }

    /**
     * Find best {@link Move} from  an arrayList
     *
     * @param moves, an array list of possible moves
     * @return the Move with highest score
     */
   private Move bestMove(ArrayList<Move> moves){
       if (moves.isEmpty()){
           return null;
       }

       Move best = table.getBestMove(moves);
       if(lastMove != null && lastMove.isInverseMove(best)){
           moves.remove(best);
           if (moves.isEmpty()){
               return null;
           }

           return table.getBestMove(moves);
       }
       return best;

    }

    /**
     * Get list of legal {@link Move} from table
     *
     * @return an arrayList of legal moves from table
     */
    private ArrayList<Move> testPossibleMoves(){
        return table.getLegalMoves();
    }
    // Todo - Sikre at spillet ikke bliver fanget i et loop - men på et tidspunkt konkludere at spille ikke kan løses:
    //  (Tænker man evt. kunen oprette en række variable i cachen som tjekker de tidligere best moves og sørger for at den ikke gentager dem flere gange?)

//    public void accounForMove(){
//        System.out.println(lastMove);
//        ArrayList<Move> moves = table.getLegalMoves();
//        //Move move = table.getBestMove(moves);
//        System.out.println(moves.size());
//
//        if (move.isInverseMove(lastMove)){
//
//            if (moves.size()>1) {
//                moves.remove(move);
//                System.out.println(moves.size());
//
//                nextMove = table.getBestMove(moves);
//                System.out.println(nextMove);
//
//                lastMove = nextMove;
//                System.out.println(lastMove);
//            }
//            else {
//                // todo Game done - no sultion
//                Log.d("lost", "No solution: ");
//            }
//        }
//        lastMove = move;
//    }
}