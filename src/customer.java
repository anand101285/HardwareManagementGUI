import java.io.Serializable;

public class customer implements Serializable {
    private String CNIC;
    private String Name;
    private long Contact_Number;


    public customer(){

    }

    public customer(String id,String n,long number){
        this.Name = n;
        this.CNIC = id;
        this.Contact_Number = number;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getContact_Number() {
        return Contact_Number;
    }

    public void setContact_Number(int contact_Number) {
        Contact_Number = contact_Number;
    }

    @Override
    public String toString() {
        return CNIC+" "+Name.replaceAll("\\s", "")+" "+Contact_Number;
    }
}
