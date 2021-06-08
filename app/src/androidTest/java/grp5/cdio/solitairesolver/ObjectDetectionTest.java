package grp5.cdio.solitairesolver;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import grp5.cdio.solitairesolver.Controller.ObjectDetection.ObjectDetection;
import grp5.cdio.solitairesolver.Model.Card;
import grp5.cdio.solitairesolver.Model.FaceValue;
import grp5.cdio.solitairesolver.Model.Suit;

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
        objectDetection.analyzeImage(bit);
    }
}