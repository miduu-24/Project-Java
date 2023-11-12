package Service;

import Repository.CakeRepository;
import Repository.ComandRepository;

public abstract class AbstractService {
    CakeRepository cakeRepository = new CakeRepository();
    ComandRepository comandRepository = new ComandRepository();

}
