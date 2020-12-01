import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HejDå extends JFrame {

    GUI_Util util = new GUI_Util();

    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    JButton ok = new JButton("OK");

    String byePic = "src/Pictures/Bye.jpg";
    JLabel bye = new JLabel(new ImageIcon(byePic));

    Player pro;
    public HejDå(Player p, Player player2) {

        pro = p;
        add(panel);
        panel.setLayout(new BorderLayout());
        util.setMainBackground(panel);
        panel.setBorder(new EmptyBorder(50, 30, 20, 30));

        panel.add(label, BorderLayout.NORTH);
        label.setText("Hej då!");
        util.labelSetFontForegBackg_white(label,0,18,0,51,204);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));

        panel.add(bye, BorderLayout.CENTER);

        panel.add(ok, BorderLayout.SOUTH);
        ok.setHorizontalAlignment(SwingConstants.CENTER);
        util.buttonSetFontForegBackg_white(ok,0,16,77,255,77);

        setSize(350, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}

