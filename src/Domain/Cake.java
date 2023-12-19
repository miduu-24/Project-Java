package Domain;

public class Cake extends Entity implements java.io.Serializable{
    private String type;
    public Cake(){

    }
    public Cake(int id, String type) {
        super(id);
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Cake{" +
                "id='" + getId() + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
