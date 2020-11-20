package QuestionsHandler.Categories;

import QuestionsHandler.Answers;
import QuestionsHandler.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Salah Abdinoor
 * 11/14/2020
 * 4:34 PM
 * Quiz2
 * Copyright: MIT
 */
public class Math {

    //--------------------------------------------------------------------------------------//
    // Math Answers
    private final List<Questions> mathList;

    private final Answers A1 = new Answers("55", "65", "43", "53");

    private final Answers A2 = new Answers("476 > 467", "456 > 465", "403 > 430", "457 > 475");

    private final Answers A3 = new Answers("4531", "4432", "4531", "5431");

    private final Answers A4 = new Answers("Tre hundra fyrtiotvå", "Trehundra fyra två", "Tre fyrtiotvå", "Tre fyra två");

    private final Answers A5 = new Answers("261", "221", "241", "251");

    private final Answers A6 = new Answers("5,68", "6,53", "5,58", "6,68");

    private final Answers A7 = new Answers("w=-50", "w=2", "w=-2", "w=50");

    private final Answers A8 = new Answers("x=4", "x=6", "x=3", "x=1");

    private final Answers A9 = new Answers("x=6", "X=7", "x=1/6", "x=9");

    private final Answers A10 = new Answers("9900", "9990", "990", "1001");

    //--------------------------------------------------------------------------------------//
    // Math Questions
    private final Questions Q1 = new Questions("Summera 1 till 10", A1);

    private final Questions Q2 = new Questions("Vilka av dessa påstående är korrekta?", A2);

    private final Questions Q3 = new Questions("Vilket nummer kommer nästa i serien? 4231, 4331, 4431,?", A3);

    private final Questions Q4 = new Questions("Du hade 342 mynt i sin samling. Hur skulle du skriva 342?", A4);

    private final Questions Q5 = new Questions("Om du hade 785 hundar hur många skulle du ha om jag tog 524?", A5);

    private final Questions Q6 = new Questions("Om Johanna hade 19,27kr och Max hade 13,59kr hur mycket mer pengar har Johanna?", A6);

    private final Questions Q7 = new Questions("w/5 = -10", A7);

    private final Questions Q8 = new Questions("14+3x=26", A8);

    private final Questions Q9 = new Questions("9/36=x/24", A9);

    private final Questions Q10 = new Questions("10001 – 101", A10);


    public Math(){

        mathList = new ArrayList<>();

        mathList.add(Q1);
        mathList.add(Q2);
        mathList.add(Q3);
        mathList.add(Q4);
        mathList.add(Q5);
        mathList.add(Q6);
        mathList.add(Q7);
        mathList.add(Q8);
        mathList.add(Q9);
        mathList.add(Q10);

        Collections.shuffle(mathList);

    }

    public String getCategoryName() {
        return "Math";
    }

    public List<Questions> getMathList() {
        return mathList;
    }

}
