package uz.es.company.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String userNotFound) {
        super(userNotFound);
    }
}
