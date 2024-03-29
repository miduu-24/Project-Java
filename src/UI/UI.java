package UI;

import Domain.Cake;
import Service.ServiceCake;
import Service.ServiceComand;

import java.util.ArrayList;
import java.util.Scanner;

import Validator.Validator;
import Validator.ValidatorException;

public class UI implements IUI {

    private final ServiceCake serviceCake;
    private final ServiceComand serviceComand;

    public UI(ServiceCake serviceCake, ServiceComand serviceComand) {
        this.serviceCake = serviceCake;
        this.serviceComand = serviceComand;
    }

    @Override
    public void run() {

        while (true) {
            try {
                printMenu();
                Scanner scanner = new Scanner(System.in);
                System.out.println("Option: ");
                String option = scanner.nextLine();
                Validator validator = new Validator();
                int optionInt = validator.intValidator(option);
                switch (optionInt) {
                    case 1:
                        addCake(validator, scanner);
                        System.out.println("Cake added");
                        break;
                    case 2:
                        removeCake(validator, scanner);
                        System.out.println("Cake removed");
                        break;
                    case 3:
                        updateCake(validator, scanner);
                        System.out.println("Cake updated");
                        break;
                    case 4:
                        System.out.println("All cakes: ");
                        printAllCakes();
                        break;
                    case 5:
                        addComand(validator, scanner);
                        System.out.println("Comand added");
                        break;
                    case 6:
                        removeComand(validator, scanner);
                        System.out.println("Comand removed");
                        break;
                    case 7:
                        updateComand(validator, scanner);
                        System.out.println("Comand updated");
                        break;
                    case 8:
                        System.out.println("All comands: ");
                        printAllComands();
                        break;
                    case 9:
                        System.out.println("Number of cakes: ");
                        printNumberOfCakes();
                        break;
                    case 10:
                        System.out.println("Number of cakes by every month: ");
                        printNumberOfCakesByEveryMonth();
                        break;
                    case 11:
                        System.out.println("Number of comands by every cake: ");
                        printNumberOfComandsByEveryCake();
                        break;
                    case 0:
                        System.out.println("Bye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }

    }

    @Override
    public void addCake(Validator validator, Scanner scanner) throws UIException {
        try {
            System.out.println("Type: ");
            String type = scanner.nextLine();
            serviceCake.addCake(type);
        } catch (Exception e) {
            throw new UIException("Error adding cake: " + e);
        }
    }

    @Override
    public void removeCake(Validator validator, Scanner scanner) throws UIException {
        try{
            System.out.println("Cake id: ");
            String cakeId = scanner.nextLine();
            int id = validator.intValidator(cakeId);
            serviceCake.removeCake(id);
        } catch (Exception e) {
            throw new UIException("Error removing cake: " + e);
        }
    }

    @Override
    public void updateCake(Validator validator, Scanner scanner) throws UIException {
        try {
            System.out.println("Cake id you want to update: ");
            String cakeId = scanner.nextLine();
            int id = validator.intValidator(cakeId);

            System.out.println("New type: ");
            String type = scanner.nextLine();

            serviceCake.updateCake(id, type);
        } catch (Exception e) {
            throw new UIException("Error updating cake: " + e);
        }
    }

    @Override
    public void printAllCakes() {
        serviceCake.getAllCakes().forEach(System.out::println);
    }

    @Override
    public void addComand(Validator validator, Scanner scanner) throws UIException {
        try{
            System.out.println("New date: ");
            String date = scanner.nextLine();
            String data = validator.dataValidator(date);

            System.out.println("Numbers new cakes: ");
            String numberCakes = scanner.nextLine();
            int number = validator.intValidator(numberCakes);
            int[] ids = new int[number];
            for (int i = 0; i < number; i++) {
                System.out.println("Cake id: ");
                String cakeId = scanner.nextLine();
                int id_cake = validator.intValidator(cakeId);
                ids[i] = id_cake;
            }
            ArrayList<Cake> cakes = serviceComand.createCakeList(ids);
            serviceComand.addComand(cakes, data);
        }
        catch (Exception e) {
            throw new UIException("Error adding comand: " + e);
        }
    }

    @Override
    public void removeComand(Validator validator, Scanner scanner) throws UIException {
        try {
            System.out.println("Comand id: ");
            String comandId = scanner.nextLine();
            int id_comand = validator.intValidator(comandId);
            serviceComand.removeComand(id_comand);
        } catch (Exception e) {
            throw new UIException("Error removing comand: " + e);
        }
    }

    @Override
    public void updateComand(Validator validator, Scanner scanner) throws ValidatorException, UIException {
        try {
            System.out.println("Comand id you want to update: ");
            String comandId = scanner.nextLine();
            int id = validator.intValidator(comandId);

            System.out.println("New date: ");
            String date = scanner.nextLine();
            String data = validator.dataValidator(date);

            System.out.println("Numbers new cakes: ");
            String numberCakes = scanner.nextLine();
            int number = validator.intValidator(numberCakes);
            int[] ids = new int[number];
            for (int i = 0; i < number; i++) {
                System.out.println("Cake id: ");
                String cakeId = scanner.nextLine();
                int id_cake = validator.intValidator(cakeId);
                ids[i] = id_cake;
            }
            ArrayList<Cake> cakes = serviceComand.createCakeList(ids);
            serviceComand.updateComand(id, cakes, data);
        } catch (Exception e) {
            throw new UIException("Error updating comand: " + e);
        }


    }

    @Override
    public void printAllComands() {
        serviceComand.getAllComands().forEach(System.out::println);
    }

    @Override
    public void printNumberOfCakes() {
        serviceComand.getNumberOfCakes().forEach(System.out::println);
    }

    @Override
    public void printNumberOfCakesByEveryMonth() {
        serviceComand.getNumberOfCakesByEveryMonth().forEach(System.out::println);
    }

    @Override
    public void printNumberOfComandsByEveryCake() {
        serviceComand.getNumberOfComandsByEveryCake().forEach(System.out::println);
    }

    @Override
    public void printMenu() {
        System.out.println(" ");
        System.out.println("Menu:");
        System.out.println("Cake:");
        System.out.println("1. Add cake");
        System.out.println("2. Remove cake");
        System.out.println("3. Update cake");
        System.out.println("4. Print all cakes");
        System.out.println(" ");
        System.out.println("Comand:");
        System.out.println("5. Add comand");
        System.out.println("6. Remove comand");
        System.out.println("7. Update comand");
        System.out.println("8. Print all comands");
        System.out.println(" ");
        System.out.println("9. Print number of cakes");
        System.out.println("10. Print number of cakes by every month");
        System.out.println("11. Print number of comands by every cake");
        System.out.println("0. Exit");
        System.out.println(" ");
    }
}
