package webMVC.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import webMVC.models.User;
import webMVC.services.UsersService;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }
    @GetMapping(value = "/{id}")
    public String getAllUsers(@PathVariable int id, ModelMap module, ModelMap user) {
        module.addAttribute("users", usersService.getAllUsers(id));
        user.addAttribute("user", usersService.getUserById(id));
        return "users/users";
    }

    @GetMapping(value = "/user/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));
        return "users/user";
    }

    @PostMapping(value = "/{id}")
    public String chat(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        return "redirect:/inProgress";
    }

    @PatchMapping(value = "/addFriend/{id}/{friendId}")
    public String addNewFriend(@ModelAttribute("user") User user, @PathVariable("id") int id, @PathVariable("friendId") int friendId) {
        System.out.println("adding new friend...");
        usersService.addFriend(id, friendId);
        return "redirect:/personal_page/" + id;
    }

    @DeleteMapping(value = "/delete/{id}/{friendId}")
    public String deleteFriend(@ModelAttribute("user") User user, @PathVariable("id") int id, @PathVariable("friendId") int friendId) {
        System.out.println("deleting friend...");
        System.out.println("my id - " + id + " friend id -" + friendId);
        usersService.deleteFriend(id, friendId);
        return "redirect:/personal_page/" + id;
    }
}
