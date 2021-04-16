package spypunk.tetris.model;
import javax.swing.JOptionPane;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Achievments<T> {
    
    private T t;
    int rowCount=0;
    public void set(T t) { this.t = t; }
    public T get() { return t; }
    public void rowsScore(int  rowsScore){
        if(Math.abs(rowCount-rowsScore)==1);
        if(Math.abs(rowCount-rowsScore)==2){
            messagebox("New Achievment Unlock: 2 rows");
        }
        if(Math.abs(rowCount-rowsScore)==3){
            messagebox("new AChievment Unlock: 3 rows");

        }


        try {
           
        } catch (Exception e) {
           
        }
        System.out.println(rowsScore+" rows");
    }
    public void Score(int score){
        if(score>100){
             messagebox("New Achievment Unlock: 100 points");
    }
       if(score>200){
        messagebox("New Achievment Unlock: 100 points");
    }
    if(score>500){
        messagebox("New Achievment Unlock: 100 points");
    }
    if(score>1000){
        messagebox("New Achievment Unlock: 100 points");
    }
        System.out.println(score+" Score");
        //infoBox("infoMessage", "titleBar");
        
    }

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void createAndShowGui(String message) {
	   
        final JLabel label = new JLabel();
        int timerDelay = 1000;
        new Timer(timerDelay , new ActionListener() {
           int timeLeft = 2;
  
           @Override
           public void actionPerformed(ActionEvent e) {
              if (timeLeft > 0) {
                 label.setText(message);
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

    public static void messagebox(String message){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               createAndShowGui(message);
            }
         });
    }


}