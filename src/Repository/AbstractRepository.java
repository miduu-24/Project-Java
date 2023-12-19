package Repository;

import Domain.Entity;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractRepository<T extends Entity> implements IRepository<T> {

    protected ArrayList<T> list = new ArrayList<>();

    @Override
    public Iterator<T> iterator() {
        // returnam un iterator pe un shallow copy :) al campului data
        return new ArrayList<>(list).iterator();
//        return data.iterator();
    }
}
