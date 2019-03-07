package common;


public class NotFoundException extends RuntimeException {

    public NotFoundException(int id) {
        super("Could not find resource " + id);
    }
}
