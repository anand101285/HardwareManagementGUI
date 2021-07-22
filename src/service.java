import java.io.Serializable;

public class service implements Serializable {
    private  String Customer_id;
    private  int hardware_id;
    private  String type_of_service;

    public service() {
    }

    public service(String c_id, int h_id, String type) {
        this.Customer_id = c_id;
        this.hardware_id = h_id;
        this.type_of_service = type;
    }


    public String getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(String customer_id) {
        Customer_id = customer_id;
    }

    public int getHardware_id() {
        return hardware_id;
    }

    public void setHardware_id(int hardware_id) {
        this.hardware_id = hardware_id;
    }

    public String getType_of_service() {
        return type_of_service;
    }

    public void setType_of_service(String type_of_service) {
        this.type_of_service = type_of_service;
    }

    @Override
    public String toString() {
        return  Customer_id +" "+ hardware_id +" "+ type_of_service;
    }
}
