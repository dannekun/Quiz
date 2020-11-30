import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultPage extends JFrame implements ActionListener {

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
        panel.setBackground( new Color(51, 133, 255));
        panel.setBorder(new EmptyBorder(50, 30, 20, 30));
        panel.add(label, BorderLayout.NORTH);
        label.setText(pro.getName() + " fick " + pro.getPoints() + " poäng!");
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(0, 51, 204));
        label.setOpaque(true);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(winner, BorderLayout.CENTER);

        if(pro.getPoints() > player2.getPoints()){
            panel.add(winner);
        }else if (pro.getPoints() < player2.getPoints()){
            panel.add(loser);
        }else if (pro.getPoints() == player2.getPoints()){
            panel.add(equals);
        }

        panel.add(ok, BorderLayout.SOUTH);

        ok.setHorizontalAlignment(SwingConstants.CENTER);
        ok.setBackground(new Color(77, 255, 77));
        ok.setForeground(Color.WHITE);
        ok.setFont(new Font("Arial", Font.PLAIN, 16));
        ok.setOpaque(true);
        ok.setBorderPainted(false);
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

