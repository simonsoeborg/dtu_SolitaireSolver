package grp5.cdio.solitairesolver.Model;

import java.util.ArrayList;

public class Table {
    private ArrayList<Pile> groundPile; // The four "columns in the right corner.
    private ArrayList<Pile> buildPile; // The seven columns in the middle of the board.
    private Pile drawPile; // The draw pile.
    private Pile discardPile; // Pile of already drawn cards.

    public Table(){
        this.drawPile = new Pile(24);  //
        this.buildPile = new ArrayList<Pile>();
        buildPile.add(new Pile(1));
        buildPile.add(new Pile(2));
        buildPile.add(new Pile(3));
        buildPile.add(new Pile(4));
        buildPile.add(new Pile(5));
        buildPile.add(new Pile(6));
        buildPile.add(new Pile(7));
        this.groundPile = new ArrayList<Pile>(4);
        this.discardPile = new Pile(0);
    }


    public void updateTable(){
        ArrayList<Move> list = getLegalMoves();
        Move move = getBestMove(list);
        makeMove(move);


    }

    public void makeMove(Move move){
        move.makeMove();

    }

    public Move getBestMove(ArrayList<Move> list){
        Move bestMove = list.get(0);
        for(Move move : list){
            if (move.getScore() > bestMove.getScore()){
                bestMove = move;
            }
        }
        return bestMove;

    }

    public ArrayList<Move> getLegalMoves (){
        ArrayList<Move> list = new ArrayList<Move>();
        for (Pile pile: groundPile){
            list.addAll(getLegalMove(pile));
        }
        for (Pile pile: buildPile){
            list.addAll(getLegalMove(pile));
        }
        list.addAll(getLegalMove(discardPile));

        return list;
    }

    public ArrayList<Move> getLegalMove (Pile pileFrom){
        ArrayList<Move> list = new ArrayList<Move>();

        for (Pile pile: groundPile){
            if (pile.isLegalMove(pileFrom.getTopCard())){
                Move move = new Move(pileFrom, pile, 0, pileFrom.getTopCard());
                move.setScore();
                list.add(move);

            }
        }
        for (Pile pile: groundPile){
            if (pile.isLegalMove(pileFrom.getTopCard())){
                Move move = new Move(pileFrom, pile, 0, pileFrom.getTopCard());
                move.setScore();
                list.add(move);

            }
        }
        return list;
    }

    @Override
    public String toString(){
        return  "";
    }
}
