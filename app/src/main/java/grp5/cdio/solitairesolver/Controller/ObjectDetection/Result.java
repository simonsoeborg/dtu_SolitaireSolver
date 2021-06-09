package grp5.cdio.solitairesolver.Controller.ObjectDetection;

import android.graphics.Rect;

// From https://github.com/pytorch/android-demo-app/tree/master/ObjectDetection
public class Result {
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

}
