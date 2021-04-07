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

    private int getScore(Move move){

    }
}


//
//        Liste Træk TestMuligeTræk(Bord){
//
//        for byggestablerne{
//        TestMarkør(Bord, bagerst synlige kort);
//        TestMarkør(Bord, øverst synligt kort);
//        }
//
//        TestMarkør(Bord, affalds-bunke synligt kort);
//
//        for grundbunke{
//        TestMarkør(Bord, øverst synligt kort);
//        }
//
//        }
//
//        TestMarkør(Bord, markør vi kigger på){
//        if(markør == ES && !ligger i grundbunke) return Træk(95 + (1-6))
//        if(markør == 2 && match ES i grundbunke && !ligger i grundbunke) return Træk(85 + (1-6))
//
//        if (markør vi kigger på = Øverst synligt kort || bagerst synlige kort)
//        if (markør == Konge && byggestak[i].isEmpty() return Træk(75 + (1-6) )
//        if (markør == Konge && (whole)byggestak[i].moveableBygge() return Træk(65+(1-6))
//        if (markør == Konge &&  byggestak[i][øverst kort].moveableGrund() &&
//        byggestak[i].elements== 1 return Træk(55+(1-6))
//
//        if ( !(markør)byggestak[i].isAllVisible() && (whole)byggestak[i].moveable(anden byggestak) return Træk(45 + (1-6))
//
//        if ( !(markør)byggestak[i].isAllVisible() && byggestak[i][øverst kort].moveable(Grund bunke) return 35 + (1-6))
//
//        if (!byggestak[i].isEmpyt() && markør == konge && (whole)byggestak[i].moveable(anden byggestak)) return 32)
//
//        if (!byggestak[i].isEmpyt() && markør == konge && (whole)byggestak[i].moveable(Grund bunke)) return 31)
//
//        if(!bunke.isEmpty && affaldbunke.isEmpty() return 30)
//        if(!bunke.isEmpty && affaldbunke.isEmpty() return 30 - (0-6))
//
//        if(!affaldbunke.isEmpty() && byggestak[i].moveable(affaldbunke)) return = 30 - (0-6), alt efter skjult bunke størrelse)
//
//        (!affaldbunke.isEmpty() && grundstak[i].moveable(affaldbunke)) return  20(- kort værdi)
//
//        (!bunke.isEmpty)return 1
//
//        if (bunke.isEmpty && !affaldbunke.isEmpty() && !udentræk) return 1
//
//        if(bunke.isEmpty && !affalds-bunke.isEmpty && !udentræk) return Træk(1)
//
//
//        If(Markør.isLegal(grundbunke)) return Træk(- 0 (- kort værdi))
//
//        if(!udentræk?) return Træk(-1000)