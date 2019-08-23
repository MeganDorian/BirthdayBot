package phoenix.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class BirthdayException {

    private static Logger log = Logger.getLogger(ConstraintViolationException.class.getName());

    public String handleThrownBirthdayExceptions (RuntimeException e) {
        String message = null;
        if (e.getCause() instanceof PersistenceException) {
            Throwable throwable = e.getCause().getCause();
            if (throwable instanceof ConstraintViolationException) {
                log.log(Level.INFO, "INCORRECT DATE OF BIRTH", throwable);
                message = "Date of birth must be less than current one";
            } else if (throwable instanceof EntityExistsException) {
                log.log(Level.INFO, "ROW WITH SUCH VALUES ALREADY IN DB", throwable);
                message = "The row already exists in  the DB";
            } else if (throwable instanceof DataException) {
                log.log(Level.INFO, "USER'S NAME IS LONGER THAN 100 SYMBOLS", throwable);
                message = "User's name must be less than 100 symbols";
            } else
                log.log(Level.INFO, "UNCAUGHT EXCEPTION", throwable);
        }
        else
            log.log(Level.INFO, e.getCause().toString());
        return message;
    }
}
