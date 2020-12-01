import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage_play extends JFrame implements ActionListener {

    GUI_Util g = new GUI_Util();

    JPanel bottomPanel = new JPanel();
    JPanel upperPanel = new JPanel();

    JLabel quizkampen = new JLabel("Quizkampen");
    JLabel player = new JLabel();
    JButton play = new JButton("Spela");
    JLabel home = new JLabel("Hem");

    Player pro = new Player();

    boolean clicked = false;

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }


    public void showWindow(Player p) {

        pro.setName(p.getName());
        player.setText(pro.getName());

        add(upperPanel);
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        upperPanel.setBackground(new Color(51, 133, 255));
        upperPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPanel.add(Box.createRigidArea(new Dimension(100, 10)));
        upperPanel.add(quizkampen);
        g.paintLabelSimply(quizkampen, "Comfortaa",1,24,255,255,255);
    /*    quizkampen.setFont(new Font("Comfortaa", Font.BOLD, 24));
        quizkampen.setForeground(Color.WHITE);*/
        quizkampen.setAlignmentX(Component.CENTER_ALIGNMENT);

        upperPanel.add(Box.createRigidArea(new Dimension(100, 30)));
        upperPanel.add(player);
        g.paintLabel(player,"Arial",0,18,
                255,255,255, 0,51,204);
    /*    player.setFont(new Font("Arial", Font.PLAIN, 18));

        player.setFont(new Font("Arial", Font.PLAIN, 18));
        player.setForeground(Color.WHITE);
        player.setBackground(new Color(0, 51, 204));
        player.setOpaque(true);*/
        player.setBorder(new EmptyBorder(10, 70, 10, 70));
        player.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPanel.add(Box.createRigidArea(new Dimension(100, 30)));
        upperPanel.add(play);
        Border line = new LineBorder(new Color(128, 255, 128));
        Border margin = new EmptyBorder(5, 80, 5, 80);
        Border compound = new CompoundBorder(line, margin);
        play.setBorder(compound);
        play.setBackground(new Color(77, 255, 77));
        play.setForeground(Color.WHITE);
        play.setFont(new Font("Arial", Font.PLAIN, 16));
        play.setContentAreaFilled(false);
        play.setOpaque(true);
        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPanel.add(Box.createRigidArea(new Dimension(100, 240)));
        add(bottomPanel);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        bottomPanel.add(home);
        home.setFont(new Font("Arial", Font.PLAIN, 14));
        home.setForeground(Color.BLACK);
        home.setBackground(Color.WHITE);
        home.setOpaque(true);
        Border lineLabel = new LineBorder(new Color(128, 191, 255));
        Border marginLabel = new EmptyBorder(10, 10, 10, 10);
        Border compoundLabel = new CompoundBorder(lineLabel, marginLabel);
        home.setBorder(compoundLabel);
        Container contentPane = getContentPane();
        contentPane.add(upperPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.PAGE_END);

        setSize(350, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        play.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {

            dispose();
            setClicked(true);

        }

    }

    public boolean findClickPlay() {
        play.addActionListener(this);
        return isClicked();
    }
}
