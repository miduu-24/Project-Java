import Repository.CakeRepository;
import Repository.ComandRepository;
import Service.ServiceCake;
import Service.ServiceComand;

import java.util.Scanner;

public interface IUI {



    public void addCake(Validator validator, Scanner scanner) throws UIException;
    public void removeCake(Validator validator, Scanner scanner) throws UIException;
    public void updateCake(Validator validator, Scanner scanner) throws UIException;
    public void printAllCakes();

    public void addComand(Validator validator, Scanner scanner) throws UIException;
    public void removeComand(Validator validator, Scanner scanner) throws UIException;
    public void updateComand(Validator validator, Scanner scanner) throws ValidatorException, UIException;
    public void printAllComands();

    public void printMenu();

    public void run();
}
