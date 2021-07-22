import java.io.Serializable;

public class hardware implements Serializable {
    private int id;
    private String name;
    private String category;
    private double price;
    private int quantity_in_stock;
    private String date_of_manufacturer;
    private Manufacturer manufacturer;

    public hardware(int id, String name, String category, double price, int quantity_in_stock,String Date, Manufacturer manufacturer) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity_in_stock = quantity_in_stock;
        this.date_of_manufacturer =Date;
        this.manufacturer = manufacturer;
    }

    public String getDate_of_manufacturer() {
        return date_of_manufacturer;
    }

    public void setDate_of_manufacturer(String date_of_manufacturer) {
        this.date_of_manufacturer = date_of_manufacturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public void setQuantity_in_stock(int quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    @Override
    public String toString() {
        return id+" "+name+" "+category+" "+price+" "+quantity_in_stock+" "+date_of_manufacturer+" "+manufacturer.toString()    ;
    }
}
