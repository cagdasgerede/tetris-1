package spypunk.tetris.model;
 

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class AchievmentsTest {

     Achievments<String> test;

                                           
    public void init(){
        test=new Achievments<String>();
    }
    public void init1(String a){
        test=new Achievments<String>(a);
    }
    @Test
    public void playedAndTotalScore(){
        init();
        test.Achievment.set(0,"90");
        assertEquals("90",test.totalScore);
    }
    @Test
    public void emptyTotalScore(){
        init1("test0.txt");
        assertEquals(0,test.totalScore);
    }
    @Test
    public void testGetFirst(){
        init();
        test.Achievment.set(0,"90");
        assertEquals("90",test.getfirst());
    }
    @Test
    public void testLoadedAchievments(){
        init1("test1.txt");
        assertEquals("90",test.Achievment.get(0));
        assertEquals("100 points",test.Achievment.get(1));
        assertEquals("200 points",test.Achievment.get(2));
        assertEquals("2 rows",test.Achievment.get(3));
        assertEquals("500 points",test.Achievment.get(4));
    }
    @Test
    public void testEmptyAchievments(){
        init1("test0.txt");
        assertEquals("0",test.Achievment.get(0));
    }

    @Test
    public void testScore(){//also tests setempty
        init1("test2.txt");
        test.Score(501);
        assertEquals("100 points",test.Achievment.get(1));
        assertEquals("200 points",test.Achievment.get(2));
        assertEquals("500 points",test.Achievment.get(3));
        test.setEmpty();
    }
    @Test
    public void testRow(){//also tests setempty
        init1("test3.txt");
        test.rowsScore(1);
        test.rowsScore(3);
        assertEquals("2 rows",test.Achievment.get(1));
        test.rowsScore(7);
        assertEquals("3 rows",test.Achievment.get(2));

       test.setEmpty();
    }

}