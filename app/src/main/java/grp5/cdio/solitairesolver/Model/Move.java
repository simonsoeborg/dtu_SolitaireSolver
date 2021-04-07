package grp5.cdio.solitairesolver.Model;

public class Move {
    private Card moveFrom;
    private Card moveTo;
    private Card card;
    private int score;

    public Move(Card from, Card to, int score, Card card){
        moveFrom = from;
        moveTo = to;
        this.score = score;
        this.card = card;
    }

    @Override
    public String toString(){

        return "Move "+card+" from " + moveFrom.toString() + " to " + moveTo.toString();
    }

    public boolean isInverseMove(Move oldMove){
        if (oldMove.moveFrom.isEqual(moveTo) && oldMove.moveTo.isEqual(moveFrom) && oldMove.card.isEqual(card)){
            return true;
        }
        return false;
    }

    public int getScore() {
        return score;
    }

}
