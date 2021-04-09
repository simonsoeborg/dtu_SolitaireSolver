package grp5.cdio.solitairesolver.Controller;

import java.util.ArrayList;

import grp5.cdio.solitairesolver.Model.Move;
import grp5.cdio.solitairesolver.Model.Table;

public class Controller {
    private Table table;

    public void loadCards(){

    }

    public Move getMove(){
        ArrayList<Move> moves = testPossibleMoves(table);
        Move move = bestMove(moves);
        return move;
    }

    public Move bestMove(ArrayList<Move> moves){
        Move best = moves.get(0);

        for (Move move: moves){
            if (move.getScore() > best.getScore()){
                best = move;
            }
        }
        return best;

    }

    public ArrayList<Move> testPossibleMoves(Table table){

        return null;
    }

}
