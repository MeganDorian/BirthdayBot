package phoenix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import phoenix.entities.TemplateMessage;
import phoenix.exceptions.TemplateException;
import phoenix.services.TemplateService;

import javax.validation.Valid;

@Controller
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateException exception;

    @GetMapping("/templates")
    public ModelAndView showTemplatesTable() {
        return new ModelAndView("template/templates", "allTemplates", templateService.templateSelectAll());
    }

    @PostMapping("/addTemplate")
    public ModelAndView saveNewTemplateInDB(@RequestParam ("message") String message, Model model) {
        if (message.equals("")) {
            model.addAttribute("error", "Template message is null");
            return new ModelAndView("template/addTemplate");
        }
        else {
            try {
                templateService.insertNewTemplateInDB(new TemplateMessage(0, message));
            } catch (RuntimeException e) {
                String errorMessage = exception.handleThrownTemplateExceptions(e);
                model.addAttribute("error", errorMessage);
                return new ModelAndView("template/addTemplate");
            }
            return new ModelAndView("redirect:/templates");
        }
    }

    @GetMapping("/addTemplate")
    public String newTemplateForm() {
        return "/template/addTemplate";
    }

    @GetMapping("templates/{id}")
    public ModelAndView deleteTemplateFromDb(@PathVariable int id) {
        templateService.deleteTemplate(id);
        return new ModelAndView("redirect:/templates");
    }

    @GetMapping("/editTemplate/{id}")
    public String showEditTemplate(@PathVariable int id, Model model) {
        TemplateMessage templateMessage = templateService.templateSearchId(id);
        model.addAttribute("template", templateMessage.getTemplate());
        return "template/editTemplate";
    }

    @PostMapping("/editTemplate/{id}")
    public ModelAndView saveTemplateChanges(@Valid @ModelAttribute("saveChanges") TemplateMessage template,
                                            Model model, @PathVariable String id) {
        if (template.getTemplate().equals("")) {
            model.addAttribute("error", "Template message is null");
            return new ModelAndView("template/editTemplate");
        }
        else {
            try {
                templateService.editTemplate(template);
            } catch (RuntimeException e) {
                String errorMessage = exception.handleThrownTemplateExceptions(e);
                model.addAttribute("error", errorMessage);
                model.addAttribute("id", id);
                model.addAttribute("template", template.getTemplate());
                return new ModelAndView("template/editTemplate");
            }
            return new ModelAndView("redirect:/templates");
        }
    }

    @ExceptionHandler({NullPointerException.class, RuntimeException.class})
    public String errorPage(RuntimeException e, Model model) {
        model.addAttribute("page", "/templates");
        return exception.handleThrownTemplateExceptions(e);
    }
}
