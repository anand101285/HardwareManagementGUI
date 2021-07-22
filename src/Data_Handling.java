import java.io.*;
import java.util.ArrayList;


public class Data_Handling {


    public static void writedata () throws IOException {

//        FileOutputStream fos = new FileOutputStream("manufacturer.sr");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        Manufacturer m1 =  new Manufacturer(9000,"Samsung");
//        Manufacturer m2 = new Manufacturer(9001,"Sandisk");
//        Manufacturer m3 = new Manufacturer(9002,"Kingston");
//        Manufacturer m4 = new Manufacturer(9003,"Lenovo");
//        Manufacturer m5 = new Manufacturer(9004,"Kingston");
//        Manufacturer m6 = new Manufacturer(9005,"Dell");
//        Manufacturer m7 = new Manufacturer(9006,"Nividia");
//        Manufacturer m8 = new Manufacturer(9007,"Intel");
//        Manufacturer m9 = new Manufacturer(9008,"IBM");
//        Manufacturer m10 = new Manufacturer(9009,"AMD");

//        oos.writeObject(m1);
//        oos.writeObject(m2);
//        oos.writeObject(m3);
//        oos.writeObject(m4);
//        oos.writeObject(m5);
//        oos.writeObject(m6);
//        oos.writeObject(m7);
//        oos.writeObject(m8);
//        oos.writeObject(m9);
//        oos.writeObject(m10);





////
//        FileOutputStream fos = new FileOutputStream("hardware.sr");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        hardware r1 = new hardware(1000,"Sx900","Ram",25.14,12,"12/02/2020",m1);
//        hardware r2 = new hardware(1001, "Rx2900", "Ram",19.35,6,"23/01/2019",m2);
//        hardware r3 = new hardware(1002, "lx1900", "Ram",21.35,6,"07/01/2020",m3);
//        hardware r4 = new hardware(1003, "Qf2450", "Ram",34.2,5,"26/01/2020",m4);
//        hardware r5 = new hardware(1004, "Fr7650", "Ram",14.2,14,"19/01/2020",m5);
//        hardware r6 = new hardware(1005, "Tq6540", "Ram",87.5,4,"05/06/2020",m6);
//        hardware r7 = new hardware(1006, "Yr5430", "Ram",56.5,19,"12/01/2021",m7);
//        hardware r8 = new hardware(1007, "Mq4450", "Ram",45.76,21,"21/01/2021",m8);
//        hardware r9 = new hardware(1008, "Ps2430", "Ram",23.54,14,"24/04/2018",m9);
//        hardware r10 = new hardware(1009, "Ec1300", "Ram",73.65,5,"14/04/2021",m10);


//        hardware s1 = new hardware(2001, "x2900", "SSD",m2,"12/01/2020",19.35,6);
//        hardware s2 = new hardware(2002, "Rx2900", "SSD",m2,"12/01/2020",19.35,6);
//        hardware s3 = new hardware(2003, "Rx2900", "SSD",m2,"12/01/2020",19.35,6);
//        hardware s4 = new hardware(2004, "Rx2900", "SSD",m2,"12/01/2020",19.35,6);
//        hardware s5 = new hardware(2005, "Rx2900", "SSD",m2,"12/01/2020",19.35,6);
//        hardware s6 = new hardware(2006, "Rx2900", "SSD",m2,"12/01/2020",19.35,6);
//        hardware s7 = new hardware(2007, "Rx2900", "SSD",m2,"12/01/2020",19.35,6);
//        hardware s8 = new hardware(2008, "Rx2900", "SSD",m2,"12/01/2020",19.35,6);
//        hardware s9 = new hardware(2009, "Rx2900", "SSD",m2,"12/01/2020",19.35,6);
//        hardware s10 = new hardware(2010, "Rx2900", "SSD",m2,"12/01/2020",19.35,6);
//
//
//        hardware l1 = new hardware(3001, "Rx2900", "Laptop",m2,"12/01/2020",19.35,6);
//        hardware l2 = new hardware(3002, "Rx2900", "Laptop",m2,"12/01/2020",19.35,6);
//        hardware l3 = new hardware(3003, "Rx2900", "Laptop",m2,"12/01/2020",19.35,6);
//        hardware l4 = new hardware(3004, "Rx2900", "Laptop",m2,"12/01/2020",19.35,6);
//        hardware l5 = new hardware(3005, "Rx2900", "Laptop",m2,"12/01/2020",19.35,6);
//        hardware l6 = new hardware(3006, "Rx2900", "Laptop",m2,"12/01/2020",19.35,6);
//        hardware l7 = new hardware(3007, "Rx2900", "Laptop",m2,"12/01/2020",19.35,6);
//        hardware l8 = new hardware(3008, "Rx2900", "Laptop",m2,"12/01/2020",19.35,6);
//        hardware l9 = new hardware(3009, "Rx2900", "Laptop",m2,"12/01/2020",19.35,6);
//        hardware l10 = new hardware(3010, "Rx2900", "Laptop",m2,"12/01/2020",19.35,6);
//
//        hardware g1 = new hardware(4001, "Rx2900", "Graphic card",m2,"12/01/2020",19.35,6);
//        hardware g2 = new hardware(4002, "Rx2900", "Graphic card",m2,"12/01/2020",19.35,6);
//        hardware g3 = new hardware(4003, "Rx2900", "Graphic card",m2,"12/01/2020",19.35,6);
//        hardware g4 = new hardware(4004, "Rx2900", "Graphic card",m2,"12/01/2020",19.35,6);
//        hardware g5 = new hardware(4005, "Rx2900", "Graphic card",m2,"12/01/2020",19.35,6);
//        hardware g6 = new hardware(4006, "Rx2900", "Graphic card",m2,"12/01/2020",19.35,6);
//        hardware g7 = new hardware(4007, "Rx2900", "Graphic card",m2,"12/01/2020",19.35,6);
//        hardware g8 = new hardware(4008, "Rx2900", "Graphic card",m2,"12/01/2020",19.35,6);
//        hardware g9 = new hardware(4009, "Rx2900", "Graphic card",m2,"12/01/2020",19.35,6);
//        hardware g10 = new hardware(4010, "Rx2900", "Graphic card",m2,"12/01/2020",19.35,6);


//        oos.writeObject(r1);
//        oos.writeObject(r2);
//        oos.writeObject(r3);
//        oos.writeObject(r4);
//        oos.writeObject(r5);
//        oos.writeObject(r6);
//        oos.writeObject(r7);
//        oos.writeObject(r8);
//        oos.writeObject(r9);
//        oos.writeObject(r10);





//        FileOutputStream fos = new FileOutputStream("customer.sr");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//        customer c1 = new customer("930011","Anand Kumar","03001892470");
//        customer c2 = new customer ("930012","Ahsan Ashfaq","03098768767");
//
        FileOutputStream fos = new FileOutputStream("service.sr");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        service s1 = new service("930011",1000,"buy");


        oos.writeObject(s1);
//        oos.writeObject(c2);





        System.out.println("down writing to the file ");

        oos.close();

    }



    public static void writeToFile (Object s,String filename){
        ObjectOutputStream outputStream = null;

        try {
            // Read old objects
            ArrayList<Object> object_data = readAllData(filename);
            // Append new object into existing list
            object_data.add(s);
            // Open Stream for writing
            outputStream = new ObjectOutputStream(new FileOutputStream(filename));

            // Write all objects (old and new one) into the file
            for(int i = 0 ; i < object_data.size() ; i++) {
                outputStream.writeObject(object_data.get(i));
            }
        } catch(IOException e) {
            System.out.println("IO Exception while opening file");
        } finally { // cleanup code which closes output stream if its object was created
            try {
                if(outputStream != null) {
                    outputStream.close();
                }

            } catch (IOException e) {
                System.out.println("IO Exception while closing file");
            }
        }
    }
    public static void delete_from_database (int index,String filename){
        ObjectOutputStream outputStream = null;
        // Read old objects
        ArrayList<Object> object_data = readAllData(filename);
        // Append new object into existing list
        object_data.remove(index);
        // Open Stream for writing
        try {
            // Open Stream for writing
            outputStream = new ObjectOutputStream(new FileOutputStream(filename));

            // Write all objects (old and new one) into the file
            for(int i = 0 ; i < object_data.size() ; i++) {
                outputStream.writeObject(object_data.get(i));
            }
        } catch(IOException e) {
            System.out.println("IO Exception while opening file");
        } finally { // cleanup code which closes output stream if its object was created
            try {
                if(outputStream != null) {
                    outputStream.close();
                }

            } catch (IOException e) {
                System.out.println("IO Exception while closing file");
            }
        }
    }
    public static ArrayList <Object>  readAllData (String filename){

        ArrayList<Object> object_data = new ArrayList<Object>(0);
        ObjectInputStream inputStream = null;
        try
        {
            // open file for reading
            inputStream = new ObjectInputStream(new FileInputStream(filename));
            // End Of File flag
            boolean EOF = false;
            // Keep reading file until file ends
            while(!EOF) {
                try {
                    // read object and type cast into CarDetails object
                    Object myObj = inputStream.readObject();
                    // add object into ArrayList
                    object_data.add(myObj);
                    //System.out.println("Read: " + myObj.getName());
                } catch (ClassNotFoundException e) {
                    //System.out.println("Class not found");
                } catch (EOFException end) {
                    // EOFException is raised when file ends
                    // set End Of File flag to true so that loop exits
                    EOF = true;
                }
            }
        }
        catch(FileNotFoundException e) {
            //System.out.println("Cannot find file");
        } catch (IOException e) {
            //System.out.println("IO Exception while opening stream");
            //e.printStackTrace();
        } finally { // cleanup code to close stream if it was opened
            try {
                if(inputStream != null)
                    inputStream.close( );
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("IO Exception while closing file");
            }
        }

        return object_data;
    }

    public static void main(String[] args) throws IOException {
        writedata ();
    }

}