package grp5.cdio.solitairesolver;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
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
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ObjectDetection objectDetection = new ObjectDetection(appContext);
        ClassLoader classLoader = objectDetection.getClass().getClassLoader();
        URL resource = classLoader.getResource("/image/test.png");
        Bitmap bitmap = BitmapFactory.decodeFile(resource.getPath());
        objectDetection.analyzeImage(bitmap);
    }
}