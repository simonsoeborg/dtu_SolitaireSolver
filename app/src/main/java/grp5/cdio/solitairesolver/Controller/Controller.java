package grp5.cdio.solitairesolver.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import grp5.cdio.solitairesolver.Model.Move;
import grp5.cdio.solitairesolver.Model.Table;
import grp5.cdio.solitairesolver.Controller.ObjectDetection.ObjectDetection;

public class Controller {
    /**
     * Controller class.
     * <p>
     * Used to mange the data model {@link Table}
     */
    private Table table;
    private Context context;
    private ObjectDetection dect = new ObjectDetection(context);
    private HashMap<String, Bitmap> EmptyMap = new HashMap<>();
    private static Move lastMove;
    private Move nextMove;



    public Controller() {
        loadCards(dect.analyzeImage(EmptyMap));
    }

    /**
     * Set up game table
     * Instansiate table from objectDectection {@link Table}
     */
    public void loadCards(Table currenTable){
        table = currenTable;
    }

    /**
     * Find best {@link Move} from table {@link Table}
     *
     * @return the Move with highest score
     */
    public Move getMove(){
        ArrayList<Move> moves = testPossibleMoves();
        if (!moves.isEmpty()) {
            Move move = bestMove(moves);
            return move;
        }
        else return null;
    }

    /**
     * Find best {@link Move} from  an arrayList
     *
     * @param moves, an array list of possible moves
     * @return the Move with highest score
     */
    public Move bestMove(ArrayList<Move> moves){
        Move best = moves.get(0);

        for (Move move: moves){
            if (move.getScore() > best.getScore()){
                best = move;
            }
        }
        return best;
    }

    /**
     * Get list of legal {@link Move} from table
     *
     * @return an arrayList of legal moves from table
     */
    public ArrayList<Move> testPossibleMoves(){
        return table.getLegalMoves();
    }

    // Todo - Sikre at spillet ikke bliver fanget i et loop - men på et tidspunkt konkludere at spille ikke kan løses:
    //  (Tænker man evt. kunen oprette en række variable i cachen som tjekker de tidligere best moves og sørger for at den ikke gentager dem flere gange?)


    public void accounForMove(){
        System.out.println(lastMove);
        ArrayList<Move> moves = table.getLegalMoves();
        Move move = table.getBestMove(moves);
        System.out.println(moves.size());

        if (move.isInverseMove(lastMove)){

            if (moves.size()>1) {
                moves.remove(move);
                System.out.println(moves.size());

                nextMove = table.getBestMove(moves);
                System.out.println(nextMove);

                lastMove = nextMove;
                System.out.println(lastMove);

            }

            else {
                // todo Game done - no sultion
                Log.d("lost", "No solution: ");
            }
        }
        lastMove = move;
    }
}

