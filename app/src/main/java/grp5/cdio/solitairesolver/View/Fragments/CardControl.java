/*
Author: Karl Emil Hansen
Collaborator(s): Simon Fridolf, Kristoffer Baumgarten, Elinor Mohr Mikkelsen, Simon Søborg
 */
package grp5.cdio.solitairesolver.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import grp5.cdio.solitairesolver.Model.BasePile;
import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Suit;
import grp5.cdio.solitairesolver.Model.Table;
import grp5.cdio.solitairesolver.R;
import grp5.cdio.solitairesolver.Controller.Controller;


public class CardControl extends Fragment {

    Table genTable;
    ArrayList<Card> cards;
    ListView buildPile1, buildPile2, buildPile3, buildPile4, buildPile5, buildPile6,
             buildPile7, groundPile1, groundPile2, groundPile3, groundPile4, discardPile;
    ImageView drawPileExist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View controlFrag = inflater.inflate(R.layout.fragment_card_control, container, false);
        super.onCreate(savedInstanceState);
        Button tagBillede = controlFrag.findViewById(R.id.tag_billede_igen);
        Button fortsaet = controlFrag.findViewById(R.id.Continue);

        Controller con = Controller.getInstance();

        drawPileExist = controlFrag.findViewById(R.id.cardback);

        discardPile = controlFrag.findViewById(R.id.Waste3Cards);

        buildPile1 = controlFrag.findViewById(R.id.kortene_i_Tableau1);
        buildPile2 = controlFrag.findViewById(R.id.kortene_i_Tableau2);
        buildPile3 = controlFrag.findViewById(R.id.kortene_i_Tableau3);
        buildPile4 = controlFrag.findViewById(R.id.kortene_i_Tableau4);
        buildPile5 = controlFrag.findViewById(R.id.kortene_i_Tableau5);
        buildPile6 = controlFrag.findViewById(R.id.kortene_i_Tableau6);
        buildPile7 = controlFrag.findViewById(R.id.kortene_i_Tableau7);

        groundPile1 = controlFrag.findViewById(R.id.groundPile1);
        groundPile2 = controlFrag.findViewById(R.id.groundPile2);
        groundPile3 = controlFrag.findViewById(R.id.groundPile3);
        groundPile4 = controlFrag.findViewById(R.id.groundPile4);


        ArrayList<ListView> buildPiles = new ArrayList<>();
        buildPiles.add(buildPile1);
        buildPiles.add(buildPile2);
        buildPiles.add(buildPile3);
        buildPiles.add(buildPile4);
        buildPiles.add(buildPile5);
        buildPiles.add(buildPile6);
        buildPiles.add(buildPile7);

        ArrayList<ListView> groundPiles = new ArrayList<>();
        groundPiles.add(groundPile1);
        groundPiles.add(groundPile2);
        groundPiles.add(groundPile3);
        groundPiles.add(groundPile4);

        PileAdapter adapter;


        // Make Table from ObjDect
        Table table = con.getTable();
        // Make Table from ObjDect


        // Test Table
        // Table table = genTable();
        // con.table = table;
        // Test Table



        if (table.getDrawPile() != null) {
            drawPileExist.setVisibility(View.VISIBLE);
        }

        if (table.getDiscardPile().size() != 0) {
            cards = table.getDiscardPile().getCards();
            adapter = new PileAdapter(cards);
            discardPile.setAdapter(adapter);
            discardPile.setVisibility(View.VISIBLE);
        }



        for (int i = 0; i < buildPiles.size(); i++) {
            cards = table.buildPile.get(i).getCards();
            adapter = new PileAdapter(cards);
            buildPiles.get(i).setAdapter(adapter);
            buildPiles.get(i).setVisibility(View.VISIBLE);
        }



        for (int i = 0; i < groundPiles.size(); i++) {
            cards = new ArrayList<>();
            if (table.groundPile.get(i).getTopCard() != null) {
                cards.add(table.groundPile.get(i).getTopCard());
                adapter = new PileAdapter(cards);
                groundPiles.get(i).setAdapter(adapter);
                groundPiles.get(i).setVisibility(View.VISIBLE);
            }
        }


        tagBillede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction()
                        .replace(R.id.FragmentFL, new CameraFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        fortsaet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //con.accounForMove();
                assert getFragmentManager() != null;

                Bundle argumemt = new Bundle();
                String bestMove = con.getMove().toString();
                argumemt.putString("bestMove", bestMove);

                Resultat resultatFrag = new Resultat();
                resultatFrag.setArguments(argumemt);

                getFragmentManager().beginTransaction()
                        .replace(R.id.FragmentFL, resultatFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return controlFrag;
    }

    private class PileAdapter extends BaseAdapter {

        private final List<Card> items;
        ImageView suit, back;
        TextView faceValue;

        public PileAdapter(List<Card> cards) {
            this.items = cards;
        }

        // override other abstract methods here
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Card item = items.get(position);
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.card_item, parent, false);
            }

            suit = convertView.findViewById(R.id.suit);
            back = convertView.findViewById(R.id.back);
            faceValue = convertView.findViewById(R.id.faceValue4);

            if (item != null) {
                if (item.getSuit().ordinal() != 0) {
                    suit.setVisibility(View.VISIBLE);
                    faceValue.setVisibility(View.VISIBLE);
                    back.setVisibility(View.GONE);
                    findCardValues(item);
                }
            }


            return convertView;
        }

        private void findCardValues(Card item) {
            // finds suit and color of the card
            int color = getResources().getColor(R.color.black);
            switch (item.getSuit().ordinal()) {
                case 1:
                    Picasso.get().load(R.drawable.hearts).into(suit);
                    color = getResources().getColor(R.color.mørkerød);
                    break;
                case 2:
                    Picasso.get().load(R.drawable.clubs).into(suit);
                    color = getResources().getColor(R.color.black);
                    break;
                case 3:
                    Picasso.get().load(R.drawable.diamonds).into(suit);
                    color = getResources().getColor(R.color.mørkerød);
                    break;
                case 4:
                    Picasso.get().load(R.drawable.spades).into(suit);
                    color = getResources().getColor(R.color.black);
                    break;
            }

            // Finds face value of the card
            faceValue.setTextColor(color);
            switch (item.getValue().ordinal()) {
                case 1:
                    faceValue.setText("A");
                    break;
                case 2:
                    faceValue.setText("2");
                    break;
                case 3:
                    faceValue.setText("3");
                    break;
                case 4:
                    faceValue.setText("4");
                    break;
                case 5:
                    faceValue.setText("5");
                    break;
                case 6:
                    faceValue.setText("6");
                    break;
                case 7:
                    faceValue.setText("7");
                    break;
                case 8:
                    faceValue.setText("8");
                    break;
                case 9:
                    faceValue.setText("9");
                    break;
                case 10:
                    faceValue.setText(getResources().getText(R.string.ten));
                    break;
                case 11:
                    faceValue.setText("J");
                    break;
                case 12:
                    faceValue.setText("Q");
                    break;
                case 13:
                    faceValue.setText("K");
                    break;
            }
        }
    }








    public Table genTable(){
        Table table = new Table();
        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardKingDiamonds = new Card(Suit.DIAMONDS, FaceValue.THIRTEEN);
        Card cardThreeDiamonds = new Card(Suit.DIAMONDS, FaceValue.THREE);

        Card cardTenHearts = new Card(Suit.HEARTS, FaceValue.TEN);
        Card cardJackHearts = new Card(Suit.HEARTS, FaceValue.ELEVEN);
        Card cardJackDiamonds = new Card(Suit.DIAMONDS, FaceValue.ELEVEN);

        Card cardFiveClubs = new Card(Suit.CLUBS, FaceValue.FIVE);
        Card cardFiveSpades = new Card(Suit.SPADES, FaceValue.FIVE);

        table.getDiscardPile().addCard(cardThreeDiamonds);
        table.getDiscardPile().addCard(cardJackDiamonds);
        //table.getDiscardPile().addCard(new Card(Suit.CLUBS, FaceValue.TWO));


        table.buildPile.get(0).addCard(cardQueenHearts);
        table.buildPile.get(1).addCard(cardKingDiamonds);
        table.buildPile.get(2).addCard(cardTenHearts);
        //table.buildPile.get(3).addCard(new Card(Suit.CLUBS, FaceValue.THREE));
        table.buildPile.get(4).addCard(cardFiveClubs);
        table.buildPile.get(1).addCard(new Card(Suit.CLUBS, FaceValue.TWELVE));
        table.buildPile.get(1).addCard(cardJackHearts);
        table.buildPile.get(6).addCard(new Card(Suit.DIAMONDS, FaceValue.EIGHT));
        table.buildPile.get(6).addCard(new Card(Suit.SPADES, FaceValue.SEVEN));
        table.buildPile.get(6).addCard(new Card(Suit.HEARTS, FaceValue.SIX));
        table.buildPile.get(6).addCard(cardFiveSpades);
        //table.buildPile.get(6).addCard(new Card(Suit.DIAMONDS, FaceValue.FOUR));

        table.groundPile.get(0).addCard(new Card(Suit.HEARTS, FaceValue.TWO));
        table.groundPile.get(1).addCard(new Card(Suit.SPADES, FaceValue.THREE));
        table.groundPile.get(2).addCard(new Card(Suit.DIAMONDS, FaceValue.TWO));
        table.groundPile.get(3).addCard(new Card(Suit.CLUBS, FaceValue.THREE));




        return table;
    }


}