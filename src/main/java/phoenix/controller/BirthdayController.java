package phoenix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import phoenix.entities.Birthday;
import phoenix.exceptions.BirthdayException;
import phoenix.services.BirthdayService;
import phoenix.services.NewBirthdayCheck;

import javax.validation.Valid;

@Controller
public class BirthdayController {

    @Autowired
    private BirthdayService birthdayService;

    @Autowired
    private BirthdayException exceptions;

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
                return new ModelAndView("redirect:/bdays");
            } catch (RuntimeException e) {
                String errorMessage=exceptions.handleThrownBirthdayExceptions(e);
                model.addAttribute("inputError", errorMessage);
                    return new ModelAndView("birthday/addBirthday");
//                }
            }
        }
        else if (result.hasFieldErrors())
            model.addAttribute("inputError", "Please input correct values");
        else
            model.addAttribute("inputError", "You need to fill in all fields");
        return new ModelAndView("birthday/addBirthday");
    }

    //save changes
    @PostMapping("/editBirthday/{id}")
    public ModelAndView editBirthdayTable(@Valid @ModelAttribute("saveChanges") Birthday birthday,
                                          BindingResult result,
                                          Model model, @PathVariable int id) {
        if (!result.hasErrors()) {
            try {
                birthdayService.editBirthday(birthday);
            }catch (RuntimeException e) {
                String errorMessage=exceptions.handleThrownBirthdayExceptions(e);
                model.addAttribute("error", errorMessage);
                model.addAttribute("id", id);
                model.addAttribute("userName", birthday.getUserName());
                model.addAttribute("dateOfBirth", birthday.getDateOfBirth());
                return new ModelAndView("birthday/editBirthday");
            }
        }
        return new ModelAndView("redirect:/bdays");
    }

    //set old values
    @GetMapping("/editBirthday/{id}")
    public String editBirthdayPage(@PathVariable("id") int id, Model model) {
        Birthday birthday=birthdayService.selectById(id);
        model.addAttribute("userName", birthday.getUserName());
        model.addAttribute("dateOfBirth", birthday.getDateOfBirth());
        return "birthday/editBirthday";
    }


    //delete from table
    @GetMapping("/bdays/{id}")
    public ModelAndView deleteRowFromBirthdayTable(@PathVariable int id) {
        birthdayService.deleteBirthday(id);
        return new ModelAndView("redirect:/bdays");
    }
}
