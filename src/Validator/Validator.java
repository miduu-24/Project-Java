package Validator;

public class Validator {
    public int intValidator(String id) throws ValidatorException {
        if (id == null) {
            throw new ValidatorException("Id cannot be null");
        }
        if (id.equals("")) {
            throw new ValidatorException("Id cannot be empty");
        }
        if (Integer.parseInt(id) < 0) {
            throw new ValidatorException("Id cannot be negative");
        }
        return Integer.parseInt(id);
    }

    public String dataValidator(String data) throws ValidatorException {
        if (data == null) {
            throw new ValidatorException("Data cannot be null");
        }
        if (data.equals("")) {
            throw new ValidatorException("Data cannot be empty");
        }
        String[] date = data.split("\\.");
        if (date.length != 3) {
            throw new ValidatorException("Data must be in the format dd.mm.yyyy");
        }
        return data;
    }
}
