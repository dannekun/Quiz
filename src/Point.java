import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by Miwa Guhrés
 * Date: 2020-11-13
 * Time: 10:10
 * Project: Quiz
 * Copyright: MIT
 */
public class Point {

    private List<Integer> pointsPerRound = new ArrayList<Integer>();// point-quiz 1,2,3
    private int numberOfRounds = 3; // I cannot pull the Properties
    private int point;

    private int currentCategory = 0;//0-2(3category)
    private int currentQuiz = 0;//0-2(3 quiz)
    private String[][] answer = {{"m-a", "m-b", "m-c"}, //Math
            {"mo-d", "mo-e", "m-f"},//Movie
            {"g-g", "g-h", "g-i"}};//Game


    public String ShowResult(String input){
        //Properties p = new Properties();
        //String output = null;
        //int numberOfRounds = Integer.parseInt(p.getProperty("numberOfRounds"));
        String output = null;
        for (int i = 0; i < numberOfRounds; i++) {

            if (input.equalsIgnoreCase(answer[currentCategory][currentQuiz])) {
                point = 1;
                //System.out.println(String.valueOf(TotalPoints));
            } else {
                point = 0;
                //System.out.println(String.valueOf(TotalPoints));
            }
            pointsPerRound.add(point);
            // System.out.println("Player1_Total Points from round: " + SumPoints((ArrayList<Integer>) pointsPerRound) +"point");
            currentQuiz++; // quiz 1-3 within the same category
            output = String.valueOf(SumPoints((ArrayList<Integer>) pointsPerRound));
        }
        return output;
    }


    public static int SumPoints (ArrayList < Integer > list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            int n = list.get(i);
            sum += n;
        }
        return sum;
    }
}


