package grp5.cdio.solitairesolver.Controller.ObjectDetection;

import android.content.Context;
import android.graphics.Bitmap;

import org.pytorch.IValue;
import org.pytorch.Module;
import org.pytorch.PyTorchAndroid;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;

import java.util.ArrayList;

public class ObjectDetection {
    private Module mModule = null;
    private final Context context;

    public ObjectDetection(Context context) {
        this.context = context;
    }
    public ArrayList<Result> analyzeImage(Bitmap bitmap){
        if (mModule == null) {
            mModule = PyTorchAndroid.loadModuleFromAsset(context.getAssets(), "best.pt");
        }
        final Tensor inputTensor = TensorImageUtils.bitmapToFloat32Tensor(bitmap, PrePostProcessor.NO_MEAN_RGB, PrePostProcessor.NO_STD_RGB);
        IValue[] outputTuple = mModule.forward(IValue.from(inputTensor)).toTuple();
        final Tensor outputTensor = outputTuple[0].toTensor();
        final float[] outputs = outputTensor.getDataAsFloatArray();
        float imgScaleX = (float)bitmap.getWidth();
        float imgScaleY = (float)bitmap.getHeight();
        float ivScaleX = (float) bitmap.getWidth();
        float ivScaleY = (float) bitmap.getHeight();

        return PrePostProcessor.outputsToNMSPredictions(outputs, imgScaleX, imgScaleY, ivScaleX, ivScaleY, 0, 0);

    }
}
