import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HomePage_play extends JFrame implements ActionListener {

    GUI_Util util = new GUI_Util();

    JPanel bottomPanel = new JPanel();
    JPanel upperPanel = new JPanel();

    JLabel quizkampen = new JLabel("Quizkampen");
    JLabel player = new JLabel();
    JButton play = new JButton("Spela");
    JLabel home = new JLabel("Hem");

    Player pro = new Player();

    public HomePage_play(Player p){
        pro.setName(p.getName());
        player.setText(pro.getName());

        add(upperPanel);
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        upperPanel.setBackground( new Color(51, 133, 255));
        upperPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPanel.add(Box.createRigidArea(new Dimension(100, 10)));
        upperPanel.add(quizkampen);
        util.labelSetFontForeg(quizkampen, "Comfortaa",1,24,255,255,255);
        quizkampen.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPanel.add(Box.createRigidArea(new Dimension(100, 30)));
        upperPanel.add(player);
        util.labelSetFontForegBackg(player,"Arial",0,18,
                255,255,255, 0,51,204);
        player.setBorder(new EmptyBorder(10, 70, 10, 70));
        player.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPanel.add(Box.createRigidArea(new Dimension(100, 30)));
        upperPanel.add(play);
        Border compoundButton = util.setCompoundBorder(128,255,128,5,80,5,80);
        play.setBorder(compoundButton);
        util.buttonSetFontForegBackg(play, "Arial",0,16,
                255,255,255,77,255,77);
        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPanel.add(Box.createRigidArea(new Dimension(100, 240)));
        add(bottomPanel);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        bottomPanel.add(home);
        util.labelSetFontForegBackg(home,"Arial",0,14,0,0,
                0,255,255,255);
        Border compoundLabel = util.setCompoundBorder(128,191,255,10,10,10,10);
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
        if (e.getSource() == play){
            dispose();
            try {
                GamePage_waiting g = new GamePage_waiting(pro);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
