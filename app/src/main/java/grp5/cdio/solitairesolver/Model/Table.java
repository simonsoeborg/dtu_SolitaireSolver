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
    public ArrayList<GroundPile> groundPile;
    public ArrayList<BuildPile> buildPile;
    public Pile drawPile;
    public Pile discardPile;

    /**
     * Set up game table with 52 cards
     */
    public Table(){
        this.drawPile = new BasePile(24);
        this.buildPile = new ArrayList<BuildPile>();
        this.groundPile = new ArrayList<GroundPile>();
        buildPile.add(new BuildPile(1));
        buildPile.add(new BuildPile(2));
        buildPile.add(new BuildPile(3));
        buildPile.add(new BuildPile(4));
        buildPile.add(new BuildPile(5));
        buildPile.add(new BuildPile(6));
        buildPile.add(new BuildPile(7));
        groundPile.add(new GroundPile(0));
        groundPile.add(new GroundPile(0));
        groundPile.add(new GroundPile(0));
        groundPile.add(new GroundPile(0));
        this.discardPile = new BasePile(0);
    }

    public void makeMove(Move move, Card card){
        if (move.card == null || move.getScore() == -1 ||  move.getScore() == 5){
            discardPile.getCards().add(card);
        } else {
            move.makeMove();
            if (card != null){
                move.moveFrom.setCard(move.moveFrom.size()-1, card);
            }
        }
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

    public GroundPile getGroundPile(int index) {
        if (index <= 3) {
        return groundPile.get(index);
        }
        return null;
    }

    public ArrayList<GroundPile> getGroundPiles() {
        return groundPile;
    }

    public void setGroundPile(int index, int cards) {
        if (index <= 3) {
            this.groundPile.set(index, new GroundPile(cards));
        }
    }

    public BuildPile getBuildPile(int index) {
        if (index <= 6) {
            return buildPile.get(index);
        }
        return null;
    }

    public void setBuildPile(int index, int cards) {
        if (index <= 6) {
            this.buildPile.set(index, new BuildPile(cards));
        }
    }

    public Pile getDrawPile() {
        return drawPile;
    }

    public Pile getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(Pile discardPile) {
        this.discardPile = discardPile;
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
        if (!pileFrom.isEmpty() && pileFrom.getTopCard().isVisible()){
            for (Pile pile: groundPile){
                if (pile.isLegalMove(pileFrom.getTopCard())){
                    Move move = new Move(pileFrom, pile, pileFrom.getTopCard());
                    move.setScore();
                    list.add(move);
                }
            }
            for (Pile pile: buildPile){
                if (pile.isLegalMove(pileFrom.getTopCard())){
                    Move move = new Move(pileFrom, pile,pileFrom.getTopCard());
                    move.setScore();
                    list.add(move);
                }
            }
        }
        return list;
    }

    /**
     * Override toString
     */
    @Override
    public String toString(){
        StringBuilder returnValue = new StringBuilder("Table: \n");

        returnValue.append("Draw Pile: \n");
        returnValue.append(drawPile.toString()).append("\n");

        returnValue.append("Discard Pile: \n");
        returnValue.append(discardPile.toString()).append("\n");

        returnValue.append("Ground Pile: \n");
        for(Pile pile : groundPile){
            returnValue.append(pile.toString()).append("\n");
        }

        returnValue.append("Build Pile: \n");
        for(Pile pile : buildPile){
            returnValue.append(pile.toString()).append("\n");
        }

        return  returnValue.toString();
    }
}
