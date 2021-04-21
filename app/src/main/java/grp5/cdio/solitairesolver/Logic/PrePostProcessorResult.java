package grp5.cdio.solitairesolver.Logic;

import android.graphics.Rect;

public class PrePostProcessorResult {
    int classIndex;
    Float score;
    Rect rect;

    public PrePostProcessorResult(int cls, Float output, Rect rect) {
        this.classIndex = cls;
        this.score = output;
        this.rect = rect;
    }
}
