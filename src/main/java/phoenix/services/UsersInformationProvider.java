package phoenix.services;

import org.springframework.stereotype.Component;
import phoenix.entities.User;

@Component
public class UsersInformationProvider {

    public String userRole(String role) {
        switch(role) {
            case "ROLE_ADMIN":
                role= "isAdmin";
                break;
            case "ROLE_USER":
                role= "isUser";
                break;
        }
        return role;
    }

    public String userStatus(String status) {
        switch(status) {
            case "active":
                status= "isActive";
                break;
            case "blocked":
                status= "isBlocked";
                break;
        }
        return status;
    }

}
