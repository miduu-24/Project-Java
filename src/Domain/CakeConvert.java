package Domain;

public class CakeConvert implements EntityConverter<Cake> {

    @Override
    public String toString(Cake object) {
        return object.getId() + "," + object.getType();

    }

    @Override
    public Cake fromString(String line){
        String []tok = line.split(",");
        return new Cake(Integer.parseInt(tok[0]),tok[1]);
    }

}
