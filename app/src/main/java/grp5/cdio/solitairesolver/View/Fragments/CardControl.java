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

import java.util.ArrayList;
import java.util.HashMap;

import grp5.cdio.solitairesolver.Controller.ObjectDetection.ObjectDetection;
import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Suit;
import grp5.cdio.solitairesolver.Model.Table;
import grp5.cdio.solitairesolver.R;


public class CardControl extends Fragment {

    Table genTable;
    ArrayList drawPile;
    ArrayList Pile;
    View buildPile1;
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

        // override other abstract methods here
        View

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
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.card_item, container, false);
            }

            ImageView suit = convertView.

            return convertView;
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