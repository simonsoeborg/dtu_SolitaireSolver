package grp5.cdio.solitairesolver.Model;

import java.util.ArrayList;

public class BuildPileModel {
    public boolean isEmpty = true;
    private ArrayList<CardModel> Pile = new ArrayList<>();

    public ArrayList<CardModel> getPile() {
        return Pile;
    }

    public void setPile(ArrayList<CardModel> buildPile) {
        Pile = buildPile;
    }
}
