/*
Author: Elinor Mohr Mikkelsen
Collaborator(s): Simon Søborg, Simon Fridolf, Karl Emil Hansen, Kristoffer Baumgarten
 */
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
    private final ArrayList<Move> lastMoveList;

    /**
     * Set up game table with 52 cards
     */
    public Table(){
        this.drawPile = new BasePile(0);
        this.buildPile = new ArrayList<BuildPile>();
        this.groundPile = new ArrayList<GroundPile>();
        buildPile.add(new BuildPile(0));
        buildPile.add(new BuildPile(0));
        buildPile.add(new BuildPile(0));
        buildPile.add(new BuildPile(0));
        buildPile.add(new BuildPile(0));
        buildPile.add(new BuildPile(0));
        buildPile.add(new BuildPile(0));
        groundPile.add(new GroundPile(0));
        groundPile.add(new GroundPile(0));
        groundPile.add(new GroundPile(0));
        groundPile.add(new GroundPile(0));
        this.discardPile = new BasePile(0);
        lastMoveList = new ArrayList<>();
    }

    public void makeMove(Move move, Card card){
        if(lastMoveList.size() > 5){
            lastMoveList.remove(0);
        }
        lastMoveList.add(move);
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
        if(list.isEmpty()){
            return new Move(null,null,null);
        }
        Move bestMove = null;

        for(Move move : list){
            boolean addToList = true;
            for (Move lastMove : lastMoveList){
                if (move.isInverse(lastMove)){
                    addToList = false;
                }
            }
            if (bestMove == null && addToList){
                bestMove = move;
            }
            else if (addToList && (move.getScore() > bestMove.getScore())){
                bestMove = move;
            }

        }

        if (bestMove == null){
            return new Move(null,null,null);
        }
        return bestMove;
    }

    public ArrayList<GroundPile> getGroundPiles() {
        return groundPile;
    }


    public ArrayList<BuildPile> getBuildPiles(){
        return buildPile;
    }

    public Pile getDrawPile() {
        return drawPile;
    }

    public Pile getDiscardPile() {
        return discardPile;
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
    public ArrayList<Move> getLegalMove (Pile pileFrom) {
        ArrayList<Move> list = new ArrayList<Move>();
        if (!pileFrom.isEmpty() && pileFrom.getTopCard().isVisible()) {
            for (Pile pile : groundPile) {
                if (pile.isLegalMove(pileFrom.getTopCard())) {
                    Move move = new Move(pileFrom, pile, pileFrom.getTopCard());
                    move.setScore();
                    list.add(move);
                }
            }
            for (Pile pile : buildPile) {
                if(pileFrom instanceof BasePile){
                    if (pile.isLegalMove(pileFrom.getTopCard())) {
                        Move move = new Move(pileFrom, pile, pileFrom.getTopCard());
                        move.setScore();
                        list.add(move);
                    }
                }
                else {
                    for (Card card : pileFrom.getCards()) {
                        if (pile.isLegalMove(card)) {
                            Move move = new Move(pileFrom, pile, card);
                            move.setScore();
                            list.add(move);
                        }

                    }
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
        StringBuilder returnValue = new StringBuilder("Bord: \n");

        returnValue.append("Vende bunke: \n");
        returnValue.append(drawPile.toString()).append("\n");

        returnValue.append("Træk bunke: \n");
        returnValue.append(discardPile.toString()).append("\n");

        returnValue.append("Kulør bunke: \n");
        for(Pile pile : groundPile){
            returnValue.append(pile.toString()).append("\n");
        }

        returnValue.append("Bygge bunke: \n");
        for(Pile pile : buildPile){
            returnValue.append(pile.toString()).append("\n");
        }

        return  returnValue.toString();
    }
}
