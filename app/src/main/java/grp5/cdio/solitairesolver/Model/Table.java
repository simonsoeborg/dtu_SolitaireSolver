package grp5.cdio.solitairesolver.Model;

import java.util.ArrayList;

public class Table {
    private ArrayList<Pile> groundPile; // The four "columns in the right corner.
    private ArrayList<Pile> buildPile; // The seven columns in the middle of the board.
    private Pile drawPile; // The draw pile.
    private Pile discardPile; // Pile of already drawn cards.

    public Table(){
        this.drawPile = new Pile(24);  //
        this.buildPile = new ArrayList<Pile>();
        buildPile.add(new pile(1));
        buildPile.add(new pile(2));
        buildPile.add(new pile(3));
        buildPile.add(new pile(4));
        buildPile.add(new pile(5));
        buildPile.add(new pile(6));
        buildPile.add(new pile(7));
        this.groundPile = new ArrayList<Pile>(4);
        this.discardPile = new Pile(0);
    }


    public void updateTable(Table t){
    }

 /*   public Move getLegalMoves (Table table){

    }
    */

    @Override
    public String toString(){
        return  "";
    }
}
