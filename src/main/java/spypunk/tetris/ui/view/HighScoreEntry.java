package spypunk.tetris.ui.view;

public class HighScoreEntry{
    private String name;
    private int score;
    public HighScoreEntry(String name, int score){
        this.name = name;
        this.score = score;
    }

    public void setName(String name){ this.name = name;}
    public void setScore(int score){ this.score = score;}

    public String getName(){ return name;}
    public int getScore(){ return score;}

    @Override
    public String toString() { return name + "-" + score + "\n";}
}