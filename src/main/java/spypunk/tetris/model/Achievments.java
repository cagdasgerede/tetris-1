package spypunk.tetris.model;
import javax.swing.JOptionPane;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Achievments<T> {
    ArrayList<String> Achievment=new ArrayList<String>();
    final  String outputFile="achievments.txt";
    public Achievments(){
        read(outputFile);

    }
    private T t;
    int rowCount=0;
    public void set(T t) { this.t = t; }
    public T get() { return t; }
    
    public void rowsScore(int  rowsScore){
        if(Math.abs(rowCount-rowsScore)==1);
        if(Math.abs(rowCount-rowsScore)==2){

            if(!haveEverEarned("2 rows")){
                messagebox("New Achievment Unlock: 2 rows");
                write(outputFile, "2 rows");
                Achievment.add("2 rows");
           }
        }
        if(Math.abs(rowCount-rowsScore)==3){

            if(!haveEverEarned("3 rows")){
                messagebox("New Achievment Unlock: 3 rows");
                write(outputFile, "3 rows");
                Achievment.add("3 rows");
           }

        }


        try {
           
        } catch (Exception e) {
           
        }
        System.out.println(rowsScore+" rows");
    }
    public void Score(int score){
        if(score>100){
            if(!haveEverEarned("100 points")){
                 messagebox("New Achievment Unlock: 100 points");
                 write(outputFile, "100 points");
                 Achievment.add("100 points");
            }
    }
       if(score>200){
      
        if(!haveEverEarned("200 points")){
            messagebox("New Achievment Unlock: 200 points");
            write(outputFile, "200 points");
            Achievment.add("200 points");
       }
    }
    if(score>500){
        if(!haveEverEarned("500 points")){
            messagebox("New Achievment Unlock: 500 points");
            write(outputFile, "500 points");
            Achievment.add("500 points");
       }
        
    }
    if(score>1000){
        if(!haveEverEarned("1000 points")){
            messagebox("New Achievment Unlock: 1000 points");
            write(outputFile, "1000 points");
            Achievment.add("1000 points");
       }
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
     public boolean haveEverEarned(String ach){
        
         
        return Achievment.contains(ach);
     }

    public static void messagebox(String message){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               createAndShowGui(message);
            }
         });
    }

    public void read(String filename){

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
               Achievment.add(myReader.nextLine());
            }

            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

    }

    public void write(String outputfile,String achivmentName){
            
        try {

            PrintWriter pw = new PrintWriter(new FileWriter(new File(outputfile),true));	
            pw.println(achivmentName);
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}