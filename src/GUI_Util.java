import javax.swing.*;
import java.awt.*;

/**
 * Created by Ivona Zoricic
 * Date: 2020-11-26
 * Time: 15:06
 * Project: Quiz
 * Copywrite: MIT
 */
public class GUI_Util {

    public static final int PLAIN = 0;
    public static final int ITALIC = 0;
    public static final int BOLD = 1;


    public JLabel paintLabel(JLabel label, String fontName, int fontType, int fontSize,
                             int rForeg, int gForeg, int bForeg,
                             int rBackg, int gBackg, int bBackg){
        label.setFont(new Font(fontName, fontType, fontSize));
        label.setForeground(new Color(rForeg, gForeg, bForeg));
        label.setBackground(new Color(rBackg, gBackg, bBackg));
        label.setOpaque(true);
        return label;
    }

    public JLabel paintLabelSimply(JLabel label, String fontName, int fontType, int fontSize,
                                   int rForeg, int gForeg, int bForeg){
        label.setFont(new Font(fontName, fontType, fontSize));
        label.setForeground(new Color(rForeg, gForeg, bForeg));
        return label;
    }
}
