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
        return toCard.isLegalMove(cards.get(cards.size()-1));
    }
}
