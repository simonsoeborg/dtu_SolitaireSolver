package grp5.cdio.solitairesolver.View.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import grp5.cdio.solitairesolver.Controller.ObjectDetection.ObjectDetection;
import grp5.cdio.solitairesolver.Model.BasePile;
import grp5.cdio.solitairesolver.Model.BuildPile;
import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Pile;
import grp5.cdio.solitairesolver.Model.Suit;
import grp5.cdio.solitairesolver.Model.Table;
import grp5.cdio.solitairesolver.R;


public class CardControl extends Fragment {

    Table genTable;
    ArrayList drawPile;
    ArrayList pile;
    ArrayList<Card> cards = new ArrayList<Card>();
    ListView buildPile1;
    LinearLayout buildPile2, buildPile3, buildPile4, buildPile5, buildPile6, buildPile7;
    LinearLayout groundPile1,groundPile2, groundPile3, groundPile4;
    LinearLayout drawPileExist;
    ImageView discardPile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View controlFrag = inflater.inflate(R.layout.fragment_card_control, container, false);
        super.onCreate(savedInstanceState);
        Button tagBillede = controlFrag.findViewById(R.id.tag_billede_igen);
        Button fortsaet = controlFrag.findViewById(R.id.Continue);

        drawPileExist = controlFrag.findViewById(R.id.cardback);

        discardPile = controlFrag.findViewById(R.id.Waste3Cards);

        groundPile1 = controlFrag.findViewById(R.id.Foundation1_linearLayout);
        groundPile2 = controlFrag.findViewById(R.id.Foundation2_linearLayout);
        groundPile3 = controlFrag.findViewById(R.id.Foundation3_linearLayout);
        groundPile4 = controlFrag.findViewById(R.id.Foundation4_linearLayout);

        buildPile1 = controlFrag.findViewById(R.id.kortene_i_Tableau1);
        buildPile2 = controlFrag.findViewById(R.id.kortene_i_Tableau2);
        buildPile3 = controlFrag.findViewById(R.id.kortene_i_Tableau3);
        buildPile4 = controlFrag.findViewById(R.id.kortene_i_Tableau4);
        buildPile5 = controlFrag.findViewById(R.id.kortene_i_Tableau5);
        buildPile6 = controlFrag.findViewById(R.id.kortene_i_Tableau6);
        buildPile7 = controlFrag.findViewById(R.id.kortene_i_Tableau7);

        // Test Table
        Table table = genTable();
        // Test Table

        ArrayList<ListView> buildPiles = new ArrayList<ListView>();
        buildPiles.add(buildPile1);
        CardControlAdapter adapter;
        BuildPile buildPile;

        for (int i = 0; i < 7; i++) {
            buildPile = table.buildPile.get(i);
            cards = buildPile.getCards();
            adapter = new CardControlAdapter(buildPile1, cards);
            adapter.notifyDataSetChanged();
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

                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction()
                        .replace(R.id.FragmentFL, new Resultat())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return controlFrag;
    }

    private class CardControlAdapter extends BaseAdapter {

        ArrayList<Card> cards;
        View basePile;
        ImageView suit;
        TextView faceValue;

        public CardControlAdapter(View basePile, ArrayList<Card> cards) {
            this.basePile = basePile;
            this.cards = cards;
        }

        // override other abstract methods here
        @Override
        public int getCount() {
            return 0;
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
        public View getView(int position, View convertView, ViewGroup container) {
            basePile = convertView;
            Card item = cards.get(position);
            if (basePile == null) {
                basePile = getLayoutInflater().inflate(R.layout.card_item, container, false);
            }

            suit = basePile.findViewById(R.id.suit);
            faceValue = basePile.findViewById(R.id.faceValue);

            findCardValues(item);

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
        Card cardKingHearts = new Card(Suit.HEARTS, FaceValue.THIRTEEN);

        Card cardTenHearts = new Card(Suit.HEARTS, FaceValue.TEN);
        Card cardJackHearts = new Card(Suit.HEARTS, FaceValue.ELEVEN);

        Card cardFiveHearts = new Card(Suit.HEARTS, FaceValue.FIVE);
        Card cardFiveClubs = new Card(Suit.CLUBS, FaceValue.FIVE);
        Card cardFiveSpades = new Card(Suit.SPADES, FaceValue.FIVE);

        table.buildPile.get(0).setCard(0, cardQueenHearts);
        table.buildPile.get(1).setCard(1, cardKingHearts);
        table.buildPile.get(2).setCard(2, cardTenHearts);
        table.buildPile.get(3).setCard(3, cardJackHearts);
        table.buildPile.get(4).setCard(4, cardFiveClubs);
        table.buildPile.get(5).setCard(5, cardFiveHearts);
        table.buildPile.get(6).setCard(6, cardFiveSpades);
        return table;
    }


}