import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddHardware extends JFrame implements ActionListener {


    JFrame frame=new JFrame();
    JTextField getName;
    JTextField getID;
    JTextField getcat;
    JTextField getprice;
    JButton button;
    JSpinner number;
    JSpinner date;
    JSpinner month;
    JSpinner year;
    JFrame mainf;
    JComboBox manu_box;
    ArrayList <Manufacturer> manufacturer;

    AddHardware(ArrayList <Manufacturer> manu ,JFrame mainframe)
    {
        mainf=mainframe;
        manufacturer=manu;
        JLabel label=new JLabel();
        label.setText("ADD DATA");
        label.setSize(500,50);
        label.setLocation(320,50);
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
        Name.setBackground(new Color(0x00111c));

        JLabel Manufacturer=new JLabel();
        Manufacturer.setText("Manufacturer : ");
        Manufacturer.setSize(180,20);
        Manufacturer.setLocation(50,180);
        Manufacturer.setFont(new Font("Segoe Print",Font.BOLD,20));
        Manufacturer.setForeground(new Color(0xffffff));

        JLabel Date=new JLabel();
        Date.setText("Date(dd/mm/yyyy) : ");
        Date.setSize(230,40);
        Date.setLocation(440,170);
        Date.setFont(new Font("Segoe Print",Font.BOLD,20));
        Date.setForeground(new Color(0xffffff));


        JLabel ID=new JLabel();
        ID.setText("ID : ");
        ID.setSize(140,20);
        ID.setLocation(350,50);
        ID.setFont(new Font("Segoe Print",Font.BOLD,20));
        ID.setForeground(new Color(0xffffff));


        JLabel Category=new JLabel();
        Category.setText("Category : ");
        Category.setSize(120,30);
        Category.setLocation(50,110);
        Category.setFont(new Font("Segoe Print",Font.BOLD,20));
        Category.setForeground(new Color(0xffffff));


        JLabel Price=new JLabel();
        Price.setText("Price : ");
        Price.setSize(140,20);
        Price.setLocation(350,120);
        Price.setFont(new Font("Segoe Print",Font.BOLD,20));
        Price.setForeground(new Color(0xffffff));
        

        JLabel quantity=new JLabel();
        quantity.setText("Quantity : ");
        quantity.setSize(170,40);
        quantity.setLocation(510,110);
        quantity.setFont(new Font("Segoe Print",Font.BOLD,20));
        quantity.setForeground(new Color(0xffffff));


        getName =new JTextField();
        getName.setSize(150,20);
        getName.setLocation(172,50);

        getID = new JTextField();
        getID.setSize(80,20);
        getID.setLocation(400,50);

        getcat =new JTextField();
        getcat.setSize(150,20);
        getcat.setLocation(172,120);

        getprice = new JTextField();
        getprice.setSize(60,20);
        getprice.setLocation(420,120);

        String manu_names[]=new String[manu.size()];
        for (int i=0;i<manu.size();i++)
        {
            manu_names[i]=manu.get(i).getName();
        }
        manu_box=new JComboBox(manu_names);
        manu_box.setSize(150,20);
        manu_box.setLocation(235,180);

        SpinnerModel model=new SpinnerNumberModel(1,0,2000,1);
        number= new JSpinner(model);
        number.setSize(60,20);
        number.setLocation(650,120);


        SpinnerModel model1=new SpinnerNumberModel(1,1,31,1);
        date= new JSpinner(model1);
        date.setSize(40,20);
        date.setLocation(660,180);

        SpinnerModel model2=new SpinnerNumberModel(1,1,12,1);
        month= new JSpinner(model2);
        month.setSize(40,20);
        month.setLocation(705,180);;


        SpinnerModel model3=new SpinnerNumberModel(2000,2000,2021,1);
        year= new JSpinner(model3);
        year.setSize(60,20);
        year.setLocation(750,180);

        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(year, "#");
        year.setEditor(editor);

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
        panel.add(Category);
        panel.add(Price);
        panel.add(Manufacturer);
        panel.add(quantity);
        panel.add(getName);
        panel.add(getID);
        panel.add(getcat);
        panel.add(getprice);
        panel.add(manu_box);
        panel.add(number);
        panel.add(Date);
        panel.add(date);
        panel.add(year);
        panel.add(month);
//        panel.add(back_img);


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
//        frame.getContentPane().setBackground(new Color(0x00111c));
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
            Manufacturer m=null;
            for (int i=0;i<manufacturer.size();i++)
            {
                if(manufacturer.get(i).getName().equals(manu_box.getSelectedItem()))
                {
                    m=manufacturer.get(i);
                }
            }

            String d=date.getValue().toString()+"/"+month.getValue().toString()+"/"+year.getValue().toString();
            if(m!=null)
            {
                try {
                    double price =Double.parseDouble(getprice.getText());
                    hardware h=new hardware(Integer.parseInt(getID.getText()),getName.getText(),getcat.getText(),price,Integer.parseInt(number.getValue().toString()),d,m);
                    Client.communication(3,h,-1);
                    this.frame.dispose();
                    mainf.dispose();
                    Client.communication(5,null,-1);
                    new MainMenu();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Please Enter price & ID in Numbers Only");
                }


            }
        }

    }
}

