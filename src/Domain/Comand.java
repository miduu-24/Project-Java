package Domain;

import java.util.ArrayList;


public class Comand extends Entity {

    ArrayList<Cake> cakes;
    String date;

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

    public void addCake(Cake cake) {
        cakes.add(cake);
    }

    public void removeCake(Cake cake) {
        cakes.remove(cake);
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
