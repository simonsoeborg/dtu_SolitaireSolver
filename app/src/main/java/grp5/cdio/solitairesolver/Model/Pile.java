package grp5.cdio.solitairesolver.Model;

import java.util.ArrayList;

public class Pile {
    private ArrayList<Card> cards;

    public Pile(int numberOfCards){
        cards = new ArrayList<Card>();
        for(int i = 1; i <= numberOfCards; i++){
            cards.add(new Card());
        }
    }

    public void setCard(int index, Card card){
        cards.set(index, card);
    }

    @Override
    public String toString(){
        String returnValue = "";
        for (Card card: cards){
            returnValue = returnValue + card.toString();
        }
        return returnValue;
    }

    public boolean isEqual(Card toTest){
        return cards.get(cards.size()-1).isEqual(toTest);
    }

    public boolean isLegalMove(Card toCard){
        return toCard.isLegalMove(cards.get(cards.size()-1));
    }

    public boolean makeMove(Card cardToMove){
        if (isLegalMove(cardToMove)){
            cards.add(cardToMove);
            return true;
        }
        return false;
    }

    public void removeCard(){
        cards.remove(cards.get(cards.size()-1));
    }

}
