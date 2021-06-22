/*
Author: Christine Nielsen
Collaborator(s): Elinor Mohr Mikkelsen, Simon Søborg
 */
package grp5.cdio.solitairesolver.Model;

/**
 * BuildPile, Seven piles that make up the main table.
 */
public class BuildPile extends Pile {
    /**
     * Create Pile
     *
     * @param numberOfCards
     */
    public BuildPile(int numberOfCards) {
        super(numberOfCards);
    }

    /**
     * Test if move is legal
     *
     * @param toCard, cord to make move to(put on top)
     * @return boolean, true = legal
     */
    public boolean isLegalMove(Card toCard){
        // Special case for King
        if (isEmpty()){
            if (toCard.getIntValue() == 13){
                return true;
            }
            return false;
        }
        Card topcard = cards.get(cards.size()-1);
        if ((topcard.isBlack() && toCard.isRed()) || (topcard.isRed() && toCard.isBlack())){
            if(topcard.getIntValue() - 1 == toCard.getIntValue())
                return true;
        }
        return false;

    }

}

