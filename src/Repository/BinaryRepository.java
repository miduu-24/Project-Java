package Repository;

import Domain.Entity;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class BinaryRepository<T extends Entity> extends MemoryRepository<T> {
    private String fileName;

    public BinaryRepository(String fileName) {
//        super();
        this.fileName = fileName;
        try {
            loadFile();
        } catch (IOException | ClassNotFoundException e) {
            // not very good practice
            //throw new RuntimeException(e);
        }
    }


    @Override
    public void add(T o) throws RepositoryException {
        super.add(o);
        // saveFile se executa doar daca super.add() nu a aruncat exceptie
        try {
            saveFile();
        } catch (IOException e) {
            throw new RepositoryException("Error saving file " + e.getMessage(), e);
        }
    }

    @Override
    public void remove(int id) throws RepositoryException {
        super.remove(id);
        try {
            saveFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, T elem) throws RepositoryException {
        super.update(id, elem);
        try {
            saveFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadFile() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        Path path = Paths.get(fileName);
        try {
            InputStream is = Files.newInputStream(path);
            ois = new ObjectInputStream(is);
            this.list = (ArrayList<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            // TODO Replace with logging
            System.out.println("Repo starting a new file");
        } finally {
            // finally se executa tot timpul
            if (ois != null)
                ois.close();
        }
    }


    private void saveFile() throws IOException {
        // try-with-resources
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        }
    }
}
