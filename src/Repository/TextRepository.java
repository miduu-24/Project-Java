package Repository;

import Domain.ComandException;
import Domain.Entity;
import Domain.EntityConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextRepository<T extends Entity> extends MemoryRepository<T>{
    private String fileName;

    private EntityConverter<T> convert;

    public TextRepository(String fileName, EntityConverter<T> converter) {
        this.fileName = fileName;
        this.convert = converter;

        loadFile();
    }

    @Override
    public void add(T elem) throws RepositoryException {
        super.add(elem);
        try{
            saveFile();
        }
        catch (RuntimeException re){throw new RepositoryException("Error saving file",re);
        }
    }

    @Override
    public void remove(int id) throws RepositoryException {
        super.remove(id);
        try{
            saveFile();
        }
        catch (RuntimeException re){throw new RepositoryException("Error saving file",re);
        }
    }

    @Override
    public void update(int id, T elem) throws RepositoryException {
        super.update(id, elem);
        try{
            saveFile();
        }
        catch (RuntimeException re){throw new RepositoryException("Error saving file",re);
        }
    }


    private void saveFile(){
        try (FileWriter fw = new FileWriter(fileName)) {
            for (T object : list) {
                fw.write(convert.toString(object));
                fw.write("\r\n");
            }
        } catch (IOException e) {throw new RuntimeException(e);
        }
    }

    private void loadFile(){
        list.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null && !line.isEmpty()) {
                list.add(convert.fromString(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ComandException e) {
            throw new RuntimeException(e);
        }
    }
}
