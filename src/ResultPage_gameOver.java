import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Ivona Zoricic
 * Date: 2020-11-29
 * Time: 21:02
 * Project: Quiz
 * Copywrite: MIT
 */
public class ResultPage_gameOver extends JFrame {
    GUI_Util util = new GUI_Util();

    JPanel panel = new JPanel();
    JLabel result_1 = new JLabel();
    JLabel result_2 = new JLabel();
    JButton ok = new JButton("OK");

    String gameOverPic = "src/Pictures/Game_Over.png";
    JLabel gameOver = new JLabel(new ImageIcon(gameOverPic));

    Player pro;
    Player pro2;
    public ResultPage_gameOver(Player p){
        pro = p;
        add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        util.setMainBackground(panel);
        panel.setBorder(new EmptyBorder(50, 30, 20, 30));

        panel.add(result_1);
        result_1.setText(pro.getName() + " fick " + pro.getPoints() + " poäng");
        util.labelSetFontForegBackg_white(result_1,0,18,0,51,204);
        result_1.setHorizontalAlignment(SwingConstants.CENTER);
        result_1.setBorder(new EmptyBorder(10, 10, 10, 10));
        util.setSizeLabel(result_1,200,30,200,30);
        panel.add(Box.createRigidArea(new Dimension(200, 20)));

        panel.add(result_2);
        result_2.setText("Spelare 2 fick " + pro.getPoints() + " poäng");
        util.labelSetFontForegBackg_white(result_2,0,18,255, 26, 26);
        result_2.setHorizontalAlignment(SwingConstants.CENTER);
        result_2.setBorder(new EmptyBorder(10, 10, 10, 10));
        util.setSizeLabel(result_2,200,30,200,30);
        panel.add(Box.createRigidArea(new Dimension(200, 20)));

        panel.add(gameOver);
        util.setSizeLabel(gameOver,200,200,200,200);
        panel.add(Box.createRigidArea(new Dimension(200, 60)));



        panel.add(ok, BorderLayout.SOUTH);
        ok.setHorizontalAlignment(SwingConstants.CENTER);
        util.buttonSetFontForegBackg_white(ok,0,16,77,255,77);
        util.setSizeButton(ok,100,30,100,30);

        ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                HomePage_play page = new HomePage_play(pro);
            }
        });

        util.alignComponentsCenter(result_1, result_2,gameOver,ok);

        setSize(350, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
