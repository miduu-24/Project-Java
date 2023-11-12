package Domain;

public abstract class Entity {
    protected int id;

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
}
