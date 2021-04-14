package grp5.cdio.solitairesolver.Model;
public class Move {
    /**
     * Move class.
     * <p>
     * Used to mange the card moves
     * moveFrom - pile to move card from
     * moveTo - pile to move card to
     * card - card to move
     * score - value of move, use to determine best move
     */
    private Pile moveFrom;
    private Pile moveTo;
    private Card card;
    private int score;

    /**
     * Create card, score is set by {@link #setScore}
     */
    public Move(Pile from, Pile to, Card card){
        moveFrom = from;
        moveTo = to;
        this.card = card;
        setScore();
    }

    /**
     * test if move is inverse
     *
     * @param oldMove, test if this in inverse of old move
     * @return boolean, true = inverse move
     */
    public boolean isInverseMove(Move oldMove){
        Card to = moveTo.getTopCard();
        Card from = moveFrom.getTopCard();

        if (oldMove.moveFrom.isEqual(to) && oldMove.moveTo.isEqual(from) && oldMove.card.isEqual(card)){
            return true;
        }
        return false;
    }

    /**
     * make move from piles movefrom -> moveto
     */
    public void makeMove(){
        moveFrom.removeCard();
        moveTo.makeMove(card);
    }

    /**
     * set score based on Move attributes
     */
    public void setScore() {
        //TODO vi skal have en måde at afgøre typen af bunke, for at kunne afgør score
        //Interface?
        //Hele flow skal testet med unittest
        switch (card.getIntValue()){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;

        }
    }
    /**
     * get Score
     */
    public int getScore() {
        return score;
    }

    /**
     * Override toString
     */
    @Override
    public String toString(){

        return "Move "+card+" from " + moveFrom.toString() + " to " + moveTo.toString();
    }
}


//        Point list for setScore()

//        Liste Træk TestMuligeTræk(Bord){
//
//        for byggestablerne{
//        TestMarkør(Bord, bagerst synlige kort);
//        TestMarkør(Bord, øverst synligt kort);
//        }
//
//        TestMarkør(Bord, affalds-bunke synligt kort);
//
//        for grundbunke{
//        TestMarkør(Bord, øverst synligt kort);
//        }
//
//        }
//
//        TestMarkør(Bord, markør vi kigger på){
//        if(markør == ES && !ligger i grundbunke) return Træk(95 + (1-6))
//        if(markør == 2 && match ES i grundbunke && !ligger i grundbunke) return Træk(85 + (1-6))
//
//        if (markør vi kigger på = Øverst synligt kort || bagerst synlige kort)
//        if (markør == Konge && byggestak[i].isEmpty() return Træk(75 + (1-6) )
//        if (markør == Konge && (whole)byggestak[i].moveableBygge() return Træk(65+(1-6))
//        if (markør == Konge &&  byggestak[i][øverst kort].moveableGrund() &&
//        byggestak[i].elements== 1 return Træk(55+(1-6))
//
//        if ( !(markør)byggestak[i].isAllVisible() && (whole)byggestak[i].moveable(anden byggestak) return Træk(45 + (1-6))
//
//        if ( !(markør)byggestak[i].isAllVisible() && byggestak[i][øverst kort].moveable(Grund bunke) return 35 + (1-6))
//
//        if (!byggestak[i].isEmpyt() && markør == konge && (whole)byggestak[i].moveable(anden byggestak)) return 32)
//
//        if (!byggestak[i].isEmpyt() && markør == konge && (whole)byggestak[i].moveable(Grund bunke)) return 31)
//
//        if(!bunke.isEmpty && affaldbunke.isEmpty() return 30)
//        if(!bunke.isEmpty && affaldbunke.isEmpty() return 30 - (0-6))
//
//        if(!affaldbunke.isEmpty() && byggestak[i].moveable(affaldbunke)) return = 30 - (0-6), alt efter skjult bunke størrelse)
//
//        (!affaldbunke.isEmpty() && grundstak[i].moveable(affaldbunke)) return  20(- kort værdi)
//
//        (!bunke.isEmpty)return 1
//
//        if (bunke.isEmpty && !affaldbunke.isEmpty() && !udentræk) return 1
//
//        if(bunke.isEmpty && !affalds-bunke.isEmpty && !udentræk) return Træk(1)
//
//
//        If(Markør.isLegal(grundbunke)) return Træk(- 0 (- kort værdi))
//
//        if(!udentræk?) return Træk(-1000)