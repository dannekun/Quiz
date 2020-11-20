import javax.swing.*;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-12
 * Time: 14:39
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ResultPage {

    //RETURN TO HOMEPAGE

    //VI BEHÖVER EN KONSTRUKTOR MED RESULTAT PLAYERS OSV FÖR HOMEPAGE


    Player pro = new Player();
    public ResultPage(Player p){

        pro = p;

        JOptionPane.showMessageDialog(null, pro.getName() + " fick " + pro.getPoints() + " poäng!");


        HomePage page = new HomePage(pro);

    }
}
