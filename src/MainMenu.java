import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenu extends JFrame implements ActionListener {

    JFrame frame=new JFrame();

    JButton Hardware;
    JButton Manufacturer;
    JButton Services;
    JButton Exit;

    MainMenu()
    {


        Hardware= new RoundButton();
        Hardware.setBounds(250,120,500,50);
        Hardware.setText("Manage Hardware Parts");
        Hardware.setFont(new Font("Segoe Print",Font.BOLD,30));
        Hardware.addActionListener(this);


        Manufacturer= new RoundButton();
        Manufacturer.setBounds(250,180,500,50);
        Manufacturer.setText("Manage Manufacturers");
        Manufacturer.setFont(new Font("Segoe Print",Font.BOLD,30));
        Manufacturer.addActionListener(this);


        Services= new RoundButton();
        Services.setBounds(250,240,500,50);
        Services.setText("Manage Services");
        Services.setFont(new Font("Segoe Print",Font.BOLD,30));
        
        
        Services.addActionListener(this);

        JLabel label=new JLabel();
        
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        Image img_logo = new ImageIcon("C:\\Users\\anand\\IdeaProjects\\Hardware Management System Final Draft\\img\\logo4.png").getImage();
        label.setIcon(new ImageIcon(img_logo));

        JPanel top=new JPanel();
        top.setBounds(0,0,1000,100);
        top.setBackground(new Color(0x09181D));
        top.add(label);

        System.out.println(this.getClass());
        Image img = new ImageIcon("C:\\Users\\anand\\IdeaProjects\\Hardware Management System Final Draft\\img\\frame3.png").getImage();
        JLabel picture = new JLabel("");
        picture.setBounds(0,0,1000,500);
        picture.setIcon(new ImageIcon(img));
        JPanel optionspanel=new JPanel();
        optionspanel.setBounds(0,100,1000,500);
        optionspanel.setBackground(new Color(0x142328));
        optionspanel.setLayout(null);
        optionspanel.add(Hardware);
        optionspanel.add(Manufacturer);
        optionspanel.add(Services);
        optionspanel.add(picture);


        frame.setVisible(true);
        frame.setTitle(this.getClass().getSimpleName());
        frame.setSize(1000,600);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0x2d3a4a));
        frame.add(top);
        frame.add(optionspanel);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource()==Hardware)
            {
                Client.choose_communication_type(1);
                frame.dispose();
                Client.communication(1,null,-1);

            }
            else if(e.getSource()==Manufacturer)
            {
                Client.choose_communication_type(2);
                frame.dispose();
                Client.communication(1,null,-1);

            }
            else if(e.getSource()==Services)
            {
                Client.choose_communication_type(3);
                frame.dispose();
                Client.services(6,null);
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
//            classNotFoundException.printStackTrace();

        }

    }
}
