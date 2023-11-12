package Repository;

import Domain.Entity;

import java.util.ArrayList;
import java.util.Collection;


public class MemoryRepository<T extends Entity> extends AbstractRepository<T>{

    @Override
    public void add(T entity) throws RepositoryException {
        if (entity == null){
            throw new IllegalArgumentException();
        }
        if (findById(entity.getId()) != null){
            throw new DuplicateObjectException("Cannot duplicate reporitory objects!");
        }
        list.add(entity);
    }

    @Override
    public void remove(int id) throws RepositoryException {
        if(findById(id) == null)
            throw new RepositoryException("The element already doesn't exist");
        else{
            list.remove(findById(id));
        }
    }

    @Override
    public T findById(int id) {
        for(T entity : list){
            if(entity.getId() == id)
                return entity;
        }
        return null;
    }

    @Override
    public void update(int id, T entity) throws RepositoryException {
        remove(id);
        add(entity);
    }

    @Override
    public Collection<T> getAll() {
        return new ArrayList<>(list);
    }
}
