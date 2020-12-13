package spypunk.tetris.ui.view;

import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class TetrisControlsHandler {

    ArrayList<Integer> controls;

    public TetrisControlsHandler() {
        controls = new ArrayList<Integer>();
    }

    public ArrayList<Integer> getControls() {
        return controls;
    }

    public void setDefault() {
        controls.add(KeyEvent.VK_LEFT);
        controls.add(KeyEvent.VK_RIGHT);
        controls.add(KeyEvent.VK_DOWN);
        controls.add(KeyEvent.VK_UP);
        controls.add(KeyEvent.VK_SPACE);
        controls.add(KeyEvent.VK_P);
        controls.add(KeyEvent.VK_M);
        controls.add(KeyEvent.VK_PAGE_UP);
        controls.add(KeyEvent.VK_PAGE_DOWN);
        controls.add(KeyEvent.VK_CONTROL);
    }

    public void setNewControllers(ArrayList<Integer> newControls) {
        controls = newControls;
    }

}
