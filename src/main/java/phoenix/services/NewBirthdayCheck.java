package phoenix.services;

import org.springframework.stereotype.Component;
import phoenix.entities.Birthday;

@Component
public class NewBirthdayCheck {

    public boolean isNullCheck (Birthday birthday) {
        return (!birthday.getUserName().equals("") && birthday.getDateOfBirth()!=null);
    }
}
