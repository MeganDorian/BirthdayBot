package phoenix.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Component;
import phoenix.services.TemplateService;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TemplateException {
    private static Logger log = Logger.getLogger(TemplateService.class.getName());

    public String handleThrownTemplateExceptions(RuntimeException e) {
        String message=null;
        if (e.getCause() instanceof PersistenceException) {
            Throwable throwable = e.getCause().getCause();
            if (throwable instanceof DataException) {
                log.log(Level.INFO, "TEMPLATE MESSAGE IS LONGER THAN 200 SYMBOLS", throwable);
                message = "Template must be less than 200 symbols";
            } else
                log.log(Level.INFO, "UNCAUGHT EXCEPTION", throwable);
        }
        else
            log.log(Level.INFO, e.getCause().toString());
        return message;
    }
}
