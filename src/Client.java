import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Client extends JFrame{

    public static final int PORT = 4444;
    public static ObjectOutputStream out;
    public static ObjectInputStream in;
    public static Socket socket;
    public static final String host="localhost";
    public static String msg;
    public static Object o;
    public static String choice;
    public static customer temp_customer;
    public static Font customFont = null;


//    public static void main(String args[])
//    {
//        try {
//
//            connecting();
//            set_Streams();
//            choose_communication_type();
//            send_Msg("bye");
//            showMsg((String)in.readObject());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public Client() throws IOException, ClassNotFoundException {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\anand\\IdeaProjects\\Hardware Management System Final Draft\\img\\gaglin.ttf")).deriveFont(40f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);

        connecting();
        set_Streams();
    }


    private static void connecting() throws IOException, ClassNotFoundException {
        showMsg("===========================================================================================================================================================================================================================================================================================");
        showMsg("Connecting to the server...");
        socket = new Socket(InetAddress.getByName(host), PORT);
        showMsg("Connected to " + socket.getInetAddress().getHostName());
    }


    private static void set_Streams() throws IOException{
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
        showMsg("I/O Stream is ready.");

    }



    public static void choose_communication_type( int client_choice) throws IOException, ClassNotFoundException {

            msg = (String) in.readObject();
            showMsg(msg);
            choice = null;
//            showMsg("\nWelcome User Select one of the following: \n1) Hardware \n2) Manufacturer \n3) Services \n4) Exit");
            System.out.println();
            switch (client_choice) {
                case 1:
                    choice="Hardware";
                    out.writeObject(choice);
                    out.flush();
//                    communication(1);
                    break;
                case 2:
                    choice="Manufacturer";
                    out.writeObject(choice);
                    out.flush();
//                    communication(1);
                    break;
                case 3:
                    choice="Services";
                    out.writeObject(choice);
                    out.flush();
//                    services();
                    break;
                case 4:
                    choice="Exit";
                    out.writeObject(choice);
                    out.flush();
                    break;
                default:
                    out.writeObject("Wrong");
                    out.flush();
                    break;
            }


    }

    public static void communication( int opt, Object obj, int integer) {
            try {
                showMsg("\n===========================================================================================================================================================================================================================================================================================");
                showMsg("\n///////////////////////////////////////////////////////////   Welcome to " + choice + " menu  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
                showMsg("Select an option: \n1) View \n2) Search \n3) Append \n4) Delete \n5) Go Back to Main Menu");

                switch (opt) {
                    case 1:
                        if (choice.equals("Hardware")) {
                            send_Msg("view Hardware");
                            ArrayList<Object> view_list = (ArrayList<Object>) in.readObject();
                            new HardwareMenu(view_list);

                            break;
                        } else if (choice.equals("Manufacturer")) {
                            send_Msg("view Manufacturer");
                            ArrayList<Object> view_list = (ArrayList<Object>) in.readObject();
                            new HardwareMenu(view_list);
                            break;
                        }

                    case 2:
                        send_Msg("search");
                        System.out.println("===========================================================================================================================================================================================================================================================================================");
                        System.out.println("Enter " + choice + " id ");
                        String search_id = "" + getInput();
                        send_Msg(search_id);
                        try {
                            o = in.readObject();
                            msg = (String) o;
                            if (msg.equals("Not found"))
                                System.out.println("========================= ////// Data not found \\\\\\\\ =========================");
                        } catch (Exception e) {
                            System.out.println(o.toString());

                        }
                        break;
                    case 3:
                        if (choice.equals("Manufacturer")) {

                            send_Msg("append");
                            out.writeObject(obj);
                        } else if (choice.equals("Hardware")) {
//                            hardware h = add_hardware_data();
                            System.out.println("Appending Hardware");
                            send_Msg("append");
                            out.writeObject(obj);
                        }
                        out.flush();
                        String fromserver = (String) in.readObject();
                        System.out.println(fromserver);
                        if(fromserver.equals("Exist"))
                            JOptionPane.showMessageDialog(null,"The ID already Exist !");
                        else
                            JOptionPane.showMessageDialog(null,"Data Added Successfully");
                        break;

                    case 4:
                        send_Msg("delete");
                        System.out.println("Enter ID of the part to delete: ");
                        int id = integer;
                        out.writeObject(String.valueOf(id));
                        out.flush();
                        int check = in.readInt();
                        System.out.println("data " + check);
                        if (check == 1)
                            System.out.println("Data successfully deleted");
                        else if (check == 0)
                            System.out.println("Record Not Found in the DataBase  or some other error");
                        break;

                    case 5:
                        System.out.println("Into Main menu");
                        send_Msg("Main");
                        msg = "Main Menu";
                        break;

                    default:
                        send_Msg("Wrong");
                        break;
                }
            } catch (ClassNotFoundException | IOException ex) {
                showMsg("Invalid object received");
            }


    }



    public static void services(int opt, Object obj) {

        showMsg("\n===========================================================================================================================================================================================================================================================================================");
        showMsg("\n//////////////////////// ///////////////////////////////////   Welcome to "+choice+" menu  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");

        ArrayList<Object> view_list;
        ArrayList<hardware> temp;
        hardware temp_hardware;

        int select_hardware;

            try {
                Scanner input = new Scanner(System.in);
                showMsg("Select a service: \n1) Add new Customer \n2) Buy \n3) Repair \n4) Display Service File \n5) Go Back to Main Menu");



                switch (opt) {


                    case 1:
                        send_Msg("Add customer");
                        temp_customer = (customer) obj;
                        out.writeObject(temp_customer);
                        out.flush();
                        if(((String) in.readObject()).equals("Done"));
                            JOptionPane.showMessageDialog(null,"Data Entered Successfully");
                        break;

                    case 2:

                        temp_hardware=(hardware)obj;
//                        temp_hardware.setQuantity_in_stock(temp_hardware.getQuantity_in_stock() - qty);
                        send_Msg("delete");
                        out.writeObject(String.valueOf(temp_hardware.getId()));
                        out.flush();

                        int check = in.readInt();

                        send_Msg("append");
                        out.writeObject(temp_hardware);
                        out.flush();
                        in.readObject();


                        send_Msg("service: buy");
                        out.writeObject(providing_service(temp_customer, temp_hardware, "buy"));
                        out.flush();
                        break;

                    case 3:
                        temp_hardware=(hardware)obj;
                        showMsg("Item is has been sent to repair");

                        send_Msg("service: repair");
                        out.writeObject(providing_service(temp_customer,temp_hardware,"repair"));
                        out.flush();
                        break;

                    case 4:
                        send_Msg("view service");
                        view_list= (ArrayList<Object>) in.readObject();
                        System.out.println("viewed service");
                        new ViewServices(view_list);
                        break;

                    case 5:
                        send_Msg("Main");
                        msg = "Main Menu";
                        break;
                    case 6:
                        send_Msg("view Customer");
                        view_list= (ArrayList<Object>) in.readObject();
                        new HardwareMenu(view_list);
                        break;
                    case 7:
                        send_Msg("view Hardware");
                        view_list = (ArrayList<Object>) in.readObject();
                        new SelectService(view_list);
                        break;

                    default:
                        System.out.println("Wrong Input Enter Again");
                        break;
                }




            } catch (Exception ex) {
                ex.printStackTrace();
                showMsg("Invalid object received");
            }

    }

    private static void showMsg(final String msg) {
        System.out.println(msg);
    }


    private static void send_Msg(String msg) {
        try {
            out.writeObject("Client# " + msg);
            out.flush();
            showMsg("Client# " + msg);
        } catch (IOException ex) {
            showMsg("Error: " + ex);
        }
    }


    private static void terminate_connection() {
        showMsg("Connection closed");
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
        }
    }
    public static int getInput()
    {
        int input;
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                input = in.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Error: " + e);
                System.out.println("Enter Again");
            }
        }
        return input;
    }

    public static double getDoubleInput()
    {
        double input;
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                input = in.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Error: " + e);
                System.out.println("Enter Again");
            }
        }
        return input;
    }

    private static ArrayList<hardware> downcast_to_Hardware(ArrayList<Object> obj)
    {
        ArrayList<hardware> hardware_obj=new ArrayList<hardware>();
        for(int i=0;i<obj.size();i++)
        {
            hardware_obj.add((hardware)obj.get(i));
        }
        return hardware_obj;
    }

    private static ArrayList<Manufacturer> downcast_to_Manufacturer(ArrayList<Object> obj)
    {
        ArrayList<Manufacturer> manufacturer_obj=new ArrayList<Manufacturer>();
        for(int i=0;i<obj.size();i++)
        {
            manufacturer_obj.add((Manufacturer) obj.get(i));
        }
        return manufacturer_obj;
    }


    public static void view_database(ArrayList<Object> view_list) {
        System.out.println("==================================================================[ DataBase ]===================================================================================================");
        for (int i = 0; i < view_list.size(); i++) {
            showMsg(i+") "+view_list.get(i).toString());
        }
    }

    public static void add_hardware_data(JFrame frame) throws IOException, ClassNotFoundException {
//        BufferedReader input =new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter ID#: ");
//        int id = getInput();
        System.out.println("Enter Name: ");
//        String name = input.readLine();
        System.out.println("Enter Category: ");
//        String cat = input.readLine();
        System.out.println("Enter price of part: ");
//        double price = getDoubleInput();
        System.out.println("Enter Date of manufacturer ");
//        String date=input.readLine();
        System.out.println("Enter quantity in stock : ");
//        int quantity = getInput();


        Manufacturer manufacturer = null;
        showMsg("\n1) Add New Manufacturer\n2) Add an Existing Manufacturer");
//        int user_selection = 0;
//        while(user_selection!=1 && user_selection!=2){
//            user_selection = getInput();
//            showMsg("Wrong Input.Enter Again!!!");
//        }
//        boolean found = false;
//        while(found == false) {
//            if (user_selection == 1) {
//                manufacturer = add_manufacturer_data();
//                found = true;
//            } else if (user_selection == 2) {
//        int manufacturer_id;
//        System.out.println("Enter Manufacturer's ID: ");
//        manufacturer_id = getInput();
        send_Msg("view Manufacturer");
        ArrayList<Object> view_list = (ArrayList<Object>) in.readObject();
//        String msg = (String)in.readObject();
        ArrayList<Manufacturer> manufacturers_list = downcast_to_Manufacturer(view_list);
        new AddHardware(manufacturers_list,frame);

//        for (int i = 0; i < manufacturers_list.size(); i++) {
//            if (manufacturers_list.get(i).getM_id() == manufacturer_id) {
//                manufacturer = manufacturers_list.get(i);
//                showMsg("Found Manufacturer : "+ manufacturer.toString());
//                found = true;
//            }
//        }
//            }
//        }

//        hardware h = new hardware(id, name, cat, price, quantity,date,manufacturer);
//        System.out.println("\nFollowing record has been added to the data base.");
//        System.out.println(h.toString());
//        return h;
    }

    public static Manufacturer add_manufacturer_data() throws IOException {
        BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
        Scanner in_int=new Scanner(System.in);
        System.out.println("Enter ID of manufacturer");
        int id = getInput();
        System.out.println("Enter Manufacturer name");
        String name=in.readLine();
        return new Manufacturer(id,name);
    }


    public static void append_customer() throws IOException {
        BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Customer's CNIC");
        String id = in.readLine();
        System.out.println("Enter Customer's name");
        String name=in.readLine();
        System.out.println("Enter Contact number ");
        String number=in.readLine();
//        return new customer(id,name,number);
    }


    public static service providing_service (customer temp_c , hardware temp_h, String type){
        service temp_service = new service(temp_c.getCNIC(),temp_h.getId(),type);
        return temp_service;
    }
}





