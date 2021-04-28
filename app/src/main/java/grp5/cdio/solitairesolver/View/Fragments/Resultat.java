package grp5.cdio.solitairesolver.View.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import java.util.Objects;

import grp5.cdio.solitairesolver.Logic.PrePostProcessor;
import grp5.cdio.solitairesolver.Logic.PrePostProcessorResult;
import grp5.cdio.solitairesolver.R;
import grp5.cdio.solitairesolver.View.PhotoHandler;
import grp5.cdio.solitairesolver.View.StartLoadingScreen;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class Resultat extends Fragment implements Runnable {
    private ImageView mImageView;
    private Button mButtonDetect;
    private ProgressBar mProgressBar;
    private Bitmap mBitmap = null;
    private Module mModule = null;
    private float mImgScaleX, mImgScaleY, mIvScaleX, mIvScaleY, mStartX, mStartY;
    private Context context;
    TextView instructionTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View resultatFrag = inflater.inflate(R.layout.fragment_resultat, container, false);
        context = StartLoadingScreen.context;
        instructionTV = resultatFrag.findViewById(R.id.instructionTV_resultatFrag);
        instructionTV.setVisibility(View.INVISIBLE);
        mProgressBar = resultatFrag.findViewById(R.id.resultProgressBar);
        mProgressBar.setVisibility(View.INVISIBLE);
        mButtonDetect = resultatFrag.findViewById(R.id.TagBilledeBtn_resultatFrag);


        mButtonDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Her skal stien til billedet der lige er blevet taget gives
                assert getArguments() != null;
                loadAssets(getArguments().getString("image"));
                mButtonDetect.setEnabled(false);
                mProgressBar.setVisibility(ProgressBar.VISIBLE);

                mImgScaleX = (float)mBitmap.getWidth() / PrePostProcessor.mInputWidth;
                mImgScaleY = (float)mBitmap.getHeight() / PrePostProcessor.mInputHeight;
                mIvScaleX = (mBitmap.getWidth() > mBitmap.getHeight() ? (float)mImageView.getWidth() / mBitmap.getWidth() : (float)mImageView.getHeight() / mBitmap.getHeight());
                mIvScaleY  = (mBitmap.getHeight() > mBitmap.getWidth() ? (float)mImageView.getHeight() / mBitmap.getHeight() : (float)mImageView.getWidth() / mBitmap.getWidth());
                mStartX = (mImageView.getWidth() - mIvScaleX * mBitmap.getWidth())/2;
                mStartY = (mImageView.getHeight() -  mIvScaleY * mBitmap.getHeight())/2;

                Thread thread = new Thread(Resultat.this);
                thread.start();
            }
        });

        return resultatFrag;
    }

    /**
     * Indlæser modellen og billedet, så det kan behandles
     * @param image
     */
    public void loadAssets(String image) {
        Log.d("loadAssets", "Loading image: "+image);
        try {
            mModule = PyTorchAndroid.loadModuleFromAsset(context.getAssets(), "model.torchscript.pt");
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("cards.classes.txt")));
            String line;
            List<String> classes = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                classes.add(line);
            }
            PrePostProcessor.mClasses = new String[classes.size()];
            classes.toArray(PrePostProcessor.mClasses);
        } catch (IOException e) {
            Log.d("loadAssets", e.getMessage());
        }
        mBitmap = BitmapFactory.decodeFile(image);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            if (resultCode == RESULT_OK && data != null) {
                mBitmap = (Bitmap) data.getExtras().get("data");
                Matrix matrix = new Matrix();
                matrix.postRotate(90.0f);
                mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
                mImageView.setImageBitmap(mBitmap);
            }
        }
    }

    @Override
    public void run() {
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(mBitmap, PrePostProcessor.mInputWidth, PrePostProcessor.mInputHeight, true);
        final Tensor inputTensor = TensorImageUtils.bitmapToFloat32Tensor(resizedBitmap, PrePostProcessor.NO_MEAN_RGB, PrePostProcessor.NO_STD_RGB);
        IValue[] outputTuple = mModule.forward(IValue.from(inputTensor)).toTuple();
        final Tensor outputTensor = outputTuple[0].toTensor();
        final float[] outputs = outputTensor.getDataAsFloatArray();
        Log.d("Run", "" + outputs.length);
        final ArrayList<PrePostProcessorResult> results =  PrePostProcessor.outputsToNMSPredictions(outputs, mImgScaleX, mImgScaleY, mIvScaleX, mIvScaleY, mStartX, mStartY);

        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            mButtonDetect.setEnabled(true);
            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
            instructionTV.setVisibility(View.VISIBLE);
        });
    }
}