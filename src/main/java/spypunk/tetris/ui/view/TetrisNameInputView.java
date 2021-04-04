package spypunk.tetris.ui.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Font;

import static spypunk.tetris.ui.constants.TetrisUIConstants.DEFAULT_FONT_COLOR;
import spypunk.tetris.ui.font.cache.FontCacheImpl;

public class TetrisNameInputView{

    private final JFrame frame;
    private final Container contentPane;
    private final JPanel firstPanel;
    private final JButton submitButton;
    private final JTextField textField;
    private final JLabel label;

    private final FontCacheImpl fontCache;
    private final Font font;

    public TetrisNameInputView(){

        frame=new JFrame();
        contentPane=frame.getContentPane();
        contentPane.setBackground(Color.BLACK);
        frame.setPreferredSize(new Dimension(350, 100));
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        firstPanel=new JPanel();
        textField = new JTextField(10);
        submitButton = new JButton("SUBMIT");
        label=new JLabel();

        fontCache=new FontCacheImpl();
        font=fontCache.getDefaultFont();
    }

    public void show(){
        print();
        frame.setVisible(true);
    }

    public void print(){

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textField.getText());
            }
        });

        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(DEFAULT_FONT_COLOR);
        submitButton.setFont(font);

        textField.setBackground(Color.BLACK);
        textField.setForeground(DEFAULT_FONT_COLOR);
        textField.setFont(font);

        label.setForeground(DEFAULT_FONT_COLOR);
        label.setFont(font);
        label.setText("PLEASE ENTER\nYOUR NAME");
        firstPanel.setBackground(Color.BLACK);
        firstPanel.add(textField);
        firstPanel.add(submitButton);
        
        frame.add(firstPanel,BorderLayout.CENTER);
        frame.add(label,BorderLayout.NORTH);
    }
   
}
