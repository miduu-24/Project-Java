package Domain;

import java.util.ArrayList;

public class ComandConvert implements EntityConverter<Comand>{

    @Override
    public String toString(Comand object) {
        return object.getId() + "," + object.getCakes() + "," + object.getDate();
    }

    @Override
    public Comand fromString(String line) throws ComandException {
        String[] tok = line.split(",");
        int i = 1;
        ArrayList<Cake> Cakes = new ArrayList<>();
        for(i = 1; i < tok.length-1; i+=2){
            Cake t = new Cake(Integer.parseInt(tok[i]), tok[i+1]);
            Cakes.add(t);
        }
        return new Comand(Integer.parseInt(tok[0]), Cakes, tok[tok.length-1]);



    }
}
