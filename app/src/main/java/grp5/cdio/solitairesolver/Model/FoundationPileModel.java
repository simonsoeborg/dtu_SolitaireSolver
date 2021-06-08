package grp5.cdio.solitairesolver.Model;

import java.util.ArrayList;

public class FoundationPileModel {
    public boolean isEmpty;
    private ArrayList<CardModel> Pile;
    private String ThisFoundationPile;

    public String getThisFoundationPile() {
        return ThisFoundationPile;
    }

    public void setThisFoundationPile(String thisFoundationPile) {
        ThisFoundationPile = thisFoundationPile;
    }

    public ArrayList<CardModel> getPile() {
        return Pile;
    }

    public void setPile(ArrayList<CardModel> pile) {
        Pile = pile;
    }
}
