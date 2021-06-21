/*
Author: Elinor Mohr Mikkelsen
Collaborator(s): Simon Søborg
 */
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
    private final boolean visible;
    private Suit suit;
    private final FaceValue value;

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
        return this.suit.equals(card.suit) && this.value.equals(card.value);
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
     * Override toString
     */
    @Override
    public String toString(){
        return suit+" "+value;

    }

    /**
     * Get visible
     */
    public boolean isVisible() {
        return visible;
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

    public boolean equals(Card obj) {
        if(Suit.isEqual(this.getSuit(), obj.getSuit()) ){
            return this.getIntValue() == obj.getIntValue();
        }
        return false;
    }
}
