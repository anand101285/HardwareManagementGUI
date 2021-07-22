import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomer extends JFrame implements ActionListener {

    JFrame frame=new JFrame();
    JTextField getName;
    JTextField getCNIC;
    JTextField getContact;
    JButton button;
    JFrame mainframe;

    AddCustomer( JFrame mainframe)
    {

        this.mainframe = mainframe;
        JLabel label=new JLabel();
        label.setText("Add Customer");
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
        ID.setText("CNIC : ");
        ID.setSize(140,20);
        ID.setLocation(350,50);
        ID.setFont(new Font("Segoe Print",Font.BOLD,20));
        ID.setForeground(new Color(0xffffff));

        JLabel Contact=new JLabel();
        Contact.setText("Contact : ");
        Contact.setSize(120,20);
        Contact.setLocation(50,100);
        Contact.setFont(new Font("Segoe Print",Font.BOLD,20));
        Contact.setForeground(new Color(0xffffff));


        getName =new JTextField();
        getName.setSize(150,20);
        getName.setLocation(172,50);

        getCNIC = new JTextField();
        getCNIC.setSize(170,20);
        getCNIC.setLocation(420,50);

        getContact = new JTextField();
        getContact.setSize(150,20);
        getContact.setLocation(172,100);

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
        panel.add(Contact);
        panel.add(getName);
        panel.add(getCNIC);
        panel.add(getContact);

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
            if(getCNIC.getText().trim().length()==13) {
                if (getContact.getText().trim().length() == 11) {
                    try {
                        customer c = new customer(getCNIC.getText(), getName.getText(), Long.parseLong(getContact.getText()));
                        Client.services(1, c);
                        frame.dispose();
                        mainframe.dispose();
                        Client.services(6, null);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Contact number can have number only!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please Enter a Valid Number");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Please Enter Valid CNIC, e.g\'4232123452563\' ");
            }


        }

    }
}
