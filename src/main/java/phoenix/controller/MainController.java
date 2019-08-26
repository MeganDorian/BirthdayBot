package phoenix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import phoenix.services.BirthdayService;
import phoenix.services.TemplateService;

import javax.servlet.http.HttpServletRequest;

@Controller
@ControllerAdvice
public class MainController {

    @GetMapping("/login")
    public String loginPage () {
        return "login";
    }

    @GetMapping("/errorPage")
    public String errorPage () {
        return "errorPages/errorPage";
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    @RequestMapping("/")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "errorPages/default404error";
    }


    @GetMapping("/403")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String accessDeniedPage() {
        return "errorPages/403";
    }

}
