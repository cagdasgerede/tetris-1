
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import spypunk.tetris.ui.view.TetrisSettingsView;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

@Testable
public class SettingsTest {

    @Test
    void testIsControllersHaveDuplicate() {
        TetrisSettingsView ts = mock(TetrisSettingsView.class);

        ArrayList<Integer> controllers = new ArrayList<Integer>();
        controllers.add(32);
        controllers.add(33);
        controllers.add(65);
        controllers.add(33);
        controllers.add(17);
        controllers.add(34);
        controllers.add(83);
        controllers.add(68);
        controllers.add(87);
        controllers.add(77);

        assertFalse(ts.checkControllersHaveDuplicate(controllers));

    }

    @Test
    void testWhenControllersEmpty() {
        TetrisSettingsView ts = mock(TetrisSettingsView.class);

        ArrayList<Integer> controllers = new ArrayList<Integer>();
        controllers.add(32);
        controllers.add(33);
        controllers.add(65);
        controllers.add(33);
        controllers.add(17);
        controllers.add(34);
        controllers.add(83);
        controllers.add(68);
        controllers.add(87);

        assertFalse(ts.checkControllersHaveDuplicate(controllers));
    }

}
