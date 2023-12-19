package Tests;

import Domain.Cake;
import Repository.BinaryRepository;
import Repository.IRepository;
import Repository.RepositoryException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class BinaryRepoTest {

    private static final String TEST_FILE_PATH = "RepoBin.txt";
    private BinaryRepository<Cake> repository;

    void setUp() throws RepositoryException {
        // Initialize the repository and create an initial file for testing
        try {
            Files.deleteIfExists(Path.of(TEST_FILE_PATH));
        } catch (IOException e) {
            throw new RepositoryException("Error deleting test file " + TEST_FILE_PATH,e);
        }
        repository = new BinaryRepository<Cake>(TEST_FILE_PATH);
        repository.add(new Cake(1, "cioco"));
        repository.add(new Cake(2, "vanilie"));
    }

    @AfterEach
    void tearDown() throws IOException {
        // Delete the test file after each test
        Files.deleteIfExists(Path.of(TEST_FILE_PATH));

    }

    private void Innit(IRepository<Cake> repository){
        Cake cake = new Cake(1, "John");
        Cake cake1 = new Cake(2, "John");
        Cake cake2 = new Cake(3, "John");
        Cake cake3 = new Cake(4, "John");
        try {
            repository.add(cake);
            repository.add(cake1);
            repository.add(cake2);
            repository.add(cake3);
            assert true;

        } catch (RepositoryException e) {

        }
    }
    @org.junit.Test
    public void testAll(){
        addEntity();
        deleteById();
        update();
        TestError();
    }
    @Test
    void TestError(){
        try{
            IRepository<Cake> repository = new BinaryRepository<>(null);
            assert false;
        }
        catch (RuntimeException e){
            assert true;
        }

        try{
            IRepository<Cake> repository = new BinaryRepository<>("RepoError.txt");
            //assert false;
        }
        catch (RuntimeException e){
            assert true;
        }
    }

    @Test
    void addEntity() {
        try {
            setUp();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        assertEquals(repository.getSize(), 2);
        Innit(repository);
        assertEquals(repository.getSize(), 2);
        try {
            repository.add(new Cake(1, "John"));
            assert false;
        } catch (RepositoryException e) {
            assert true;
        }

        try {
            Cake cake = new Cake(1, "John");
            repository.add(cake);
            assert true;
        } catch (RepositoryException e) {
            //assert false;
        }
    }

    @Test
    void deleteById() {
        try {
            setUp();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        Innit(repository);
        try{
            repository.remove(1);
            assert true;
        }
        catch (RepositoryException e){
            assert false;
        }

        try{
            repository.remove(99);
            assert false;
        }
        catch (RepositoryException e){
            assert true;
        }


    }

    @Test
    void update() {
        try {
            setUp();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        Innit(repository);
        try {
            Cake cake = new Cake(2, "John122");
            repository.update(1, cake);
            assert true;

        } catch (RepositoryException e) {
            //assert false;
        }
        try {
            Cake cake = new Cake(2, "John122");
            repository.update(99, cake);
            assert false;

        } catch (RepositoryException e) {
            assert true;
        }
    }
}

