package grp5.cdio.solitairesolver.Model;

import java.util.ArrayList;
public class Table {
    /**
     * Table class.
     * <p>
     * Used to mange the data model {@link Pile}
     * groundPile - The four "columns in the right corner.
     * buildPile - The seven columns in the middle of the board
     * drawPile - The draw pile
     * discardPile - Pile of already drawn cards
     */
    private ArrayList<Pile> groundPile;
    private ArrayList<Pile> buildPile;
    private Pile drawPile;
    private Pile discardPile;



    /**
     * Set up game table with 52 cards
     */
    public Table(){
        this.drawPile = new Pile(24);
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

    /**
     * Make best {@link Move}
     *
     */
    public void updateTable(){
        ArrayList<Move> list = getLegalMoves();
        Move move = getBestMove(list);
        makeMove(move);
    }

    /**
     * Make/update table based on a {@link Move}
     *
     * @param move to make
     */
    public void makeMove(Move move){
        move.makeMove();
    }

    /**
     * Get the {@link Move} with highest
     *
     * @param list, ArrayList of {@link Move}
     * @return the {@link Move} with highest score
     */
    public Move getBestMove(ArrayList<Move> list){
        Move bestMove = list.get(0);
        for(Move move : list){
            if (move.getScore() > bestMove.getScore()){
                bestMove = move;
            }
        }

        return bestMove;
    }

    /**
     * Get list of legal {@link Move}
     *
     * @return ArrayList of legal {@link Move}
     */
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

    /**
     * Get ArrayList of all legal {@link Move} from one {@link Pile}
     *
     * @param pileFrom, the {@link Pile} to test for legal moves
     * @return ArrayList of all legal {@link Move}
     */
    public ArrayList<Move> getLegalMove (Pile pileFrom){
        ArrayList<Move> list = new ArrayList<Move>();

        for (Pile pile: groundPile){
            if (pile.isLegalMove(pileFrom.getTopCard())){
                Move move = new Move(pileFrom, pile, pileFrom.getTopCard());
                move.setScore();
                list.add(move);
            }
        }
        for (Pile pile: groundPile){
            if (pile.isLegalMove(pileFrom.getTopCard())){
                Move move = new Move(pileFrom, pile,pileFrom.getTopCard());
                move.setScore();
                list.add(move);
            }
        }
        return list;
    }

    /**
     * Override toString
     */
    @Override
    public String toString(){
        return  "";
    }
}
