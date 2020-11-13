import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Ivona Zoricic
 * Date: 2020-11-13
 * Time: 21:02
 * Project: Quiz
 * Copywrite: MIT
 */
public class Settings extends JFrame {

    JLabel rounds = new JLabel("How many rounds?");
    JLabel questions = new JLabel("How many questions per round?");
    JTextField answerRounds = new JTextField();
    JTextField answerQuestions = new JTextField(10);
    JButton submit = new JButton("Submit");
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    Properties properties = new Properties();

    Settings() {
        setTitle("Settings");

        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(submit, BorderLayout.SOUTH);
        leftPanel.setLayout(new GridLayout(2, 1));
        leftPanel.add(rounds);
        leftPanel.add(questions);
        rightPanel.setLayout(new GridLayout(2, 1));
        rightPanel.add(answerRounds);
        rightPanel.add(answerQuestions);

        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String inputRounds = answerRounds.getText();
                String inputQuestions = answerQuestions.getText();
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream("src/RoundQuestions.properties");
                    properties.setProperty("numberOfRounds", inputRounds);
                    properties.setProperty("numberOfQuestions", inputQuestions);

                    properties.store(fos, "Properties generated for change numbers of rounds and questions");

                    fos.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                dispose();
            }
        });

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
