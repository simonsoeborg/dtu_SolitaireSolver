package grp5.cdio.solitairesolver.Controller;

import java.util.ArrayList;

import grp5.cdio.solitairesolver.Model.Move;
import grp5.cdio.solitairesolver.Model.Table;

public class Controller {
    /**
     * Controller class.
     * <p>
     * Used to mange the data model {@link Table}
     */
    private Table table;


    /**
     * Set up game table {@link Table}
     */
    public void loadCards(){

    }

    /**
     * Find best {@link Move} from table {@link Table}
     *
     * @return the Move with highest score
     */
    public Move getMove(){
        ArrayList<Move> moves = testPossibleMoves();
        Move move = bestMove(moves);
        return move;
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

}
