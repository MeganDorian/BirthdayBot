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

//    @Autowired
//    private BirthdayService birthdayService;

//    @Autowired
//    private TemplateService templateService;

    @GetMapping("/login")
    public String loginPage () {
        return "login";
    }

    @GetMapping("/errorPage")
    public String errorPage () {
        return "errorPage";
    }

//    @RequestMapping("/bdays")
//    public ModelAndView showBirthdaysTable() {
//        return new ModelAndView("birthday/bdays", "allBdays", birthdayService.selectAll());
//    }
//
//    @GetMapping("/add")
//    public ModelAndView addNewBirthday() {
//        return new ModelAndView("birthday/addBirthday");
//    }
//
//    @PostMapping ("/add")
//    public ModelAndView saveNewBirthdayInDB(@Valid @ModelAttribute("newBirthday") Birthday newBirthday,
//                                            BindingResult result,
//                                            Model model, NewBirthdayCheck check) {
//        if (!result.hasErrors() && check.isNullCheck(newBirthday)) {
//            try {
//                birthdayService.insertBirthday(newBirthday);
//                return new ModelAndView("birthday/bdays", "allBdays", birthdayService.selectAll());
//            } catch (RuntimeException e) {
//                Throwable throwable = Throwables.getRootCause(e);
//                if (throwable instanceof PSQLException) {
//                    model.addAttribute("inputError", "Date of birth must be less than current one");
//                    return new ModelAndView("birthday/addBirthday");
//                }
//            }
//        }
//        else if (result.hasFieldErrors())
//            model.addAttribute("inputError", "Please input correct values");
//        else
//            model.addAttribute("inputError", "You need to fill in all fields");
//        return new ModelAndView("birthday/addBirthday");
//    }

//    @GetMapping("/templates")
//    public ModelAndView showTemplatesTable() {
//        return new ModelAndView("template/templates", "allTemplates", templateService.templateSelectAll());
//    }
//
//    @PostMapping("/addTemplate")
//    public ModelAndView saveNewTemplateInDB() {
//        return new ModelAndView("template/templates", "allTemplates", templateService.templateSelectAll());
//    }
}
