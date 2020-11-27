import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Ivona Zoricic
 * Date: 2020-11-26
 * Time: 15:06
 * Project: Quiz
 * Copywrite: MIT
 */
public class GUI_Util {

    public static final Color mainPlayerColor = new Color(0, 51, 204);

    public void setMainBackground(JPanel panel){
        panel.setBackground( new Color(51, 133, 255));
    }

    public JLabel labelSetFontForegBackg(JLabel label, String fontName, int fontType, int fontSize,
                                         int rForeg, int gForeg, int bForeg,
                                         int rBackg, int gBackg, int bBackg){
        label.setFont(new Font(fontName, fontType, fontSize));
        label.setForeground(new Color(rForeg, gForeg, bForeg));
        label.setBackground(new Color(rBackg, gBackg, bBackg));
        label.setOpaque(true);
        return label;
    }

    public JLabel labelSetFontForeg(JLabel label, String fontName, int fontType, int fontSize,
                                    int rForeg, int gForeg, int bForeg){
        label.setFont(new Font(fontName, fontType, fontSize));
        label.setForeground(new Color(rForeg, gForeg, bForeg));
        return label;
    }

    public JButton buttonSetFontForegBackg(JButton button, String fontName, int fontType, int fontSize,
                                           int rForeg, int gForeg, int bForeg,
                                           int rBackg, int gBackg, int bBackg){
        button.setFont(new Font(fontName, fontType, fontSize));
        button.setForeground(new Color(rForeg, gForeg, bForeg));
        button.setBackground(new Color(rBackg, gBackg, bBackg));
        button.setOpaque(true);
        return button;
    }

    public JButton buttonSetFontForeg(JButton button, String fontName, int fontType, int fontSize,
                                           int rForeg, int gForeg, int bForeg){
        button.setFont(new Font(fontName, fontType, fontSize));
        button.setForeground(new Color(rForeg, gForeg, bForeg));
        button.setOpaque(true);
        return button;
    }


    public Border setCompoundBorder(int r, int g, int b, int top, int left, int bottom, int right){
        Border line = new LineBorder(new Color(r,g,b));
        Border margin = new EmptyBorder(top,left,bottom,right);
        Border compound = new CompoundBorder(line, margin);
        return compound;
    }

    public JButton setSizeButton(JButton button, int wPre, int hPre, int wMax, int hMax){
        button.setPreferredSize(new Dimension(wPre, hPre));
        button.setMaximumSize(new Dimension(wMax, hMax));
        return button;
    }

}
