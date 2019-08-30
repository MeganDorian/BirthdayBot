package phoenix.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@ControllerAdvice
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/errorPage")
    public String error (Model model) {
        model.addAttribute("message", "User not found");
        return "errorPages/errorPage";
    }

    @GetMapping("/blockedUser")
    public String userIsBlocked (Model model) {
        model.addAttribute("message", "User is blocked");
        return "errorPages/errorPage";
    }

    @ExceptionHandler({RuntimeException.class})
    @RequestMapping("/")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String pageNotFound() {
        return "errorPages/default404error";
    }


    @GetMapping("/403")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String accessDenied() {
        return "errorPages/403";
    }

}
