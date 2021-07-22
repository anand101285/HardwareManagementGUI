import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import  javax.swing.UIManager;
import  javax.swing.UIManager.LookAndFeelInfo;
import  javax.swing.UnsupportedLookAndFeelException;

public class HardwareMenu extends JFrame implements ActionListener {

    JFrame frame=new JFrame();
    Client connected_client;

    JTable viewtable;


    JButton returnbutton;
    JButton insertnew;
    JButton delete;
    JButton search;
    JTextField getsearch;
    Vector originalTableModel;

    HardwareMenu(ArrayList<Object> row)
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
        JLabel label=new JLabel();
        label.setForeground(Color.WHITE);
        
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);


        JPanel top=new JPanel();
        top.setBounds(220,0,1040,100);
        top.setBackground(new Color(0x00111c));
        top.add(label);
        Image img_logo = new ImageIcon("C:\\Users\\anand\\IdeaProjects\\Hardware Management System Final Draft\\img\\"+connected_client.choice.toLowerCase()+".png").getImage();
        label.setIcon(new ImageIcon(img_logo));



        Object[][] rows=new Object[row.size()][6];

        for(int i=0;i<row.size();i++)
        {
            rows[i]=(row.get(i)).toString().split(" ");
        }
        String choice=connected_client.choice;


        String column_name[]=null;
        if(connected_client.choice.equals("Hardware"))
            column_name= new String[]{"ID", "Name", "Category", "price", "Quantity", "Date","Manufacturer ID", "Manufacturer"};
        else if(connected_client.choice.equals("Manufacturer"))
            column_name= new String[]{"Manufactureing ID ", "Manufacturer Name"};
        else if(connected_client.choice.equals("Services"))
            column_name= new String[]{"CNIC", "Name", "Contact"};

        DefaultTableModel defmodel= new DefaultTableModel(rows,column_name){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        viewtable =new JTable(defmodel);
        viewtable.getTableHeader().setBackground(new Color(0x000f17));
        viewtable.getTableHeader().setForeground(new Color(0xc0c0c2));
        viewtable.setFont(new Font("Segoe Print", Font.PLAIN, 15));
        viewtable.setBackground(new Color(0x031928));
        viewtable.setForeground(new Color(0xc0c0c2));
        viewtable.setRowHeight(30);
        viewtable.getColumnModel().getColumn(0).setPreferredWidth(10);
        if(connected_client.choice.equals("Hardware")) {
            viewtable.getColumnModel().getColumn(3).setPreferredWidth(15);
            viewtable.getColumnModel().getColumn(4).setPreferredWidth(10);
        }
        System.out.println(viewtable.getTableHeader().getFont().toString());
//        viewtable.getTableHeader().setFont(new FontUIResource(new Font("Segoe Print", Font.ITALIC, 70)));
        UIManager.put("TableHeader.font",new Font("Segoe Print", Font.BOLD, 17) );
        UIManager.put("TableHeader.foreground",new Color(0xc0c0c2));
        System.out.println(viewtable.getTableHeader().getFont().toString());
        viewtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent event) {
                if(connected_client.choice.equals("Services")) {
                    if (viewtable.getSelectedRow() > -1) {

                        String nic=viewtable.getValueAt(viewtable.getSelectedRow(), 0).toString();
                        System.out.println(nic);
                        for (int i=0;i<row.size();i++)
                        {
                            if(((customer)row.get(i)).getCNIC().equals(nic))
                            {
                                connected_client.temp_customer=(customer) row.get(i);
                            }

                        }
                        System.out.println("Chafnasdasd");

                        connected_client.services(7,null);
                        frame.dispose();

                    }
                }
            }
        });

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
        returnbutton.setText("<- RETURN");
        returnbutton.setFont(new Font("Calibre",Font.BOLD,10));
        
        
        returnbutton.addActionListener(this);




        insertnew =new RoundButton();
        insertnew.setBounds(10,240,200,50);
        if(choice.equals("Services"))
            insertnew.setText("Insert Customer");
        else
            insertnew.setText("Insert "+connected_client.choice);

        insertnew.setFont(new Font("Calibre",Font.BOLD,12));
        
        
        insertnew.addActionListener(this);


        delete =new RoundButton();
        delete.setBounds(10,300,200,50);
        if(choice.equals("Services"))
            delete.setText("Delete Customer");
        else
            delete.setText("Delete "+connected_client.choice);

        delete.setFont(new Font("Calibre",Font.BOLD,12));
        delete.addActionListener(this);
        if((choice.equals("Services")))
            delete.setVisible(false);


        getsearch =new JTextField();
        getsearch.setSize(150,30);
        getsearch.setBorder(new RoundBorder(20));
        getsearch.setLocation(370,20);

        search =new RoundButton();
        search.setBounds(520,20,100,30);
        search.setText("search: ");
        search.setFont(new Font("Calibre",Font.BOLD,10));
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
        side.add(insertnew);
        side.add(delete);

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

        else if(e.getSource()== insertnew)
        {
            if(connected_client.choice.equals("Manufacturer"))
                new AddManufacturer( frame );
            else if(connected_client.choice.equals("Hardware")) {
                try {
                    connected_client.add_hardware_data(frame);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
            else if(connected_client.choice.equals("Services"))
            {
                new AddCustomer( frame);
            }
        }

        else if(e.getSource()==delete)
        {
            int col=viewtable.getSelectedColumn();
            int row=viewtable.getSelectedRow();

            if(row!=-1 && col!=-1)
            {
                int id=Integer.parseInt(viewtable.getValueAt(row,0).toString());
                System.out.println("id: "+id);
                connected_client.communication(4,null,id);
                JOptionPane.showMessageDialog(null,"Record Successfully deleted");
                this.frame.dispose();
                connected_client.communication(5,null,-1);
                new MainMenu();

            }
            else{
                JOptionPane.showMessageDialog(null,"Please select one of the row to delete");
            }
        }



    }
}
