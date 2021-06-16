package grp5.cdio.solitairesolver.Model;
import java.util.ArrayList;

public abstract class Pile {
    /**
     * abstract Pile class, used as base for the {@link BuildPile} {@link GroundPile} {@link BasePile} (Move rules are different)
     * <p>
     * Used to mange an ArrayList of {@link Card}
     */
    protected ArrayList<Card> cards;

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
    public abstract boolean isLegalMove(Card toCard);
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
     * add top card
     */
    public void addCard(Card card){
        cards.add(card);
    }

    /**
     * remove top card
     */
    public void removeCard(){
        cards.remove(cards.get(cards.size()-1));
    }

    /**
     * get all cards in pile
     * @return Card
     */
    public ArrayList<Card> getCards(){return cards;}

    /**
     * get top card
     * @return Card
     */
    public Card getTopCard(){
        if (!cards.isEmpty()){
            return cards.get(cards.size()-1);
        }
        return null;
    }

    /**
     * get card below top card
     * @return Card
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
        if (isEmpty()){
            return "Empty Pile";
        }
        for (Card card: cards){
            returnValue = returnValue + card.toString();
        }
        return returnValue;
    }

    /**
     * get size
     * @return int size
     */
    public int size(){
        return cards.size();
    }

    /**
     * is pile empty
     * @return boolean, true = empty
     */
    public boolean isEmpty(){
        return cards.isEmpty();
    }


}
