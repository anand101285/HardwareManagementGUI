import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class ViewServices extends JFrame implements ActionListener {

    JFrame frame=new JFrame();
    Client connected_client;

    JTable viewtable;

    JButton returnbutton;
    JButton search;
    JTextField getsearch;
    Vector originalTableModel;

    ViewServices(ArrayList<Object> row)
    {

        JLabel label=new JLabel();
        label.setText("Customer: "+connected_client.temp_customer.getName());
        label.setFont(connected_client.customFont);
        label.setForeground(new Color(0xffffff));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);


        JPanel top=new JPanel();
        top.setBounds(0,0,1000,100);
        top.setBackground(new Color(0x00111c));
        top.add(label);


        Object[][] rows=new Object[row.size()][6];

        for(int i=0;i<row.size();i++)
        {
//            int j=0;
//            for (j=0;j<connected_client.customer.size();j++) {
//                if(((service) row.get(i)).getCustomer_id().equals(((customer) connected_client.customer.get(j)).getCNIC()))
//                {
//                    break;
//                }
//            }
            rows[i]=(row.get(i)).toString().split(" ");
//            rows[i][3]=((customer)connected_client.customer.get(j)).getName();
        }

        String column_name[]= {"Customer ID", "Hardware ID", "Service Type"};

        DefaultTableModel defmodel= new DefaultTableModel(rows,column_name){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        viewtable =new JTable(defmodel);
        viewtable.getTableHeader().setBackground(new Color(0x393943));
        viewtable.getTableHeader().setForeground(new Color(0xc0c0c2));
        viewtable.setFont(new Font("Segoe Print", Font.PLAIN, 15));
        viewtable.setBackground(new Color(0x393943));
        viewtable.setForeground(new Color(0xc0c0c2));
        viewtable.setRowHeight(30);
        viewtable.getColumnModel().getColumn(0).setPreferredWidth(30);

        JScrollPane scrollPane=new JScrollPane(viewtable);

        scrollPane.setVisible(true);
        originalTableModel = (Vector) ((DefaultTableModel) viewtable.getModel()).getDataVector().clone();


        JPanel optionspanel=new JPanel();
        optionspanel.setBounds(0,100,1000,500);
        optionspanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        optionspanel.setBackground(new Color(0x00111c));
        optionspanel.setLayout(new BorderLayout());
        optionspanel.add(scrollPane,BorderLayout.CENTER);



        returnbutton = new RoundButton();
        returnbutton.setBounds(2,20,100,30);

        returnbutton.setText("RETURN");
        returnbutton.setFont(new Font("Calibre",Font.BOLD,10));
        
        
        returnbutton.addActionListener(this);


        getsearch =new JTextField();
        getsearch.setSize(150,30);
        getsearch.setLocation(370,20);

        search =new RoundButton();
        search.setBounds(520,20,100,30);
        search.setText("search: ");
        search.setFont(new Font("Calibre",Font.BOLD,10));
        
        
        search.addActionListener(this);



        JPanel end=new JPanel();
        end.setBounds(0,600,1000,150);
        end.setBackground(new Color(0x00111c));
        end.setLayout(null);
        end.add(returnbutton);
        end.add(search);
        end.add(getsearch);



        frame.setVisible(true);
        frame.setTitle(this.getClass().getSimpleName());
        frame.setSize(1000,750);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0xFFFFFF));
        frame.add(top);
        frame.add(optionspanel);
        frame.add(end);

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
            connected_client.services(7,null);
        }
        else if(e.getSource()==search)
        {
            Search(getsearch.getText());
        }


    }
}
