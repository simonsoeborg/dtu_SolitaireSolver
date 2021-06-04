package grp5.cdio.solitairesolver;

import org.junit.Test;

import grp5.cdio.solitairesolver.Controller.Controller;
import grp5.cdio.solitairesolver.Model.Move;


public class ControllerTest {
    @Test
    public void test() {
        Controller controller = new Controller();
        Move move = controller.getMove(new Object());
        System.out.print(move);
    }
}
