package phoenix.controller;

import com.google.common.base.Throwables;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import phoenix.entities.Birthday;
import phoenix.services.BirthdayService;
import phoenix.services.NewBirthdayCheck;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BirthdayController {

    @Autowired
    private BirthdayService birthdayService;

    @RequestMapping("/bdays")
    public ModelAndView showBirthdaysTable() {
        return new ModelAndView("birthday/bdays", "allBdays", birthdayService.selectAll());
    }

    @GetMapping("/add")
    public ModelAndView addNewBirthday() {
        return new ModelAndView("birthday/addBirthday");
    }

    @PostMapping("/add")
    public ModelAndView saveNewBirthdayInDB(@Valid @ModelAttribute("newBirthday") Birthday newBirthday,
                                            BindingResult result,
                                            Model model, NewBirthdayCheck check) {
        if (!result.hasErrors() && check.isNullCheck(newBirthday)) {
            try {
                birthdayService.insertBirthday(newBirthday);
                return new ModelAndView("birthday/bdays", "allBdays", birthdayService.selectAll());
            } catch (RuntimeException e) {
                Throwable throwable = Throwables.getRootCause(e);
                if (throwable instanceof PSQLException) {
                    model.addAttribute("inputError", "Date of birth must be less than current one");
                    return new ModelAndView("birthday/addBirthday");
                }
            }
        }
        else if (result.hasFieldErrors())
            model.addAttribute("inputError", "Please input correct values");
        else
            model.addAttribute("inputError", "You need to fill in all fields");
        return new ModelAndView("birthday/addBirthday");
    }

    @PostMapping("/editBirthday")
    public String editBirthdayTable(Model model, @RequestBody Birthday birthday) {
        model.addAttribute("userName", birthday.getUserName());
        model.addAttribute("dateOfBirth", birthday.getDateOfBirth());
        return "editBirthday";
    }

    @GetMapping("/bdays/{id}")
    public ModelAndView deleteRowFromBirthdayTable(@PathVariable int id) {
        birthdayService.deleteBirthday(id);
        return new ModelAndView("redirect:/bdays", "allBdays", birthdayService.selectAll());
    }
}
