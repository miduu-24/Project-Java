package Domain;

import java.util.ArrayList;


public class Comand extends Entity implements java.io.Serializable {

    ArrayList<Cake> cakes;
    String date;

    public Comand() {
        cakes = new ArrayList<Cake>();
    }
    public Comand(int id, ArrayList<Cake> cakes, String data) throws ComandException {
        super(id);
        if (cakes.isEmpty()) {
            throw new ComandException("The cake list is empty!");
        }
        this.cakes = cakes;
        this.date = data;
    }

    public ArrayList<Cake> getCakes() {
        return cakes;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Comand{" +
                "id='" + getId() + '\'' +
                ", cakes='" + cakes + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

}
