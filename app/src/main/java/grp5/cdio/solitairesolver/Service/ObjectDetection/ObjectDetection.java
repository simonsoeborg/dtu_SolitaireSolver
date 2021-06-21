/*
// Based https://github.com/pytorch/android-demo-app/tree/master/ObjectDetection
Author: Simon Fridolf
Collaborator(s): Karl Emil Hansen, Simon Søborg, Elinor Mohr Mikkelsen
 */
package grp5.cdio.solitairesolver.Service.ObjectDetection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.pytorch.IValue;
import org.pytorch.Module;
import org.pytorch.PyTorchAndroid;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Suit;
import grp5.cdio.solitairesolver.Model.Table;


public class ObjectDetection {
    private Module mModule = null;
    private final Context context;

    public static String DRAW_PILE = "draw";
    public static String BUILD_PILE = "build";
    public static String GROUND_PILE = "ground";
    public static Bitmap total;
    public Table table = new Table();

    public ObjectDetection(Context context) {
        this.context = context;
    }

    /**
     * Get Table from bitmaps
     *
     * @param map HashMap of bitmaps. Key is draw, build or ground.
     */
    public Table analyzeImage(HashMap<String, Bitmap> map) throws IOException {
        Bitmap build = map.get(BUILD_PILE);
        Bitmap draw = map.get(DRAW_PILE);
        Bitmap ground = map.get(GROUND_PILE);
        total = map.get("total");

      /*  Float totalScore = new Float(0);
        for(Result result : resultArrayList){
            totalScore = totalScore + result.getScore();
        }
        Float acc = totalScore/resultArrayList.size();*/

        // Collections.sort(resultArrayListBuild);

        ArrayList<Result> resultArrayListBuild = analyzeBitmap(build);
        resultArrayListBuild = sortList(resultArrayListBuild);
        resultArrayListBuild = removeDuplicates(resultArrayListBuild);
        table = buildPileSorter(resultArrayListBuild, table);

        ArrayList<Result> resultArrayListGround = analyzeBitmap(ground);
        resultArrayListGround = sortList(resultArrayListGround);
        resultArrayListGround = removeDuplicates(resultArrayListGround);
        table = groundPileSorter(resultArrayListGround, table);

        ArrayList<Result> resultArrayListDraw = analyzeBitmap(draw);
        resultArrayListDraw = sortList(resultArrayListDraw);
        resultArrayListDraw = removeDuplicates(resultArrayListDraw);
        table = discardPileSorter(resultArrayListDraw, table);

        return table;
    }

    /**
     * analyze Bitmaps
     *
     * @param bitmap to analyse 
     */
    public ArrayList<Result> analyzeBitmap(Bitmap bitmap) throws IOException {
        float mImgScaleX, mImgScaleY, mIvScaleX, mIvScaleY, mStartX, mStartY;
        if (mModule == null) {
            mModule = PyTorchAndroid.loadModuleFromAsset(context.getAssets(), "new.model.1585.torchscript.pt");
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("cards.classes.2.txt")));
        String line;
        List<String> classes = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            classes.add(line);
        }
        PrePostProcessor.mClasses = new String[classes.size()];
        Log.d("init", "classes: " + classes.size());

        mImgScaleX = (float)bitmap.getWidth() / PrePostProcessor.mInputWidth;
        mImgScaleY = (float)bitmap.getHeight() / PrePostProcessor.mInputHeight;

        //mIvScaleX = 1;
        //mIvScaleY  = 1;

        //mStartX = bitmap.getWidth()/2;
        //mStartY = bitmap.getHeight()/2;

        mIvScaleX = (bitmap.getWidth() > bitmap.getHeight() ? (float)bitmap.getWidth() / bitmap.getWidth() : (float)bitmap.getHeight() / bitmap.getHeight());
        mIvScaleY  = (bitmap.getHeight() > bitmap.getWidth() ? (float)bitmap.getHeight() / bitmap.getHeight() : (float)bitmap.getWidth() / bitmap.getWidth());

        mStartX = (bitmap.getWidth() - mIvScaleX * bitmap.getWidth())/2;
        mStartY = (bitmap.getHeight() -  mIvScaleY * bitmap.getHeight())/2;


        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, PrePostProcessor.mInputWidth, PrePostProcessor.mInputHeight, true);
        final Tensor inputTensor = TensorImageUtils.bitmapToFloat32Tensor(resizedBitmap, PrePostProcessor.NO_MEAN_RGB, PrePostProcessor.NO_STD_RGB);
        IValue[] outputTuple = mModule.forward(IValue.from(inputTensor)).toTuple();
        final Tensor outputTensor = outputTuple[0].toTensor();
        final float[] outputs = outputTensor.getDataAsFloatArray();
        Log.d("Run", "" + outputs.length);
        return PrePostProcessor.outputsToNMSPredictions(outputs, mImgScaleX, mImgScaleY, mIvScaleX, mIvScaleY, mStartX, mStartY);

    }

    public Table getTable() {
        return table;
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

    private Table buildPileSorter(ArrayList<Result> result, Table table) {
        ArrayList<Result> b0 = new ArrayList<>();
        ArrayList<Result> b1 = new ArrayList<>();
        ArrayList<Result> b2 = new ArrayList<>();
        ArrayList<Result> b3 = new ArrayList<>();
        ArrayList<Result> b4 = new ArrayList<>();
        ArrayList<Result> b5 = new ArrayList<>();
        ArrayList<Result> b6 = new ArrayList<>();

        int width = total.getWidth() * 7/8;

        for (Result o : result) {
            int x = o.getRect().left + total.getWidth() / 28;
            if (0 < x && x < width/7) {
                b0.add(o);
            } else if (width/7 < x && x < 2*width/7) {
                b1.add(o);
            } else if (2*width/7 < x && x < 3*width/7) {
                b2.add(o);
            } else if (3*width/7 < x && x < 4*width/7) {
                b3.add(o);
            } else if (4*width/7 < x && x < 5*width/7) {
                b4.add(o);
            } else if (5*width/7 < x && x < 6*width/7) {
                b5.add(o);
            } else if (6*width/7 < x && x < width) {
                b6.add(o);
            }
        }

        Collections.sort(b0);
        Collections.sort(b1);
        Collections.sort(b2);
        Collections.sort(b3);
        Collections.sort(b4);
        Collections.sort(b5);
        Collections.sort(b6);

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

    private Table groundPileSorter(ArrayList<Result> result, Table table) {

        int width = total.getWidth() / 2;
        Card card;

        for (Result o : result) {
            int x = o.getRect().left + total.getWidth() / 28;
            card = getNames(o);
            if (0 < x && x < width/4) {
                table.groundPile.get(0).addCard(card);
            } else if (width/7 < x && x < 2*width/4) {
                table.groundPile.get(1).addCard(card);
            } else if (2*width/7 < x && x < 3*width/4) {
                table.groundPile.get(2).addCard(card);
            } else if (3*width/7 < x && x < width) {
                table.groundPile.get(3).addCard(card);
            }
        }

        return table;
    }

    private Table discardPileSorter(ArrayList<Result> result, Table table) {

        Card card;

        for (Result o : result) {
            card = getNames(o);
            table.discardPile.addCard(card);
        }
        return table;
    }


}
