package grp5.cdio.solitairesolver;

import android.graphics.Bitmap;

import org.junit.Test;

import java.util.HashMap;

import grp5.cdio.solitairesolver.Controller.Controller;
import grp5.cdio.solitairesolver.Model.Move;


public class ControllerTest {
    @Test
    public void test() {
        Controller controller = new Controller();
        Move move = controller.getMove(new HashMap<String, Bitmap >());
        System.out.print(move);
    }
}
