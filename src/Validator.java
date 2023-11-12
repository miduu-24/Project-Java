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
}
