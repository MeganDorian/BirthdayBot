package phoenix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import phoenix.entities.TemplateMessage;
import phoenix.services.TemplateService;

import javax.validation.Valid;

@Controller
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/templates")
    public ModelAndView showTemplatesTable() {
        return new ModelAndView("template/templates", "allTemplates", templateService.templateSelectAll());
    }

    @PostMapping("/addTemplate")
    public ModelAndView saveNewTemplateInDB(@RequestParam ("message") String message) {
        templateService.insertNewTemplateInDB(new TemplateMessage(0,message));
        return new ModelAndView("redirect:/templates");
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
    public String showEditTemplate(Model model, @PathVariable int id) {
        TemplateMessage templateMessage = templateService.templateSearchId(id);
        model.addAttribute("template", templateMessage.getTemplate());
        return "template/editTemplate";
    }

    @PostMapping("/editTemplate/{id}")
    public ModelAndView saveTemplateChanges(@Valid @ModelAttribute("saveChanges") TemplateMessage template) {
        templateService.editTemplate(template);
        return new ModelAndView("redirect:/templates");
    }
}
