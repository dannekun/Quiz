import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ResultPage_waiting extends JFrame {

    GUI_Util util = new GUI_Util();

    JPanel panel = new JPanel();
    JLabel label = new JLabel();

    String wellDonePic = "src/Pictures/Well_done.jpg";
    JLabel wellDone = new JLabel(new ImageIcon(wellDonePic));

    Player pro;
    public ResultPage_waiting(Player p){
        pro = p;
        add(panel);
        panel.setLayout(new BorderLayout());
        util.setMainBackground(panel);
        panel.setBorder(new EmptyBorder(50, 30, 20, 30));

        panel.add(label, BorderLayout.NORTH);
        label.setText(pro.getName() + " fick " + pro.getPoints() + " poäng!");
        util.labelSetFontForegBackg_white(label,0,18,0,51,204);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(wellDone, BorderLayout.CENTER);

        setSize(350, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}

