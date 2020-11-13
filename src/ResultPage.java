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
    public ResultPage(Player p, boolean b){
        pro.setName(p.getName());
        if (b == true){
            JOptionPane.showMessageDialog(null,pro.getName() + " vann!");
        }else {
            JOptionPane.showMessageDialog(null, pro.getName() + " förlorade!");
        }

        HomePage page = new HomePage(pro);

    }
}
