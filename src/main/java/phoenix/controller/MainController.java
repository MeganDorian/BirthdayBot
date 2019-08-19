package phoenix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import phoenix.entities.Birthday;
import phoenix.services.BirthdayService;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Controller
public class MainController {

    @Autowired
    private BirthdayService birthdayService;

    @GetMapping("/login")
    public String loginPage () {
        return "login";
    }

    @GetMapping("/errorPage")
    public String errorPage () {
        return "errorPage";
    }

    @RequestMapping("/bdays")
    public ModelAndView showBirthdaysTable() {
        return new ModelAndView("bdays", "allBdays", birthdayService.selectAll());
    }

    @GetMapping("/add")
    public ModelAndView addNewBirthday() {
        return new ModelAndView("addBirthday");
    }

    @PostMapping ("/add")
    public ModelAndView saveNewBirthdayInDB(@Valid @ModelAttribute("newBirthday") Birthday newBirthday,
                                            BindingResult result,
                                            Model model) {
        if (!result.hasErrors() && !newBirthday.getUserName().equals("") && newBirthday.getDateOfBirth()!=null) {
            birthdayService.insertBirthday(newBirthday);
            return new ModelAndView("bdays", "allBdays", birthdayService.selectAll());
        }
        else if (result.hasFieldErrors())
            model.addAttribute("inputError", "Please input correct values");
        else
            model.addAttribute("inputError", "You need to fill in all fields");
        return new ModelAndView("addBirthday");
    }

}
