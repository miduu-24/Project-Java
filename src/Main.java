import Domain.ComandException;
import Repository.RepositoryException;
import Service.ServiceException;
import Tests.Tests;

public class Main {
    public static void main(String[] args) throws ServiceException, RepositoryException, ComandException {
        Tests tests = new Tests();
        tests.TestAll();
        UI ui = new UI();
        ui.run();
    }
}