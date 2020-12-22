package spypunk.tetris.ui.view;


import java.awt.GraphicsConfiguration;
import javax.swing.*;

public class TetrisHighScoreView extends JFrame{

    HighScoreEntry[] entries;

    public TetrisHighScoreView(){
        super("TetrisHighScoreView");

        Scores highScoreTable = new Scores();
        highScoreTable.updateEntries();
        entries = highScoreTable.entries();
        JList<HighScoreEntry> subList = new JList<>(entries);

        setSize(200, 350);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JPanel panel = new JPanel();
        JLabel subLabel = new JLabel("High Score Table:");
        panel.add(subLabel);
        subList.setVisibleRowCount(10);
        JScrollPane scroller = new JScrollPane(subList);
        panel.add(scroller);
        add(panel);

        setVisible(true);
        setResizable(true);
    }
}
