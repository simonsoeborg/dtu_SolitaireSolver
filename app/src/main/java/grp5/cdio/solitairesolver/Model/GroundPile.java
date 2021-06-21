/*
Author: Elinor Mohr Mikkelsen
Collaborator(s):
 */
package grp5.cdio.solitairesolver.Model;

/**
 * GroundPile, Four piles on which a whole suit or sequence must be built up
 */
public class GroundPile extends Pile{
    /**
     * Create Pile
     *
     * @param numberOfCards
     */
    public GroundPile(int numberOfCards) {
        super(numberOfCards);
    }

    /**
     * Test if move is legal
     *
     * @param toCard, card to make move to(put on top)
     * @return boolean, true = legal
     */
    public boolean isLegalMove(Card toCard){
        return isLegal(toCard);
    }


    /**
     * Test if move is legal for special rule for groundpile
     *
     * @param toCard, card to make move to(put on top)
     * @return boolean, true = legal
     */
    private boolean isLegal(Card toCard){
        // Special case for ace
        if (isEmpty() && toCard.getIntValue() == 1){
            return true;
        }
        if (!isEmpty() && getTopCard().isVisible() && Suit.isEqual(getTopCard().getSuit(), toCard.getSuit())){
            if(getTopCard().getIntValue() == toCard.getIntValue() - 1)
                return true;
        }
        return false;
    }
}
