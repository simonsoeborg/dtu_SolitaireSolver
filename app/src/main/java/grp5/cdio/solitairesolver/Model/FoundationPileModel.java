package grp5.cdio.solitairesolver.Model;

import java.util.ArrayList;

public class FoundationPileModel {
    public boolean isEmpty = true;
    private ArrayList<CardModel> Pile;

    public ArrayList<CardModel> getPile() {
        return Pile;
    }

    public void setPile(ArrayList<CardModel> pile) {
        Pile = pile;
    }
}
