import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Miwa Guhrés
 * Date: 2020-11-13
 * Time: 10:10
 * Project: Quiz
 * Copyright: MIT
 */
public class Point {

    public static void main(String[] args) {

        List<Integer> player1_TotalPoints = new ArrayList<Integer>();// round1, round 2...
        List<Integer> player2_TotalPoints = new ArrayList<Integer>();

        int pointPerQuizP1 = 0;
        int pointPerQuizP2 = 0;
        boolean round = true;
        //boolean rightAnswerP1=true;
        //boolean rightAnswerP2=false;

        while(round){
                //---test code-----
                Scanner sc = new Scanner(System.in);
                System.out.println("Input Player 1 or 2");
                int player = sc.nextInt();//player1 =1 or player2= 2
                System.out.println("Q1point: Input point 1 or 0");
                int q1 = sc.nextInt();//point 1 or 0
                System.out.println("Q2point: Input point 1 or 0");
                int q2 = sc.nextInt();//point 1 or 0

                if (player==1) {
                    pointPerQuizP1 = q1 + q2;
                    System.out.println("Player1: Point per round " + pointPerQuizP1 + "points");
                    player1_TotalPoints.add(pointPerQuizP1);
                    System.out.println(String.valueOf(player1_TotalPoints));
                    System.out.println("Player1_Total Points from all rounds: " + SumAllRoundsPoints((ArrayList<Integer>) player1_TotalPoints));
                } else if (player==2) {
                    pointPerQuizP2 = q1 + q2;
                    System.out.println("Player2: Point per round " + pointPerQuizP2++ + "points");
                    player2_TotalPoints.add(pointPerQuizP2);
                    System.out.println(String.valueOf(player2_TotalPoints));
                    System.out.println("Player2_Total Points from all rounds: " + SumAllRoundsPoints((ArrayList<Integer>) player2_TotalPoints));

                }
            }
        }


    public static int SumAllRoundsPoints(ArrayList<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            int n = list.get(i);
            sum += n;
        }
        return sum;
    }

}



