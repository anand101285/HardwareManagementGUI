import javax.swing.*;
import java.io.IOException;
import  javax.swing.UIManager;
import  javax.swing.UIManager.LookAndFeelInfo;
import  javax.swing.UnsupportedLookAndFeelException;

public class Runner {

    public static void main(String[]  args)
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
        Client c= null;
        try {
            c = new Client();
            MainMenu m =new MainMenu();

//            new AddHardware();




        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        new MainMenuH(c);
    }
}
