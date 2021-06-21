/*
Author: Elinor Mohr Mikkelsen
Collaborator(s): Karl Emil Hansen, Simon Fridolf, Simon Søborg
 */
package grp5.cdio.solitairesolver.Controller;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import grp5.cdio.solitairesolver.Model.Move;
import grp5.cdio.solitairesolver.Model.Table;
import grp5.cdio.solitairesolver.Service.ObjectDetection.ObjectDetection;

public class Controller {
    /**
     * Controller class.
     * <p>
     * Used to mange the data model {@link Table}
     */
    public Table table;
    private static Controller single_instance = null;
    private ObjectDetection dect;

    public Controller() {}

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
    public void loadCards(Context context, HashMap<String, Bitmap> map) throws IOException {
        dect = new ObjectDetection(context);
        table = dect.analyzeImage(map);
    }

    /**
     * get game table
     */
    public Table getTable(){
        return dect.getTable();
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
            return bestMove(moves);
        }
    }

    /**
     * Find best {@link Move} from  an arrayList
     *
     * @param moves, an array list of possible moves
     * @return the Move with highest score
     */
   private Move bestMove(ArrayList<Move> moves){
            return table.getBestMove(moves);
    }

    /**
     * Get list of legal {@link Move} from table
     *
     * @return an arrayList of legal moves from table
     */
    private ArrayList<Move> testPossibleMoves(){
        return table.getLegalMoves();
    }
}