import javax.swing.*;
import java.awt.*;

import  javax.swing.UIManager;
import  javax.swing.UIManager.LookAndFeelInfo;
import  javax.swing.UnsupportedLookAndFeelException;
public class RoundButton extends JButton {

    RoundButton()
    {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.setBackground(new Color(0x00111c));
        this.setForeground(new Color(0xffffff));
        this.setFocusPainted(false);
        
    }

}
