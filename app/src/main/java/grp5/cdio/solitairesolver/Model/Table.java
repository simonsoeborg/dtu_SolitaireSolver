package grp5.cdio.solitairesolver.Model;

import java.util.ArrayList;

public class Table {
    private ArrayList<Pile> groundPile; // The four "columns in the right corner.
    private ArrayList<Pile> buildPile; // The seven columns in the middle of the board.
    private ArrayList<Pile> drawPile; // The draw pile.
    private ArrayList<Pile> discardPile; // Pile of already drawn cards.

    @Override
    public String toString(){
        return null;
    }
}
