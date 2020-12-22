package spypunk.tetris.ui.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

public class Scores{
    public static final int maxEntries = 10;
    private int numEntries;

    private HighScoreEntry[] entries;

    private final String fileName = "scoreList.txt";

    public Scores(){
        entries = new HighScoreEntry[maxEntries];
        numEntries = 0;
    }

    public void add(HighScoreEntry e){
        int newScore = e.getScore();
        if(numEntries == maxEntries){
            if(newScore <= entries[numEntries-1].getScore())
                return;
        }else{
            numEntries++;
        }

        //last element of the array that is not empty
        int i = numEntries-1;
        //this loop shifts the array
        if(i>0){
            if (entries[i-1] != null) {
                for (; (i >= 1) && (newScore > entries[i - 1].getScore()); i--)
                    entries[i] = entries[i - 1];
            }
        }

        entries[i] = e;
        updateFile();
    }

    public void updateFile(){
        try {
            FileWriter myWriter = new FileWriter(fileName);
            for (int i = 0; i < entries.length; i++) {
                if (entries[i] != null) {
                    myWriter.write(entries[i].getName() + "-" + entries[i].getScore() + "\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void updateEntries(){
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String playerName = data.substring(0, data.indexOf('-'));
                int playerScore = Integer.parseInt(data.substring(data.indexOf('-')+1));

                HighScoreEntry newPlayer = new HighScoreEntry(playerName, playerScore);
                add(newPlayer);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public HighScoreEntry highestScore(){
        return entries[0];
    }

    public HighScoreEntry lowestScore(){
        return entries[9];
    }

    public HighScoreEntry[] entries(){
        return this.entries;
    }
}