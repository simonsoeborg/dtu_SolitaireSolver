/*
// From https://github.com/pytorch/android-demo-app/tree/master/ObjectDetection
Author: Karl Emil Hansen
Collaborator(s): Simon Fridolf
 */

package grp5.cdio.solitairesolver.Service.ObjectDetection;

import android.graphics.Rect;


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
