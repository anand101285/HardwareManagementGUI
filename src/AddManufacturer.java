import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddManufacturer extends JFrame implements ActionListener {

    JFrame frame=new JFrame();
    JTextField getName;
    JTextField getID;
    JButton button;
    JFrame mainframe;

    AddManufacturer( JFrame mainframe)
    {

        this.mainframe = mainframe;
        JLabel label=new JLabel();
        label.setText("Add Manufacturer");
        label.setSize(500,50);
        label.setLocation(320,40);
        label.setFont(Client.customFont);
        label.setForeground(new Color(0xffffff));

        JPanel backcolor=new JPanel();
        backcolor.setSize(400,50);
        backcolor.setLocation(320,50);
        backcolor.setBackground(new Color(0x00111c));
        backcolor.add(label);


        JLabel Name=new JLabel();
        Name.setText("Name : ");
        Name.setSize(120,20);
        Name.setLocation(50,50);
        Name.setFont(new Font("Segoe Print",Font.BOLD,20));
        Name.setForeground(new Color(0xffffff));
        


        JLabel ID=new JLabel();
        ID.setText("ID : ");
        ID.setSize(140,20);
        ID.setLocation(350,50);
        ID.setFont(new Font("Segoe Print",Font.BOLD,20));
        ID.setForeground(new Color(0xffffff));



        getName =new JTextField();
        getName.setSize(150,20);
        getName.setLocation(172,50);

        getID = new JTextField();
        getID.setSize(80,20);
        getID.setLocation(400,50);

        JLabel back_img=new JLabel();
        Image img_logo = new ImageIcon("C:\\Users\\anand\\IdeaProjects\\Hardware Management System Final Draft\\img\\addback1.png").getImage();
        back_img.setBounds(0,0,1000,500);
        back_img.setIcon(new ImageIcon(img_logo));


        JPanel panel=new JPanel();
        panel.setBounds(0,100,1000,300);
        panel.setBackground(new Color(0x00111c));
        panel.setLayout(null);
        panel.add(Name);
        panel.add(ID);
        panel.add(getName);
        panel.add(getID);

        button =new RoundButton();
        button.setBounds(450,410,100,30);
        button.setText("INSERT");
        button.setFont(new Font("Segoe Print",Font.BOLD,10));
        

        button.addActionListener(this);

        frame.setVisible(true);
        frame.setTitle("add");
        frame.setSize(1000,500);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0x00111c));
        frame.add(panel);
        frame.add(button);
        frame.add(backcolor);
        frame.add(back_img);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button)
        {
            Manufacturer m=new Manufacturer(Integer.parseInt(getID.getText()),getName.getText());

            Client.communication(3,m,-1);

            frame.dispose();
            mainframe.dispose();
            Client.communication(5,null,-1);
            new MainMenu();


        }

    }
}
