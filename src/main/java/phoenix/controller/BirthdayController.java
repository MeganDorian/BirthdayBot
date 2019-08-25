package phoenix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import phoenix.entities.Birthday;
import phoenix.exceptions.BirthdayException;
import phoenix.services.BirthdayService;

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
                                            Model model) {
        if (!result.hasErrors()) {
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
                exceptions.handleThrownBirthdayExceptions(e);
//                model.addAttribute("error", errorMessage);
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
    public String editBirthdayPage(@PathVariable("id") int id, Model model){
            Birthday birthday = birthdayService.selectById(id);
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

    @ExceptionHandler({NullPointerException.class, RuntimeException.class})
    public String errorPage(RuntimeException e, Model model) {
        model.addAttribute("page", "/bdays");
        return exceptions.handleThrownBirthdayExceptions(e);
    }


//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public String errorPagesMustBeThrown(@PathVariable(required = false) int id,
//                                         RuntimeException e, Model model) {
//        model.addAttribute("id", id);
//        return exceptions.handleThrownBirthdayExceptions(e);
//    }
//
//    @RequestMapping("/editBirthday/{id}")
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public String notFound(@PathVariable (required = false) int id, Model model, RuntimeException e) {
//        exceptions.handleThrownBirthdayExceptions(e);
//        model.addAttribute("id", id);
//        return "errorPages/404";
//    }
//
//    @RequestMapping("/editBirthday/{id}")
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public String internalError(@PathVariable (required = false) int id, Model model, RuntimeException e) {
//        exceptions.handleThrownBirthdayExceptions(e);
//        return "errorPages/500";
//    }
}


