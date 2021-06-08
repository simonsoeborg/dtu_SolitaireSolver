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
import java.util.List;

// Based https://github.com/pytorch/android-demo-app/tree/master/ObjectDetection
public class ObjectDetection {
    private Module mModule = null;
    private final Context context;

    public ObjectDetection(Context context) {
        this.context = context;
    }
    public ArrayList<Result> analyzeImage(Bitmap bitmap) throws IOException {
        float mImgScaleX, mImgScaleY, mIvScaleX, mIvScaleY, mStartX, mStartY;
        if (mModule == null) {
            mModule = PyTorchAndroid.loadModuleFromAsset(context.getAssets(), "best.torchscript.pt");
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("cards.classes.txt")));
        String line;
        List<String> classes = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            classes.add(line);
        }
        PrePostProcessor.mClasses = new String[classes.size()];
        Log.d("init", "classes: " + classes.size());

        mImgScaleX = (float)bitmap.getWidth() / PrePostProcessor.mInputWidth;
        mImgScaleY = (float)bitmap.getHeight() / PrePostProcessor.mInputHeight;

        mIvScaleX = 1;
        mIvScaleY  = 1;

        mStartX = bitmap.getWidth()/2;
        mStartY = bitmap.getHeight()/2;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, PrePostProcessor.mInputWidth, PrePostProcessor.mInputHeight, true);
        final Tensor inputTensor = TensorImageUtils.bitmapToFloat32Tensor(resizedBitmap, PrePostProcessor.NO_MEAN_RGB, PrePostProcessor.NO_STD_RGB);
        IValue[] outputTuple = mModule.forward(IValue.from(inputTensor)).toTuple();
        final Tensor outputTensor = outputTuple[0].toTensor();
        final float[] outputs = outputTensor.getDataAsFloatArray();
        Log.d("Run", "" + outputs.length);
        return PrePostProcessor.outputsToNMSPredictions(outputs, mImgScaleX, mImgScaleY, mIvScaleX, mIvScaleY, mStartX, mStartY);


    }
}
