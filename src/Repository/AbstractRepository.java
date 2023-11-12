package Repository;

import Domain.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractRepository<T extends Entity> implements IRepository<T> {

    protected List<T> list = new ArrayList<>();

    @Override
    public Iterator<T> iterator() {
        // returnam un iterator pe un shallow copy :) al campului data
        return new ArrayList<>(list).iterator();
//        return data.iterator();
    }
}
