package phoenix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import phoenix.entities.Birthday;
import phoenix.entities.User;
import phoenix.services.UserService;
import phoenix.services.UsersInformationProvider;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UsersInformationProvider provider;

    @RequestMapping("/users")
    public ModelAndView showAllUsers() {
        return new ModelAndView("user/users", "users", userService.getAllUsers());
    }

    @GetMapping("/editUser/{id}")
    public ModelAndView startEditingUser(@PathVariable("id") int id, Model model){
        User user = userService.getById(id);
        model.addAttribute(user);

        String role=provider.userRole(user.getRole());
        model.addAttribute(role, "checked");

        String status=provider.userStatus(user.getStatus());
        model.addAttribute(status, "checked");

        return new ModelAndView("user/editUser");
    }

    @GetMapping("/addUser")
    public ModelAndView addNewUser() {
        return new ModelAndView("user/addUser");
    }

    @PostMapping("/addUser")
    public ModelAndView saveNewUser(@Valid @ModelAttribute("newUser") User newUser,
                                    BindingResult result,
                                    Model model) {
        if(!result.hasErrors()){
            userService.insertUser(newUser);
        }
        return new ModelAndView("redirect:/users");
    }
}
