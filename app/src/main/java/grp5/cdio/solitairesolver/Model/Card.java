package grp5.cdio.solitairesolver.Model;

public class Card {
    /**
     * Card class.
     * <p>
     * Used to mange the card values
     * visible - is the card front visible
     * suit - suit of the card
     * value - The face value of the card
     */
    private boolean visible;
    private Suit suit;
    private FaceValue value;

    /**
     * Create visible card
     */
    public Card(Suit suit, FaceValue value){
        this.suit = suit;
        this.value = value;
        this.visible = true;
    }

    /**
     * Create invisible card
     */
    public Card(){
        this.suit = Suit.UNKNOWN;
        this.value = FaceValue.UNKNOWN;
        this.visible = false;
    }

    /**
     * Test if two card are the same
     *
     * @param card, card to test
     * @return boolean, true = same card
     */
    public boolean isEqual(Card card){
        if(this.suit.equals(card.suit) && this.value.equals(card.value)){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Test if card is red
     *
     * @return boolean, true = red
     */
    public boolean isRed(){
        return Suit.isRed(suit);
    }

    /**
     * Test if card is black
     *
     * @return boolean, true = black
     */
    public boolean isBlack(){
        return Suit.isBlack(suit);
    }

    /**
     * Test if move is legal
     *
     * @param toCard, cord to make move to(put on top)
     * @return boolean, true = legal
     */
    public boolean isLegalMove(Card toCard){
        if(toCard == null && this.getIntValue() == 13){
            return true;
        }
        if ((this.isBlack() && toCard.isRed()) || (this.isRed() && toCard.isBlack())){
            if(this.getIntValue() + 1 == toCard.getIntValue())
                return true;
        }
        return false;
    }

    /**
     * Override toString
     */
    @Override
    public String toString(){
        if (visible){
            return "["+suit+"-"+value+"]";
        } else {
            return "[X-X]";
        }
    }

    /**
     * Get visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Set visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Get Suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Set suit
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    /**
     * Get FaceValue
     */
    public FaceValue getValue() {
        return value;
    }

    /**
     * Get int value of FaceValue
     */
    public int getIntValue() {
        return FaceValue.getValue(value);
    }

    /**
     * Set FaceValue
     */
    public void setValue(FaceValue value) {
        this.value = value;
    }

}
