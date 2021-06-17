package grp5.cdio.solitairesolver.Model;

import java.util.ArrayList;

public class Move {
    /**
     * Move class.
     * <p>
     * Used to mange the card moves
     * moveFrom - pile to move card from
     * moveTo - pile to move card to
     * card - card to move
     * score - value of move, use to determine best move
     */
    public Pile moveFrom;
    protected Pile moveTo;
    public Card card;
    protected int score;

    /**
     * Create card, score is set by {@link #setScore}
     */
    public Move(Pile from, Pile to, Card card) {
        moveFrom = from;
        moveTo = to;
        this.card = card;
        setScore();
    }

    /**
     * test if move is inverse
     *
     * @param oldMove, test if this in inverse of old move
     * @return boolean, true = inverse move
     */
    public boolean isInverseMove(Move oldMove) {
        if (oldMove == null) {
            return false;
        }

        Card to = moveTo.getTopCard();
        Card from = moveFrom.getTopCard();

        if (oldMove.moveFrom.isEqual(to) && oldMove.moveTo.isEqual(from) && oldMove.card.isEqual(card)) {
            return true;
        }
        return false;
    }

    /**
     * make move from piles movefrom -> moveto
     */
    public void makeMove() {
        ArrayList<Card> listToMove = new ArrayList<>();
        boolean addToList = false;

        for(Card cardInList: moveFrom.getCards()){
            if (cardInList.equals(card)){
                addToList = true;
            }
            if (addToList){
                listToMove.add(cardInList);
            }

        }
        moveFrom.removeCard(listToMove);
        moveTo.makeMove(listToMove);
    }

    /**
     * set score based on Move attributes
     */
    public void setScore() {
        if (card == null) {
            score = 5;
            return;
        }
        int value = card.getIntValue();

        // Test if ace can move to GroundPile(instanceof test type of pile)
        if (value == 1 && moveFrom instanceof BuildPile && moveTo instanceof GroundPile) {
            score = 95 + moveFrom.size();
            return;
        }

        // Test if 2 can move to GroundPile
        if (value == 2 && moveFrom instanceof BuildPile && moveTo instanceof GroundPile) {
            score = 85 + moveFrom.size();
            return;
        }

        // Test if king can move to Empty BuildPile
        if (value == 13 && moveFrom instanceof BuildPile && moveTo instanceof BuildPile && moveTo.isEmpty() && !moveFrom.isEmpty()) {
            score = 75 + moveFrom.size();
            return;
        }
        // Test if card can move from BuildPile to GroundPile
        if (moveFrom instanceof BuildPile && moveTo instanceof GroundPile) {
            score = moveFrom.size() + 40;
            return;
        }

        // Test if card can move from BuildPile to BuildPile
        if (value != 13 && moveFrom instanceof BuildPile && moveTo instanceof BuildPile) {
            score = moveFrom.size() + 30;
            return;
        }

        // Test if card can move from BasePile to GroundPile
        if (moveFrom instanceof BasePile && moveTo instanceof GroundPile) {
            score = moveFrom.size() + 20;
            return;
        }

        // Test if card can move from BasePile to BuildPile
        if (moveFrom instanceof BasePile && moveTo instanceof BuildPile) {
            score = moveFrom.size() + 10;
            return;
        }

        score = -1;
        card = null;


    }

    /**
     * get Score
     */
    public int getScore() {
        return score;
    }

    public boolean isInverse(Move move){
        if (move == null || move.card == null || move.moveFrom == null || move.moveTo == null){
            return false;
        }
        if (card == null || moveFrom == null || moveTo == null){
            return false;
        }

        if (moveFrom.equals(move.moveTo) && moveTo.equals(move.moveFrom) && card.isEqual(move.card)){
            return true;
        }
        return false;
    }

    /**
     * Override toString
     */
    @Override
    public String toString() {
        if (card == null) {
            return "Draw new cards";
        }
        return "Move " + card + " from pile " + moveFrom.toString() + " to pile " + moveTo.toString();
    }
}