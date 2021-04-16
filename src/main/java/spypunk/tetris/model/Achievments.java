package spypunk.tetris.model;
import javax.swing.JOptionPane;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Achievments<T> {
    
    private T t;

    public void set(T t) { this.t = t; }
    public T get() { return t; }
    public void rowsScore(int  rowsScore){

        try {
            messagebox();
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println(rowsScore+" rows");
    }
    public void Score(int Score){
        System.out.println(Score+" Score");
        //infoBox("infoMessage", "titleBar");
        
    }

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void createAndShowGui() {
	   
        final JLabel label = new JLabel();
        int timerDelay = 1000;
        new Timer(timerDelay , new ActionListener() {
           int timeLeft = 2;
  
           @Override
           public void actionPerformed(ActionEvent e) {
              if (timeLeft > 0) {
                 label.setText("Closing in " + timeLeft + " seconds");
                 timeLeft--;
              } else {
                 ((Timer)e.getSource()).stop();
                 Window win = SwingUtilities.getWindowAncestor(label);
                 win.setVisible(false);
              }
           }
        }){{setInitialDelay(0);}}.start();
  
        JOptionPane.showMessageDialog(null, label);
  
     }

    public static void messagebox(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               createAndShowGui();
            }
         });
    }


}