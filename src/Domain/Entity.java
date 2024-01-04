package Domain;

public abstract class Entity implements java.io.Serializable{
    protected int id;

    public Entity() {
    }

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Entity{" +
                "id='" + id + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }
}
