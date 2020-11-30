import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultPage extends JFrame implements ActionListener {

    GUI_Util util = new GUI_Util();

    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    JButton ok = new JButton("OK");

    String winnerPic = "src/Pictures/Winner.jpg";
    String loserPic = "src/Pictures/Loser.jpg";
    String equalsPic = "src/Pictures/Equal.jpg";
    JLabel winner = new JLabel(new ImageIcon(winnerPic));
    JLabel loser = new JLabel(new ImageIcon(loserPic));
    JLabel equals = new JLabel(new ImageIcon(equalsPic));

    boolean clicked = false;

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    Player pro;

    public ResultPage(Player p, Player player2) {

        pro = p;

        add(panel);

        panel.setLayout(new BorderLayout());
        util.setMainBackground(panel);
        panel.setBorder(new EmptyBorder(50, 30, 20, 30));

        panel.add(label, BorderLayout.NORTH);
        label.setText(pro.getName() + " fick " + pro.getPoints() + " poäng!");
        util.labelSetFontForegBackg_white(label,0,18,0,51,204);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));

        //    panel.add(winner, BorderLayout.CENTER);

        if(pro.getPoints() > player2.getPoints()){
            panel.add(winner, BorderLayout.CENTER);
        }else if (pro.getPoints() < player2.getPoints()){
            panel.add(loser, BorderLayout.CENTER);
        }else if (pro.getPoints() == player2.getPoints()){
            panel.add(equals, BorderLayout.CENTER);
        }

        panel.add(ok, BorderLayout.SOUTH);
        util.buttonSetFontForegBackg_white(ok,0,16,77,255,77);
        ok.setHorizontalAlignment(SwingConstants.CENTER);

        ok.addActionListener(this);


        setSize(350, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok){
            setClicked(true);
        }
    }

    public boolean findClickAndPlay(){
        ok.addActionListener(this);
        return isClicked();
    }
}

