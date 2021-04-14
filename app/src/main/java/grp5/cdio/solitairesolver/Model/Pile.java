package grp5.cdio.solitairesolver.Model;
import java.util.ArrayList;

public class Pile {
    /**
     * Pile clase.
     * <p>
     * Used to mange an ArrayList of {@link Card}
     */
    private ArrayList<Card> cards;

    /**
     * Create Pile
     *
     * @param numberOfCards, cards in pile
     *
     */
    public Pile(int numberOfCards){
        cards = new ArrayList<Card>();
        for(int i = 1; i <= numberOfCards; i++){
            cards.add(new Card());
        }
    }

    /**
     * set card at index
     *
     * @param index, index to replace
     * @param card, {@link Card} to place at index
     */
    public void setCard(int index, Card card){
        cards.set(index, card);
    }

    /**
     * Test if top card and toCard are the same
     *
     * @param toTest, card to test
     * @return boolean, true = same card
     */
    public boolean isEqual(Card toTest){
        return cards.get(cards.size()-1).isEqual(toTest);
    }

    /**
     * Test if move is legal
     *
     * @param toCard, cord to make move to(put on top)
     * @return boolean, true = legal
     */
    public boolean isLegalMove(Card toCard){
        return toCard.isLegalMove(cards.get(cards.size()-1));
    }

    /**
     * Test if move is legal
     *
     * @param cardToMove, card to move(put on top)
     * @return boolean, true = made move
     */
    public boolean makeMove(Card cardToMove){
        if (isLegalMove(cardToMove)){
            cards.add(cardToMove);
            return true;
        }
        return false;
    }

    /**
     * remove top card
     */
    public void removeCard(){
        cards.remove(cards.get(cards.size()-1));
    }

    /**
     * get top card
     */
    public Card getTopCard(){
        return cards.get(cards.size()-1);
    }

    /**
     * get card below top card
     */
    public Card getBelowTopCard(){
        return cards.get(cards.size()-2);
    }

    /**
     * Override toString
     */
    @Override
    public String toString(){
        String returnValue = "";
        for (Card card: cards){
            returnValue = returnValue + card.toString();
        }
        return returnValue;
    }


}
