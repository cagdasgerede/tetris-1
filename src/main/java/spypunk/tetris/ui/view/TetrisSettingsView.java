package spypunk.tetris.ui.view;

import javax.swing.*;

import spypunk.tetris.guice.TetrisModule.TetrisProvider;
import spypunk.tetris.model.Tetris;
import spypunk.tetris.model.Tetris.State;
import spypunk.tetris.service.TetrisService;
import spypunk.tetris.sound.service.SoundService;
import spypunk.tetris.ui.controller.input.TetrisControllerInputHandler;
import spypunk.tetris.ui.font.cache.FontCache;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spypunk.tetris.ui.util.SwingUtils;

public class TetrisSettingsView extends JFrame {

    private static final Logger LOGGER = LoggerFactory.getLogger(TetrisSettingsView.class);

    private static final String USER_HOME = System.getProperty("user.home");

    private static final String ERROR_TITLE = "Error";

    private static final String ERROR_MESSAGE_TEMPLATE = "Controllers cannot be the same, check the log file %s%s.spypunk-tetris%stetris.log for more information";

    private static final String ERROR_MESSAGE = String.format(ERROR_MESSAGE_TEMPLATE, USER_HOME, File.separator,
        File.separator);
    private final TetrisControllerInputHandler tetrisControllerInputHandler;
    private final TetrisService tetrisService;
    private final SoundService soundService;
    private final Tetris tetris;
    private final TetrisMainView tetrisMainView;
    private final String SETTINGS = "SETTINGS";
    ArrayList<Integer> controls;
    ArrayList<Integer> controllers;
    JTextField tleft, tright, tdown, trotate, tnewgame, tpause, tmute, tincvolume, tdecvolume, tharddrop;
    ArrayList<JTextField> textFields;

    TetrisSettingsView(FontCache fontCache, final TetrisControllerInputHandler tetrisControllerInputHandler,
            final TetrisService tetrisServic, final SoundService soundServic, @TetrisProvider final Tetris tetri,
            final TetrisMainView tetrisMainVie) {
        this.soundService = soundServic;
        this.tetris = tetri;
        this.tetrisMainView = tetrisMainVie;
        this.tetrisControllerInputHandler = tetrisControllerInputHandler;
        this.tetrisService = tetrisServic;

        pauseGame();
        TetrisSettingsView tetrisSettingsView = this;
        textFields = new ArrayList<JTextField>();
        controls = new ArrayList<Integer>();
        controllers = new ArrayList<Integer>();
        controls = tetrisControllerInputHandler.getControls();
        for (int i = 0; i < controls.size(); i++) {
            controllers.add(controls.get(i));
        }

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int width = this.getSize().width;
        int height = this.getSize().height;
        int x = (dim.width - width) / 2;
        int y = (dim.height - height) / 3;
        this.setLocation(x, y);
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(10, 2));
        FlowLayout layout = new FlowLayout();
        FlowLayout layoutText = new FlowLayout();
        JPanel p3 = new JPanel();
        p3.setLayout(layoutText);
        JPanel p2 = new JPanel();
        p2.setLayout(layout);
        p1.setBackground(Color.BLACK);
        p2.setBackground(Color.BLACK);
        p3.setBackground(Color.BLACK);
        JLabel left, right, down, rotate, newgame, pause, mute, incvolume, decvolume, harddrop;
        JButton buttonSave, buttonMute;
        left = new JLabel("LEFT");
        left.setFont(fontCache.getDefaultFont());
        left.setForeground(Color.WHITE);
        tleft = new JTextField(KeyEvent.getKeyText(controls.get(0)));
        tleft.setFont(fontCache.getDefaultFont());
        tleft.setForeground(Color.RED);
        tleft.setBackground(Color.black);
        tleft.setCaretColor(Color.WHITE);
        tleft.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (tleft.getText().length() >= 1
                        && !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    evt.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                textSetter(e, tleft);
            }
        });

        right = new JLabel("RIGHT");
        right.setFont(fontCache.getDefaultFont());
        right.setForeground(Color.WHITE);
        tright = new JTextField(KeyEvent.getKeyText(controls.get(1)));
        tright.setFont(fontCache.getDefaultFont());
        tright.setForeground(Color.RED);
        tright.setBackground(Color.black);
        tright.setCaretColor(Color.WHITE);
        tright.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (tright.getText().length() >= 1
                        && !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    evt.consume();
                }
            }

            public void keyReleased(KeyEvent e) {
                textSetter(e, tright);
            }
        });

        down = new JLabel("DOWN");
        down.setFont(fontCache.getDefaultFont());
        down.setForeground(Color.WHITE);
        tdown = new JTextField(KeyEvent.getKeyText(controls.get(2)));
        tdown.setFont(fontCache.getDefaultFont());
        tdown.setForeground(Color.RED);
        tdown.setBackground(Color.black);
        tdown.setCaretColor(Color.WHITE);
        tdown.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (tdown.getText().length() >= 1
                        && !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    evt.consume();
                }
            }

            public void keyReleased(KeyEvent e) {
                textSetter(e, tdown);
            }
        });

        rotate = new JLabel("ROTATE");
        rotate.setFont(fontCache.getDefaultFont());
        rotate.setForeground(Color.WHITE);
        trotate = new JTextField(KeyEvent.getKeyText(controls.get(3)));
        trotate.setFont(fontCache.getDefaultFont());
        trotate.setForeground(Color.RED);
        trotate.setBackground(Color.black);
        trotate.setCaretColor(Color.WHITE);
        trotate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (trotate.getText().length() >= 1
                        && !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    evt.consume();
                }
            }

            public void keyReleased(KeyEvent e) {
                textSetter(e, trotate);
            }
        });

        newgame = new JLabel("NEW GAME");
        newgame.setFont(fontCache.getDefaultFont());
        newgame.setForeground(Color.WHITE);
        tnewgame = new JTextField(KeyEvent.getKeyText(controls.get(4)));
        tnewgame.setFont(fontCache.getDefaultFont());
        tnewgame.setForeground(Color.RED);
        tnewgame.setBackground(Color.black);
        tnewgame.setCaretColor(Color.WHITE);
        tnewgame.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (tnewgame.getText().length() >= 1
                        && !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    evt.consume();
                }
            }

            public void keyReleased(KeyEvent e) {
                textSetter(e, tnewgame);
            }
        });

        pause = new JLabel("PAUSE");
        pause.setFont(fontCache.getDefaultFont());
        pause.setForeground(Color.WHITE);
        tpause = new JTextField(KeyEvent.getKeyText(controls.get(5)));
        tpause.setFont(fontCache.getDefaultFont());
        tpause.setForeground(Color.RED);
        tpause.setBackground(Color.black);
        tpause.setCaretColor(Color.WHITE);
        tpause.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (tpause.getText().length() >= 1
                        && !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    evt.consume();
                }
            }

            public void keyReleased(KeyEvent e) {
                textSetter(e, tpause);
            }
        });

        mute = new JLabel("MUTE");
        mute.setFont(fontCache.getDefaultFont());
        mute.setForeground(Color.WHITE);
        tmute = new JTextField(KeyEvent.getKeyText(controls.get(6)));
        tmute.setFont(fontCache.getDefaultFont());
        tmute.setForeground(Color.RED);
        tmute.setBackground(Color.black);
        tmute.setCaretColor(Color.WHITE);
        tmute.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (tmute.getText().length() >= 1
                        && !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    evt.consume();
                }
            }

            public void keyReleased(KeyEvent e) {
                textSetter(e, tmute);
            }
        });

        incvolume = new JLabel("INC. VOL");
        incvolume.setFont(fontCache.getDefaultFont());
        incvolume.setForeground(Color.WHITE);
        tincvolume = new JTextField(KeyEvent.getKeyText(controls.get(7)));
        tincvolume.setFont(fontCache.getDefaultFont());
        tincvolume.setForeground(Color.RED);
        tincvolume.setBackground(Color.black);
        tincvolume.setCaretColor(Color.WHITE);
        tincvolume.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (tincvolume.getText().length() >= 1
                        && !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    evt.consume();
                }
            }

            public void keyReleased(KeyEvent e) {
                textSetter(e, tincvolume);
            }
        });

        decvolume = new JLabel("DEC. VOL");
        decvolume.setFont(fontCache.getDefaultFont());
        decvolume.setForeground(Color.WHITE);
        tdecvolume = new JTextField(KeyEvent.getKeyText(controls.get(8)));
        tdecvolume.setFont(fontCache.getDefaultFont());
        tdecvolume.setForeground(Color.RED);
        tdecvolume.setBackground(Color.black);
        tdecvolume.setCaretColor(Color.WHITE);
        tdecvolume.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (tdecvolume.getText().length() >= 1
                        && !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    evt.consume();
                }
            }

            public void keyReleased(KeyEvent e) {
                textSetter(e, tdecvolume);
            }
        });

        harddrop = new JLabel("HARD DROP");
        harddrop.setFont(fontCache.getDefaultFont());
        harddrop.setForeground(Color.WHITE);
        tharddrop = new JTextField(KeyEvent.getKeyText(controls.get(9)));
        tharddrop.setFont(fontCache.getDefaultFont());
        tharddrop.setForeground(Color.RED);
        tharddrop.setBackground(Color.black);
        tharddrop.setCaretColor(Color.WHITE);
        tharddrop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                if (tharddrop.getText().length() >= 1
                        && !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    getToolkit().beep();
                    evt.consume();
                }
            }

            public void keyReleased(KeyEvent e) {
                textSetter(e, tharddrop);
            }
        });

        buttonSave = new JButton("SAVE");
        buttonSave.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent aEv) {
                setControls();
                if (checkControllersHaveDuplicate(controllers)) {
                    tetrisControllerInputHandler.changeControls(controllers);
                    pauseGame();
                    tetrisSettingsView.dispose();
                } else {
                    try {
                        SwingUtils.doInAWTThread(TetrisSettingsView::showErrorDialog);
                        throw new Exception("Same controllers");
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });

        buttonMute = new JButton("MUTE");

        buttonMute.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent aEv) {
                tetrisService.mute();

                final boolean muted = tetris.isMuted();

                tetrisMainView.setMuted(muted);
                soundService.setMuted(muted);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });

        JLabel label = new JLabel(SETTINGS);
        label.setFont(fontCache.getDefaultFont());
        label.setForeground(Color.WHITE);

        p1.add(left);
        p1.add(tleft);
        p1.add(right);
        p1.add(tright);
        p1.add(down);
        p1.add(tdown);
        p1.add(rotate);
        p1.add(trotate);
        p1.add(newgame);
        p1.add(tnewgame);
        p1.add(pause);
        p1.add(tpause);
        p1.add(mute);
        p1.add(tmute);
        p1.add(incvolume);
        p1.add(tincvolume);
        p1.add(decvolume);
        p1.add(tdecvolume);
        p1.add(harddrop);
        p1.add(tharddrop);
        p2.add(buttonSave);
        p2.add(buttonMute);
        p3.add(label);

        add(p3, "North");
        add(p1, "Center");
        add(p2, "South");
        this.setVisible(true);
        this.setSize(400, 400);

    }

    int decideTheKey(String str) {

        if (str.length() == 1) {
            if (Character.isLetter(str.charAt(0))) {
                KeyStroke ks = KeyStroke.getKeyStroke(Character.toUpperCase(str.charAt(0)), 0);
                return ks.getKeyCode();
            } else {
                KeyStroke ks = KeyStroke.getKeyStroke(str.charAt(0), 0);
                return ks.getKeyCode();
            }

        } else {

            switch (str.toLowerCase()) {
                case ("left"):
                    return KeyEvent.VK_LEFT;
                case ("right"):
                    return KeyEvent.VK_RIGHT;
                case ("down"):
                    return KeyEvent.VK_DOWN;
                case ("up"):
                    return KeyEvent.VK_UP;
                case ("space"):
                    return KeyEvent.VK_SPACE;
                case ("page up"):
                    return KeyEvent.VK_PAGE_UP;
                case ("page down"):
                    return KeyEvent.VK_PAGE_DOWN;
                case ("insert"):
                    return KeyEvent.VK_INSERT;
                case ("f1"):
                    return KeyEvent.VK_F1;
                case ("f2"):
                    return KeyEvent.VK_F2;
                case ("f3"):
                    return KeyEvent.VK_F3;
                case ("f4"):
                    return KeyEvent.VK_F4;
                case ("f5"):
                    return KeyEvent.VK_F5;
                case ("f6"):
                    return KeyEvent.VK_F6;
                case ("f7"):
                    return KeyEvent.VK_F7;
                case ("f8"):
                    return KeyEvent.VK_F8;
                case ("f9"):
                    return KeyEvent.VK_F9;
                case ("f10"):
                    return KeyEvent.VK_F10;
                case ("ctrl"):
                    return KeyEvent.VK_CONTROL;
                case ("shift"):
                    return KeyEvent.VK_SHIFT;
                case ("esc"):
                    return KeyEvent.VK_ESCAPE;
            }
        }

        return 0;
    }

    void setControls() {

        controllers.set(0, decideTheKey(tleft.getText()));
        controllers.set(1, decideTheKey(tright.getText()));
        controllers.set(2, decideTheKey(tdown.getText()));
        controllers.set(3, decideTheKey(trotate.getText()));
        controllers.set(4, decideTheKey(tnewgame.getText()));
        controllers.set(5, decideTheKey(tpause.getText()));
        controllers.set(6, decideTheKey(tmute.getText()));
        controllers.set(7, decideTheKey(tincvolume.getText()));
        controllers.set(8, decideTheKey(tdecvolume.getText()));
        controllers.set(9, decideTheKey(tharddrop.getText()));
    }

    void textSetter(KeyEvent e, JTextField textField) {

        switch (e.getKeyCode()) {
            case (37):
                textField.setText("left");
                break;
            case (39):
                textField.setText("right");
                break;
            case (40):
                textField.setText("down");
                break;
            case (38):
                textField.setText("up");
                break;
            case (32):
                textField.setText("space");
                break;
            case (33):
                textField.setText("page up");
                break;
            case (34):
                textField.setText("page down");
                break;
            case (155):
                textField.setText("insert");
                break;
            case (112):
                textField.setText("f1");
                break;
            case (113):
                textField.setText("f2");
                break;
            case (114):
                textField.setText("f3");
                break;
            case (115):
                textField.setText("f4");
                break;
            case (116):
                textField.setText("f5");
                break;
            case (117):
                textField.setText("f6");
                break;
            case (118):
                textField.setText("f7");
                break;
            case (119):
                textField.setText("f8");
                break;
            case (120):
                textField.setText("f9");
                break;
            case (121):
                textField.setText("f10");
                break;
            case (17):
                textField.setText("ctrl");
                break;
            case (16):
                textField.setText("shift");
                break;
            case (27):
                textField.setText("esc");
                break;
        }

    }

    void pauseGame() {
        tetrisService.pause();

        final State state = tetris.getState();

        if (State.PAUSED.equals(state)) {
            soundService.pauseMusic();
        } else if (State.RUNNING.equals(state)) {
            soundService.resumeMusic();
        }
    }

    
    public boolean checkControllersHaveDuplicate(ArrayList<Integer> controls) {

        HashSet<Integer> hashSet = new HashSet<Integer>(controls);
        if(hashSet.size() < controls.size())
            return false;

        return true;

    }

    private static void showErrorDialog() {
        JOptionPane.showMessageDialog(null,
            ERROR_MESSAGE,
            ERROR_TITLE,
            JOptionPane.ERROR_MESSAGE);
    }

}
