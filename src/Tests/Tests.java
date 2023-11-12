package Tests;


import Domain.Cake;
import Domain.ComandException;
import Repository.RepositoryException;
import Service.ServiceException;

public class Tests {
   public void TestAll() throws ServiceException, RepositoryException, ComandException {
       CakeTest cakeTest = new CakeTest();
       cakeTest.testCake();

       ComandTest comandTest = new ComandTest();
       comandTest.TestComand();

       RepositoryTest repositoryTest = new RepositoryTest();
       repositoryTest.RepositoryCakeTest();
       repositoryTest.RepositoryComandTest();

       ServiceTest serviceTest = new ServiceTest();
       serviceTest.ServiceCakeTest();
       serviceTest.ServiceComandTest();
   }
}
