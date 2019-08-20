package phoenix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import phoenix.services.TemplateService;

@Controller
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/templates")
    public ModelAndView showTemplatesTable() {
        return new ModelAndView("template/templates", "allTemplates", templateService.templateSelectAll());
    }

    @PostMapping("/addTemplate")
    public ModelAndView saveNewTemplateInDB() {
        return new ModelAndView("template/templates", "allTemplates", templateService.templateSelectAll());
    }
}
