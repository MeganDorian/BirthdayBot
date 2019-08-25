package phoenix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import phoenix.services.BirthdayService;
import phoenix.services.TemplateService;

@Controller
public class MainController {

    @GetMapping("/login")
    public String loginPage () {
        return "login";
    }

    @GetMapping("/errorPage")
    public String errorPage () {
        return "errorPages/errorPage";
    }

}
