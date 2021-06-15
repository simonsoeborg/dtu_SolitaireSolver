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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Suit;
import grp5.cdio.solitairesolver.Service.ObjectDetection.ObjectDetection;
import grp5.cdio.solitairesolver.Service.ObjectDetection.Result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ObjectDetectionTest {
    @Test
    public void useAppContext() throws IOException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ObjectDetection objectDetection = new ObjectDetection(appContext);
        AssetManager am = appContext.getAssets();
        InputStream is = am.open("test.jpg");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
        Bitmap bit = BitmapFactory.decodeStream(bufferedInputStream);
        ArrayList<Result> resultArrayList = objectDetection.analyzeBitmap(bit);

        // top left - lav left giver mest left maks 2 instanser af hvert kort
        // bottom right

        //prøv evt at teste med andre billeder
        // cluster metode i java?

        // Fundet alle med samme id
        // lave linje med top left, evt byg buffer løbende(Se på hvad der er af afstand pt fra de andres billeder)
        // Dannet kort basseret på ID
        // lagt kort i matchende bunke fra top left


        // !!! Testen virker kun ved debugging!!!
        FaceValue[] values = {FaceValue.TEN, FaceValue.TWO, FaceValue.THREE, FaceValue.FOUR ,FaceValue.FIVE, FaceValue.SIX , FaceValue.SEVEN, FaceValue.EIGHT ,FaceValue.NINE, FaceValue.ONE,
                            FaceValue.ELEVEN, FaceValue.THIRTEEN, FaceValue.TWELVE};

        int val;
        for (int i = 0; i < resultArrayList.size() ; i++) {

            if ((resultArrayList.get(i).getClassIndex()+1)%4==3) {

                val = resultArrayList.get(i).getClassIndex()/4;
                Card nameTester = new Card(Suit.HEARTS, values[val]);
                System.out.println(nameTester);
            }

            else if ((resultArrayList.get(i).getClassIndex()+1)%4==0) {
                val = resultArrayList.get(i).getClassIndex()/4;
                Card nameTester = new Card(Suit.SPADES, values[val]);
                System.out.println(nameTester);
            }

            else if ((resultArrayList.get(i).getClassIndex()+1)%4==2) {
                val = resultArrayList.get(i).getClassIndex()/4;
                Card nameTester = new Card(Suit.DIAMONDS, values[val]);
                System.out.println(nameTester);
            }

            else if ((resultArrayList.get(i).getClassIndex()+1)%4==1) {
                val = resultArrayList.get(i).getClassIndex()/4;
                Card nameTester = new Card(Suit.CLUBS, values[val]);
                System.out.println(nameTester);
            }
        }

        Float totalScore = new Float(0);
        for(Result result : resultArrayList){
            totalScore = totalScore + result.getScore();
        }
        Float acc = totalScore/resultArrayList.size();


        ArrayList<Result> sortedList = new ArrayList<>();

        sortedList.add(resultArrayList.get(0));
        for (Result element : resultArrayList) {

            for (int i = 0; i < sortedList.size(); i++) {

                if (sortedList.get(i).getClassIndex()!=element.getClassIndex()){

                    if (sortedList.get(i).getRect().left < element.getRect().left){
                        sortedList.add(element);
                    }
                    else{
                        sortedList.add(i, element);
                    }
                }
            }
        }
    }
}