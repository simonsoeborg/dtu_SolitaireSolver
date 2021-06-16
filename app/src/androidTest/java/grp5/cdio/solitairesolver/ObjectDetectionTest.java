package grp5.cdio.solitairesolver;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Suit;
import grp5.cdio.solitairesolver.Model.Table;
import grp5.cdio.solitairesolver.Service.ObjectDetection.ObjectDetection;
import grp5.cdio.solitairesolver.Service.ObjectDetection.Result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ObjectDetectionTest {

    Bitmap bit;

    @Test
    public void useAppContext() throws IOException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ObjectDetection objectDetection = new ObjectDetection(appContext);
        AssetManager am = appContext.getAssets();
        InputStream is = am.open("img_test4_build_black.jpg");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
        bit = BitmapFactory.decodeStream(bufferedInputStream);
        ArrayList<Result> resultArrayList = objectDetection.analyzeBitmap(bit);

        // top left - lav left giver mest left maks 2 instanser af hvert kort
        // bottom right

        //prøv evt at teste med andre billeder
        // cluster metode i java?

        // Fundet alle med samme id
        // lave linje med top left, evt byg buffer løbende(Se på hvad der er af afstand pt fra de andres billeder)
        // Dannet kort basseret på ID
        // lagt kort i matchende bunke fra top left


        Float totalScore = new Float(0);
        for(Result result : resultArrayList){
            totalScore = totalScore + result.getScore();
        }
        Float acc = totalScore/resultArrayList.size();

        ArrayList<Result> sortedResultArrayList = sortList(resultArrayList);

        Collections.sort(resultArrayList);

        ArrayList<Result> DoneResultArrayList = removeDuplicates(sortedResultArrayList);

        Table table = cardSorter(DoneResultArrayList);

        System.out.println(table.toString());

        System.out.println("havelåge");
    }

    public ArrayList<Result> sortList(ArrayList<Result> arr) {
        ArrayList<Result> sortedList = new ArrayList<>();
        if (arr.size() > 0) {
            sortedList.add(arr.get(0));
        }
        for (int i = 0; i < arr.size(); i++) {
            Result currentObj = arr.get(i);
            if(!sortedList.contains(currentObj)) {
                for (int j = 0; j < sortedList.size(); j++) {
                    if(currentObj.getRect().left < sortedList.get(j).getRect().left) {
                        sortedList.add(j, currentObj);
                        break;
                    }
                }
                if(!sortedList.contains(currentObj)) {
                    sortedList.add(currentObj);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<Result> removeDuplicates(ArrayList<Result> arr) {
        for (int i = arr.size()-1; i > 0; i--) {
            if (contains(arr, arr.get(i).getClassIndex())) {
                arr.remove(i);
            }
        }
        return  arr;
    }

    private boolean contains(Collection<Result> c, int classIndex) {
        int counter = 0;
        for (Result o : c) {
            if (o != null && o.getClassIndex() == classIndex) {
                counter++;
            }
        }
        return counter > 1;
    }

    public Card getNames (Result result){
        FaceValue[] values = {FaceValue.TEN, FaceValue.TWO, FaceValue.THREE, FaceValue.FOUR,
                FaceValue.FIVE, FaceValue.SIX, FaceValue.SEVEN, FaceValue.EIGHT, FaceValue.NINE,
                FaceValue.ONE, FaceValue.ELEVEN, FaceValue.THIRTEEN, FaceValue.TWELVE};

        int val;

        if ((result.getClassIndex()+1)%4==3) {

            val = result.getClassIndex()/4;
            Card nameTester = new Card(Suit.HEARTS, values[val]);
            return nameTester;
        }

        else if ((result.getClassIndex()+1)%4==0) {
            val = result.getClassIndex()/4;
            Card nameTester = new Card(Suit.SPADES, values[val]);
            return nameTester;
        }

        else if ((result.getClassIndex()+1)%4==2) {
            val = result.getClassIndex()/4;
            Card nameTester = new Card(Suit.DIAMONDS, values[val]);
            return nameTester;
        }

        else if ((result.getClassIndex()+1)%4==1) {
            val = result.getClassIndex()/4;
            Card nameTester = new Card(Suit.CLUBS, values[val]);
            return nameTester;
        }
        return null;
    }

    private Table cardSorter(ArrayList<Result> result) {
        ArrayList<Result> b0 = new ArrayList<>();
        ArrayList<Result> b1 = new ArrayList<>();
        ArrayList<Result> b2 = new ArrayList<>();
        ArrayList<Result> b3 = new ArrayList<>();
        ArrayList<Result> b4 = new ArrayList<>();
        ArrayList<Result> b5 = new ArrayList<>();
        ArrayList<Result> b6 = new ArrayList<>();

        int width = bit.getWidth() * 7/8;

        for (int i = 0; i < result.size(); i++) {
            int x = result.get(i).getRect().left;
            if (0 < x && x < width/7) {
                b0.add(result.get(i));
            } else if (width/7 < x && x < 2*width/7) {
                b1.add(result.get(i));
            } else if (2*width/7 < x && x < 3*width/7) {
                b2.add(result.get(i));
            } else if (3*width/7 < x && x < 4*width/7) {
                b3.add(result.get(i));
            } else if (4*width/7 < x && x < 5*width/7) {
                b4.add(result.get(i));
            } else if (5*width/7 < x && x < 6*width/7) {
                b5.add(result.get(i));
            } else if (6*width/7 < x && x < width) {
                b6.add(result.get(i));
            }
        }

        Collections.sort(b0);
        Collections.sort(b1);
        Collections.sort(b2);
        Collections.sort(b3);
        Collections.sort(b4);
        Collections.sort(b5);
        Collections.sort(b6);

        Table table = new Table();
        Card card;

        for (Result o : b0) {
            table.buildPile.get(0).addCard(getNames(o));
        }
        for (Result o : b1) {
            table.buildPile.get(1).addCard(getNames(o));
        }
        for (Result o : b2) {
            table.buildPile.get(2).addCard(getNames(o));
        }
        for (Result o : b3) {
            table.buildPile.get(3).addCard(getNames(o));
        }
        for (Result o : b4) {
            table.buildPile.get(4).addCard(getNames(o));
        }
        for (Result o : b5) {
            table.buildPile.get(5).addCard(getNames(o));
        }
        for (Result o : b6) {
            table.buildPile.get(6).addCard(getNames(o));
        }



        return table;
    }




}