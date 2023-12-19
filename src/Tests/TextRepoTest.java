package Tests;

import Domain.Cake;
import Domain.CakeConvert;
import Repository.IRepository;
import Repository.RepositoryException;
import Repository.TextRepository;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextRepoTest {


    @org.junit.Test
    public void testAll(){
        update();
        addEntity();
        deleteById();
        TestFile();

    }
    CakeConvert pacientConvert = new CakeConvert();

    @Test
    void TestFile(){
        try{
            IRepository<Cake> repository = new TextRepository<>("RepoError.txt",pacientConvert);
            assert false;
        }
        catch (RuntimeException e){
            assert true;
        }

        try{
            IRepository<Cake> repository = new TextRepository<>("DontExist",pacientConvert);
            assert false;
        }
        catch (RuntimeException e){
            assert true;
        }

    }

    private void Innit(IRepository<Cake> repository){
        Cake pacient1 = new Cake(1, "John");
        Cake pacient2 = new Cake(2, "John");
        Cake pacient3 = new Cake(3, "John");
        try {
            repository.add(pacient1);
            repository.add(pacient2);
            repository.add(pacient3);
            assert true;

        } catch (RepositoryException e) {
            //assert false;
        }
    }

    private void ClearRepo(){
        try (FileWriter fw = new FileWriter("RepoTest.txt")) {
            Cake pacient = new Cake(1, "John");
            fw.write(pacientConvert.toString(pacient));
            assert true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void addEntity() {
        IRepository<Cake> repository = new TextRepository("RepoTest.txt",pacientConvert);
        ClearRepo();
        Innit(repository);
        try{
            repository.add(new Cake(1,"John"));
            assert false;
        }
        catch (RepositoryException e){
            assert true;
        }

        try{
            repository.add(new Cake(4,"John"));
            assert true;
        }
        catch (RepositoryException e){
            assert false;
        }
        ClearRepo();

    }

    @Test
    void deleteById() {
        IRepository<Cake> repository = new TextRepository<>("RepoTest.txt",pacientConvert);
        ClearRepo();
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
        ClearRepo();
        try{
            IRepository<Cake> repository1 = new TextRepository<>("RepoTest.txt",pacientConvert);
            assertEquals(repository1.getAll().size(),1);
            assert true;
        }
        catch (RuntimeException e){
            assert false;
        }
    }

    @Test
    void update() {
        ClearRepo();
        IRepository<Cake> repository = new TextRepository<>("RepoTest.txt",pacientConvert);
        assertEquals(repository.getAll().size(),1);
        Innit(repository);
        try {
            Cake pacient3 = new Cake(1, "John1");
            repository.update(1,pacient3);
            assert true;

        } catch (RepositoryException e) {
            assert false;
        }
        try {
            Cake pacient3 = new Cake(99, "John1");
            repository.update(99,pacient3);
            assert false;

        } catch (RepositoryException e) {
            assert true;
        }
        ClearRepo();
        try{
            IRepository<Cake> repository1 = new TextRepository<>("RepoTest.txt",pacientConvert);
            assertEquals(repository1.getAll().size(),1);
            assert true;
        }
        catch (RuntimeException e){
            assert false;
        }
    }
}
