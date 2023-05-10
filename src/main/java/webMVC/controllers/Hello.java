package webMVC.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import webMVC.models.User;
import webMVC.services.UsersService;

@Controller
public class Hello {
    private final UsersService usersService;
    @Autowired
    public Hello(UsersService usersService) {
        this.usersService = usersService;
    }
    @GetMapping(value = "/")
    public String helloPage(@ModelAttribute("user") User user){
        return "hello/hello";
    }
    @GetMapping(value = "/inProgress")
    public String inProgress(){
        return "inProgress";
    }
    @GetMapping(value = "/new")
    public String newUser(@ModelAttribute("user") User user){
        return "hello/newUserPage";
    }
    @PostMapping(value = "/new")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:/new";
        }
        usersService.addNewUser(user);
        return "redirect:/";
    }
    @PostMapping(value = "/")
    public String login(@ModelAttribute("user") User user){
        int id = usersService.findUserByLoginAndPassword(user.getLogin(), user.getPassword());
        if (id == 0){
            return "redirect:/";
        }
        else return "redirect:/personal_page/" + id;
    }
}
