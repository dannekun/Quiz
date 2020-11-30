import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HejDå extends JFrame {

    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    JButton ok = new JButton("OK");

    String winnerPic = "src/Pictures/Winner.jpg";
    String loserPic = "src/Pictures/Loser.jpg";
    JLabel winner = new JLabel(new ImageIcon(winnerPic));
    JLabel loser = new JLabel(new ImageIcon(loserPic));

    Player pro;
    public HejDå(Player p, Player player2) {

        pro = p;
        add(panel);
        panel.setLayout(new BorderLayout());
        panel.setBackground( new Color(51, 133, 255));
        panel.setBorder(new EmptyBorder(50, 30, 20, 30));
        panel.add(label, BorderLayout.NORTH);
        label.setText("Hej då!");
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(0, 51, 204));
        label.setOpaque(true);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(winner, BorderLayout.CENTER);

        //TODO LÄGG TILL BILD PÅ HEJ DÅ!!!


        panel.add(ok, BorderLayout.SOUTH);
        ok.setHorizontalAlignment(SwingConstants.CENTER);
        ok.setBackground(new Color(77, 255, 77));
        ok.setForeground(Color.WHITE);
        ok.setFont(new Font("Arial", Font.PLAIN, 16));

        ok.setOpaque(true);
        ok.setBorderPainted(false);

        setSize(350, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}

