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
public class PointTest {

    public static void main(String[] args) {


        List<Integer> pointsPerRound = new ArrayList<Integer>();// point-quiz 1,2,3
        int numberOfRounds = 3;
        int numberOfCategory=3;//Do not need right now
        int point;

        int currentCategory = 0;//0-2(3category)0:Math, 1:Movie, 2:Game
        int currentQuiz = 0;//0-2(3 quiz)
        String[][] answer = {{"m-a", "m-b", "m-c"}, //Math
                {"mo-d", "mo-e", "m-f"},//Movie
                {"g-g", "g-h", "g-i"}};//Game


        // public String ShowResult(){//String input
        //Properties p = new Properties();
        //String output = null;
        //int numberOfRounds = Integer.parseInt(p.getProperty("numberOfRounds"));
        String output = null;
        for (int j=0;j<numberOfCategory; j++) { // Do not need right now Because users chose a category

            for (int i = 0; i < numberOfRounds; i++) {

                Scanner sc = new Scanner(System.in);
                System.out.println("Input answer");
                String input = sc.next();


                if (input.equalsIgnoreCase(answer[currentCategory][currentQuiz])) {
                    point = 1;
                    System.out.println("Rätt!" + " Du har fått " + point + " poäng");
                    //System.out.println(String.valueOf(TotalPoints));
                } else {
                    System.out.println("Fel...");
                    point = 0;
                    System.out.println("Rätt!" + " Du har fått " + point + " poäng");
                    //System.out.println(String.valueOf(TotalPoints));
                }
                pointsPerRound.add(point);
                System.out.println("Player1_Total Points: " + SumPoints((ArrayList<Integer>) pointsPerRound) + "point");
                currentQuiz++; // quiz 1-3 within the same category
                currentQuiz=currentQuiz++%3;//0,1,2,0,1,2....
                System.out.println("currentQuiz index: "+currentQuiz);
                output = String.valueOf(SumPoints((ArrayList<Integer>) pointsPerRound));
            }
            currentCategory++;
            System.out.println("currentCategory index: "+currentCategory);
        }
        //     return output;
        // }

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
