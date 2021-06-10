package grp5.cdio.solitairesolver.Controller.ObjectDetection;

import android.content.Context;
import android.graphics.Bitmap;
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
import java.util.HashMap;
import java.util.List;

import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Suit;
import grp5.cdio.solitairesolver.Model.Table;

// Based https://github.com/pytorch/android-demo-app/tree/master/ObjectDetection
public class ObjectDetection {
    private Module mModule = null;
    private final Context context;

    public ObjectDetection(Context context) {
        this.context = context;
    }

    /**
     * Get Table from bitmaps
     *
     * @param map HashMap of bitmaps. Key is draw, build or ground
     */
    public Table analyzeImage(HashMap<String, Bitmap> map){

        Table table = new Table();
        Card cardQueenHearts = new Card(Suit.HEARTS, FaceValue.TWELVE);
        Card cardKingHearts = new Card(Suit.HEARTS, FaceValue.THIRTEEN);

        Card cardFourHearts= new Card(Suit.HEARTS, FaceValue.FOUR);
        Card cardJackHearts = new Card(Suit.HEARTS, FaceValue.ELEVEN);

        Card cardFiveHearts = new Card(Suit.HEARTS, FaceValue.FIVE);
        Card cardFiveClubs = new Card(Suit.CLUBS, FaceValue.FIVE);
        Card cardFiveSpades = new Card(Suit.SPADES, FaceValue.FIVE);

        table.buildPile.get(0).setCard(0, cardQueenHearts);
        table.buildPile.get(1).setCard(1, cardKingHearts);
        table.buildPile.get(2).setCard(2, cardFourHearts);
        table.buildPile.get(3).setCard(3, cardJackHearts);
        table.buildPile.get(4).setCard(4, cardFiveClubs);
        table.buildPile.get(5).setCard(5, cardFiveHearts);
        table.buildPile.get(6).setCard(6, cardFiveSpades);
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
            mModule = PyTorchAndroid.loadModuleFromAsset(context.getAssets(), "best.torchscript.2.pt");
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
}
