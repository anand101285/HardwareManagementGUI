import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

public class SelectService extends JFrame implements ActionListener {


    JFrame frame=new JFrame();
    Client connected_client;

    JTable viewtable;

    JButton returnbutton;
    JButton Buy;
    JButton Repair;
    JButton search;
    JButton viewservices;
    JTextField getsearch;
    JButton b;
    JDialog d;
    JSpinner number;
    Vector originalTableModel;
    ArrayList<Object> hardware_part;

    SelectService(ArrayList<Object> row) throws IOException, FontFormatException {




        hardware_part=row;
        JLabel label=new JLabel();
        label.setText("CUSTOMER :"+connected_client.temp_customer.getName().toUpperCase());
        label.setFont(connected_client.customFont);
        label.setForeground(new Color(0xffffff));
        label.setLocation(400,40);



        JPanel top=new JPanel();
        top.setBounds(220,0,1040,100);
        top.setBackground(new Color(0x00111c));
        top.add(label);



        Object[][] rows=new Object[row.size()][6];

        for(int i=0;i<row.size();i++)
        {
            rows[i]=(row.get(i)).toString().split(" ");
        }

        String column_name[]= {"ID", "Name", "Category", "price", "Quantity", "Date","Manufacturer ID", "Manufacturer"};

        DefaultTableModel defmodel= new DefaultTableModel(rows,column_name){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        viewtable =new JTable(defmodel);
        viewtable =new JTable(defmodel);
        viewtable.getTableHeader().setBackground(new Color(0x393943));
        viewtable.getTableHeader().setForeground(new Color(0xc0c0c2));
        viewtable.setFont(new Font("Segoe Print", Font.PLAIN, 15));
        viewtable.setBackground(new Color(0x393943));
        viewtable.setForeground(new Color(0xc0c0c2));
        viewtable.setRowHeight(30);
        viewtable.getColumnModel().getColumn(0).setPreferredWidth(10);
        viewtable.getColumnModel().getColumn(3).setPreferredWidth(15);
        viewtable.getColumnModel().getColumn(4).setPreferredWidth(10);


        JScrollPane scrollPane=new JScrollPane(viewtable);

        scrollPane.setVisible(true);
        originalTableModel = (Vector) ((DefaultTableModel) viewtable.getModel()).getDataVector().clone();


        JPanel optionspanel=new JPanel();
        optionspanel.setBounds(220,100,1015,500);
        optionspanel.setBorder(new EmptyBorder(10, 10, 10, 20));
        optionspanel.setBackground(new Color(0x00111c));
        optionspanel.setLayout(new BorderLayout());
        optionspanel.add(scrollPane,BorderLayout.CENTER);



        returnbutton = new RoundButton();
        returnbutton.setBounds(10,100,100,30);

        returnbutton.setText("RETURN");
        returnbutton.setFont(new Font("Calibre",Font.BOLD,10));
        
        
        returnbutton.addActionListener(this);


        Buy =new RoundButton();
        Buy.setBounds(10,240,200,50);
        Buy.setText("Buy");
        Buy.setFont(new Font("Calibre",Font.BOLD,15));
        Buy.setBackground(new Color(0x00111c));
        Buy.setForeground(new Color(0xffffff));
        Buy.addActionListener(this);

        viewservices =new RoundButton();
        viewservices.setBounds(10,360,200,50);
        viewservices.setText("View Service Data");
        viewservices.setFont(new Font("Calibre",Font.BOLD,15));
        
        
        viewservices.addActionListener(this);

        Repair =new RoundButton();
        Repair.setBounds(10,300,200,50);
        Repair.setText("Repair");
        Repair.setFont(new Font("Calibre",Font.BOLD,15));
        
        
        Repair.addActionListener(this);


        getsearch =new JTextField();
        getsearch.setSize(150,30);
        getsearch.setLocation(370,50);

        search =new RoundButton();
        search.setBounds(520,45,100,35);
        search.setText("search: ");
        search.setFont(new Font("Calibre",Font.BOLD,15));
        
        
        search.addActionListener(this);



        JPanel end=new JPanel();
        end.setBounds(220,600,1040,150);
        end.setBackground(new Color(0x00111c));
        end.setLayout(null);
        end.add(search);
        end.add(getsearch);


        JPanel side=new JPanel();
        side.setBounds(0,0,220,750);
        side.setLayout(null);
        side.setBackground(new Color(0x00111c));
        side.add(returnbutton);
        side.add(Repair);
        side.add(Buy);
        side.add(viewservices);



        frame.setVisible(true);
        frame.setTitle(this.getClass().getSimpleName());
        frame.setSize(1240,750);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0xFFFFFF));
        frame.add(top);
        frame.add(optionspanel);
        frame.add(end);
        frame.add(side);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }


    public void Search(String searchstr)
    {

        DefaultTableModel currtableModel = (DefaultTableModel) viewtable.getModel();
        //To empty the table before search
        currtableModel.setRowCount(0);
        //To search for contents from original table content
        for (Object trows : originalTableModel) {
            Vector rowVector = (Vector) trows;
            for (Object column : rowVector) {
                if (column.toString().contains(searchstr)) {
                    //content found so adding to table
                    currtableModel.addRow(rowVector);
                    break;
                }
            }

        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==returnbutton)
        {
            frame.dispose();
            connected_client.communication(5,null,-1);
            new MainMenu();

        }
        else if(e.getSource()==search)
        {
            Search(getsearch.getText());
        }
        else if(e.getSource()==Buy)
        {
            int col=viewtable.getSelectedColumn();
            int row=viewtable.getSelectedRow();

            if(row!=-1 && col!=-1) {

                try {
                    d = new JDialog(frame, "dialog Box");

                    // create a label
                    JLabel l = new JLabel("Quantity: ");
                    SpinnerModel model=null;
                    model = new SpinnerNumberModel(1, 1, Integer.parseInt(viewtable.getValueAt(row, 4).toString()), 1);

                    number = new JSpinner(model);
                    // create a button
                    b = new JButton("Done");

                    // add Action Listener
                    b.addActionListener(this);

                    // create a panel
                    JPanel p = new JPanel();
                    p.add(l);
                    p.add(number);
                    p.add(b);

                    // add panel to dialog
                    d.add(p);
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    d.setLocation(dim.width/2-d.getSize().width/2, dim.height/2-d.getSize().height/2);
                    // setsize of dialog
                    d.setSize(200, 200);

                    // set visibility of dialog
                    d.setVisible(true);
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Sorry! product not avaliable");
                }


            }
            else {
                JOptionPane.showMessageDialog(null,"Please Select one of the row");
            }
        }
        else if(e.getSource()==b)
        {
            int quan=Integer.parseInt(number.getValue().toString());
            d.dispose();
            System.out.println("here");

            for (int i=0;i<hardware_part.size();i++)
            {
                if(viewtable.getValueAt(viewtable.getSelectedRow(),0).equals(((hardware)hardware_part.get(i)).getId()+""))
                {
                    System.out.println("Done");
                    ((hardware) hardware_part.get(i)).setQuantity_in_stock(((hardware) hardware_part.get(i)).getQuantity_in_stock()-quan);
                    connected_client.services(2,hardware_part.get(i));
                    connected_client.services(7,null);
                    this.frame.dispose();
                }
            }
        }
        else if(e.getSource()==Repair)
        {
            int col=viewtable.getSelectedColumn();
            int row=viewtable.getSelectedRow();
            if(row==-1 && col==-1)
            {
                JOptionPane.showMessageDialog(null,"Please select one of the row");
                return;
            }

            for (int i=0;i<hardware_part.size();i++)
            {
                if(viewtable.getValueAt(viewtable.getSelectedRow(),0).equals(((hardware)hardware_part.get(i)).getId()+""))
                {
                    System.out.println("Done");
                    connected_client.services(3,hardware_part.get(i));
                    JOptionPane.showMessageDialog(null,"Your Hardware has been sent for repair request");
                }
            }
        }
        else if(e.getSource()==viewservices)
        {
            this.frame.dispose();
            Client.services(4,null);
        }


    }
}
