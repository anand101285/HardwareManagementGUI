import jdk.jfr.DataAmount;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final int PORT = 4444;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static ServerSocket server;
    private static Socket socket;
    private static String choice;

    public static void main(String args[]) {
        try {
            server = new ServerSocket(PORT);
            while (true) {
                try {
                    connecting();
                    set_streams();
                    while(communication_type()) {
                        communication();
                    }

                } catch (EOFException ex) {
                    showMsg("\nServer connection closed");
                } finally {
                    terminate_connection();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void connecting() throws IOException {
        showMsg("\n===========================================================================================================================================================================================");
        showMsg("Waiting for connection...");
        socket = server.accept();
        showMsg("Connection accepted from " + socket.getInetAddress().getHostName());
    }

    private static void set_streams() throws IOException {
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
        showMsg("I/O Stream is ready.");

        String msg = "Connection established successfully.";
        send_Msg(msg);

    }


    private static boolean communication_type() throws IOException, ClassNotFoundException {
        boolean flag = true;
        choice = null;
        try {
            while (true) {
                System.out.println("Testing    asdasdasd");
                choice = (String) in.readObject();
                if(choice.equals("Wrong"))
                {
                    send_Msg("Wrong Input Enter Again");
                    continue;
                }
                else if (choice.equals("Exit")) {
                    flag = false;
                }
                break;
            }
        }
        catch (java.net.SocketException ex)
        {
            return false;
        }

        return flag;
    }


    private static void communication() {
        String msg = "Choice selected: "+ choice;
//        send_Msg(msg);
        showMsg("Client# Choice = " + choice);
        ArrayList<Object> return_database = null;
        do {
            try {
                Object search_in_file = null;
                Object write_to_file;
                System.out.println("Reading again...");
                msg = (String) in.readObject();
                showMsg("\n"+ msg);
                switch(msg){

                    case "Client# Main" :
                        msg = "Client# Main";
                        send_Msg("Return to Main Menu");
                        break;

                    case "Client# view Hardware":
                        System.out.println("choice :"+choice);
                        if(choice.equals("Hardware") || choice.equals("Services"))
                            return_database = Data_Handling.readAllData("hardware.sr");
                        System.out.println("Returned db");
                        out.writeObject(return_database);
                        out.flush();
                        break;

                    case "Client# view Manufacturer":

                        return_database= Data_Handling.readAllData("manufacturer.sr");
                        out.writeObject(return_database);
                        out.flush();
                        break;

                    case "Client# view Customer":
                        return_database=Data_Handling.readAllData("customer.sr");
                        out.writeObject(return_database);
                        out.flush();
                        break;

                    case "Client# search" :
                        if (choice.equals("Hardware") || choice.equals("Services"))
                            search_in_file = search_in_hardware(get_integer());
                        else if (choice.equals("Manufacturer"))
                            search_in_file = search_in_manufacturer(get_integer());
                        else
                            break;
                        System.out.println("searhed"+search_in_file);
                        if (search_in_file == null) {
                            out.writeObject("Not found");
                        } else {
                            out.writeObject(search_in_file);
                        }
                        out.flush();
                        break;

                    case "Client# delete":
                        String id = (String)in.readObject();
                        int check=0;
                        if(choice.equals("Hardware") || choice.equals("Services")){
                            check = delete_hardware(Integer.parseInt(id));
                        }
                        else if(choice.equals("Manufacturer"))
                            check= delete_manufacturer(Integer.parseInt(id));

                        showMsg("id to delete = "+id);

                        out.writeInt(check);
                        out.flush();
                        break;

                    case "Client# append":
                        int correct=0;
                        write_to_file = in.readObject();
                        if(choice.equals("Hardware") || choice.equals("Services")){
                                correct=check_if_HardwareExist(write_to_file);
                                System.out.println("after correct");
                                if(correct==1)
                                    Data_Handling.writeToFile(write_to_file,"hardware.sr");
                        }
                        else if(choice.equals("Manufacturer")) {
                            correct=check_if_ManufacturerExist(write_to_file);
                            if(correct==1)
                               Data_Handling.writeToFile(write_to_file, "manufacturer.sr");
                        }
                        System.out.println("coreect :"+correct);
                        if(correct==0)
                            out.writeObject("Exist");
                        else
                            out.writeObject("Done");
                        break;

                    case "Client# search customer":
                        String c_id = (String)in.readObject();
                        System.out.println("Id ==="+ c_id);
                        search_in_file = search_in_customer(c_id);

                        if (search_in_file == null) {
                            out.writeObject("Not found");
                        } else {
                            System.out.println("Data sent");
                            out.writeObject(search_in_file);
                        }
                        out.flush();
                        break;


                    case "Client# Add customer":
                        write_to_file = in.readObject();
                        Data_Handling.writeToFile(write_to_file,"customer.sr");
                        out.writeObject("Done");
                        break;


                    case "Client# service: buy":
                        System.out.println("Inside buy services");
                        write_to_file = in.readObject();
                        Data_Handling.writeToFile(write_to_file,"service.sr");
                        break;

                    case "Client# service: repair":
                        write_to_file = in.readObject();
                        Data_Handling.writeToFile(write_to_file,"service.sr");
                        break;

                    case "Client# view service":
                        return_database = Data_Handling.readAllData("service.sr");
                        out.writeObject(return_database);
                        out.flush();
                        break;
                    default:
                        send_Msg("Wrong Input Enter Again");
                        break;

                }
            } catch (ClassNotFoundException | IOException ex) {
                ex.printStackTrace();
                showMsg("Invalid object received");
                msg = "Client# Main";
            }
        } while (!msg.equals("Client# Main"));
    }

    private static int check_if_HardwareExist(Object h)
    {

        System.out.println("Inside hardware check");
        if(h.getClass().toString().contains("hardware"))
        {
            hardware h_data=(hardware) h;
            System.out.println(h_data.getId());
            if(search_in_hardware(h_data.getId())==null) {
                System.out.println("Extign hardware check");
                return 1;
            }
            else {
                System.out.println("Extign hardware check");
                return 0;
            }
        }
        else
            return 1;
    }

    private static int check_if_ManufacturerExist(Object h)
    {
        System.out.println("Inside M check");
        if(h.getClass().toString().contains("Manufacturer"))
        {
            Manufacturer m_data=(Manufacturer) h;
            System.out.println(m_data.getM_id());
            if(search_in_manufacturer(m_data.getM_id())==null)
                return 1;
            else
                return 0;
        }
        else
            return 1;
    }

    private static void showMsg(final String msg) {
        System.out.println(msg);
    }


    private static void send_Msg(String msg) {
        try {
            out.writeObject("\nServer# " + msg);
            out.flush();
            showMsg("\nServer# " + msg);
        } catch (IOException ex) {
            showMsg("\nError: " + ex);
        }
    }

    private static void terminate_connection() throws IOException {
        showMsg("\nConnection closed");
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
        }
    }


    private static int get_integer() throws IOException, ClassNotFoundException {
        String from_client = (String) in.readObject();
        String input = from_client.substring(8);

        return Integer.parseInt(input);

    }


    public static ArrayList<hardware> downcast_to_hardware(){
            ArrayList<hardware> view_list = new ArrayList<hardware>();
            ArrayList<Object> database  = Data_Handling.readAllData("hardware.sr");
            for( int i=0;i<database.size();i++)
            {
                view_list.add((hardware)database.get(i));
            }
            return view_list;
    }

    public static hardware search_in_hardware(int id){
        ArrayList<hardware> search_list = downcast_to_hardware();
        for (int i=0;i<search_list.size();i++) {
            if(search_list.get(i).getId()==id){
                System.out.println("Record found");
                return search_list.get(i);
            }
        }
        return null;
    }

    public static int delete_hardware(int id)
    {
        ArrayList<hardware> h = downcast_to_hardware();
        for(int i=0;i<h.size();i++)
        {
            if(h.get(i).getId()==id) {
                Data_Handling.delete_from_database(i, "hardware.sr");
                return 1;
            }
        }
        return 0;

    }

    private static ArrayList<Manufacturer> downcast_to_manufacturer(ArrayList<Object> obj)
    {
        ArrayList<Manufacturer> manufacturer_obj=new ArrayList<Manufacturer>();
        for(int i=0;i<obj.size();i++)
        {
            manufacturer_obj.add((Manufacturer) obj.get(i));
        }
        return manufacturer_obj;
    }

    public static Manufacturer search_in_manufacturer(int id){
        ArrayList<Manufacturer> search_list = downcast_to_manufacturer(Data_Handling.readAllData("manufacturer.sr"));
        for (int i=0;i<search_list.size();i++) {
            if(search_list.get(i).getM_id()==id){
                System.out.println("Record found");
                return search_list.get(i);
            }
        }
        return null;
    }

    public static int delete_manufacturer(int id)
    {
        ArrayList<Manufacturer> search_list = downcast_to_manufacturer(Data_Handling.readAllData("manufacturer.sr"));
        for(int i=0;i<search_list.size();i++)
        {
            if(search_list.get(i).getM_id()==id) {
                System.out.println("Deleting index s"+i);
                Data_Handling.delete_from_database(i, "manufacturer.sr");
                return 1;
            }
        }
        return 0;

    }


    public static ArrayList<customer> downcast_to_customer(){
        ArrayList<customer> view_list = new ArrayList<customer>();
        ArrayList<Object> database  = Data_Handling.readAllData("customer.sr");
        for( int i=0;i<database.size();i++)
        {
            view_list.add((customer) database.get(i));
        }
        return view_list;
    }


    public static customer search_in_customer(String id){
        ArrayList<customer> search_list = downcast_to_customer();
        for (int i=0;i<search_list.size();i++) {
            System.out.println("items in the list"+ search_list.get(i).toString());
            if(search_list.get(i).getCNIC().equals(id)){
                System.out.println("Record found");
                return search_list.get(i);
            }
        }
        return null;
    }
}

