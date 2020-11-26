import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Created by Ivona Zoricic
 * Date: 2020-11-26
 * Time: 11:56
 * Project: Quiz
 * Copywrite: MIT
 */
public class HomePage_waiting extends JFrame {
    JPanel bottomPanel = new JPanel();
    JPanel upperPanel = new JPanel();

    JLabel quizkampen = new JLabel("Quizkampen");
    JLabel player = new JLabel();
    JLabel home = new JLabel("Hem");
    JLabel info = new JLabel("Väntar på spelare nr 2...");

    Player pro = new Player();


    public void showWindow(Player p){
        pro.setName(p.getName());
        player.setText(pro.getName());

        add(upperPanel);
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        upperPanel.setBackground( new Color(51, 133, 255));
        upperPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPanel.add(Box.createRigidArea(new Dimension(100, 10)));
        upperPanel.add(quizkampen);
        quizkampen.setFont(new Font("Comfortaa", Font.BOLD, 24));
        quizkampen.setForeground(Color.WHITE);
        quizkampen.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPanel.add(Box.createRigidArea(new Dimension(100, 30)));
        upperPanel.add(player);
        player.setFont(new Font("Arial", Font.PLAIN, 18));
        player.setForeground(Color.WHITE);
        player.setBackground(new Color(0, 51, 204));
        player.setOpaque(true);
        player.setBorder(new EmptyBorder(10, 70, 10, 70));
        player.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPanel.add(Box.createRigidArea(new Dimension(100, 30)));
        upperPanel.add(Box.createRigidArea(new Dimension(100, 240)));
        add(bottomPanel);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        bottomPanel.add(home);
        home.setFont(new Font("Arial", Font.PLAIN, 14));
        home.setForeground(Color.BLACK);
        home.setBackground(Color.WHITE);
        home.setOpaque(true);
        bottomPanel.add(Box.createRigidArea(new Dimension(40, 20)));
        bottomPanel.add(info);
        info.setFont(new Font("Arial", Font.ITALIC, 14));
        info.setForeground(Color.BLACK);
        info.setOpaque(true);
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
    }

    public void closeWindow(){
        this.dispose();
    }



/*    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play){
            dispose();
            try {
                GamePage_waiting g = new GamePage_waiting(pro);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }*/

}


