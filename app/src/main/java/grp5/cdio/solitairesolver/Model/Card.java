package grp5.cdio.solitairesolver.Model;

public class Card {
    private boolean visible;
    private Suit suit;
    private FaceValue value;

    public Card(Suit suit, FaceValue value){
        this.suit = suit;
        this.value = value;
        this.visible = true;
    }
    public Card(){
        this.suit = Suit.UNKNOWN;
        this.value = FaceValue.UNKNOWN;
        this.visible = false;
    }

    @Override
    public String toString(){
        if (visible){
            return "["+suit+"-"+value+"]";
        } else {
            return "[X-X]";
        }
    }

    public boolean isEqual(Card card){
        if(this.suit.equals(card.suit) && this.value.equals(card.value)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isRed(){
        return Suit.isRed(suit);
    }

    public boolean isBlack(){
        return Suit.isBlack(suit);
    }

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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public FaceValue getValue() {
        return value;
    }

    public int getIntValue() {
        return FaceValue.getValue(value);
    }

    public void setValue(FaceValue value) {
        this.value = value;
    }

}
