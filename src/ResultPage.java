import javax.swing.*;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-12
 * Time: 14:39
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ResultPage {

    JPanel panel = new JPanel();



    Player pro = new Player();
    public ResultPage(Player p){

        pro = p;

        JOptionPane.showMessageDialog(null, pro.getName() + " fick " + pro.getPoints() + " poäng!");
    /*    setSize(350, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);*/



        HomePage page = new HomePage(pro);

    }
}

//Det finns fortfarande bugs! (DanneCleanCode)
//Efter man har tryckt det sista svaret ändrar det inte färgen till rött/blått

//TODO: Gå igenom alla frågor och sätta <html> i strängarna
//TODO: Göra klart ResultPage
