import java.io.Serializable;

public class Manufacturer implements Serializable {
    private String name;
    private int M_id;

    public Manufacturer(int m_id, String name) {
        this.name = name;
        M_id = m_id;
    }
    public Manufacturer(Manufacturer m)
    {
        this.name=m.getName();
        this.M_id=m.getM_id();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getM_id() {
        return M_id;
    }

    public void setM_id(int m_id) {
        M_id = m_id;
    }


    @Override
    public String toString()
    {
        return this.M_id+" "+this.name;
    }
}
