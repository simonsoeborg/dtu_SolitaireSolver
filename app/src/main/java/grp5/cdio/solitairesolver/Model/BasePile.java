/*
Author: Elinor Mohr Mikkelsen
Collaborator(s):
 */
package grp5.cdio.solitairesolver.Model;

/**
 * BasePile, Waste and Hand
 */
public class BasePile extends Pile {
    /**
     * Create Pile
     *
     * @param numberOfCards
     */
    public BasePile(int numberOfCards) {
        super(numberOfCards);
    }

    /**
     * Test if move is legal
     *
     * @param toCard, cord to make move to(put on top)
     * @return boolean, true = legal
     */
    public boolean isLegalMove(Card toCard){
        return false;
    }
}
