package phoenix.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.validation.constraints.Null;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class BirthdayException{

    private static Logger log = Logger.getLogger(ConstraintViolationException.class.getName());

    public String handleThrownBirthdayExceptions (RuntimeException e) {
        String message = "unhandled exception found";
        Throwable exception = e.getCause();
        if (exception instanceof PersistenceException) {
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
        else if (exception instanceof NumberFormatException) {
            log.log(Level.INFO, "NUMBER FORMAT EXCEPTION IN URL",exception);
            message= "errorPages/400";
        }
        else if (e instanceof NullPointerException) {
            log.log(Level.INFO, "ENTITY NOT FOUND WITH SUCH ID", e.fillInStackTrace());
            message="errorPages/404";
        }
        else
            log.log(Level.INFO, "UNCAUGHT EXCEPTION", e.toString());
        return message;
    }

}
