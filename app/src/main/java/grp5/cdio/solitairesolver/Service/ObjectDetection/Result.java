/*
// From https://github.com/pytorch/android-demo-app/tree/master/ObjectDetection
Author: Karl Emil Hansen
Collaborator(s): Simon Fridolf
 */

package grp5.cdio.solitairesolver.Service.ObjectDetection;

import android.graphics.Rect;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Result implements Comparable<Result>{
    int classIndex;
    Float score;
    Rect rect;

    public Result(int cls, Float output, Rect rect) {
        this.classIndex = cls;
        this.score = output;
        this.rect = rect;
    }
    public int getClassIndex() {
        return classIndex;
    }

    public Float getScore() {
        return score;
    }


    public Rect getRect() {
        return rect;
    }

    public int compareTo(Result result) {
        return Integer.compare(rect.top, result.getRect().top);
    }
}
