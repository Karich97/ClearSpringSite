package webMVC.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import webMVC.CRUD.DAO.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import webMVC.models.User;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

    private final UserDao userDao;

    @Autowired
    public UsersController(@Qualifier("usersService") UserDao userDao) {
        this.userDao = userDao;
    }
    @GetMapping(value = "/{id}")
    public String getAllUsers(@PathVariable int id, ModelMap module, ModelMap user) {
        module.addAttribute("users", userDao.getAllUsers(id));
        user.addAttribute("user", userDao.getUserById(id));
        return "users/users";
    }

    @GetMapping(value = "/user/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDao.getUserById(id));
        return "users/user";
    }

    @PostMapping(value = "/{id}")
    public String chat(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        return "redirect:/inProgress";
    }

    @PatchMapping(value = "/addFriend/{id}/{friendId}")
    public String addNewFriend(@ModelAttribute("user") User user, @PathVariable("id") int id, @PathVariable("friendId") int friendId) {
        System.out.println("adding new friend...");
        userDao.addFriend(id, friendId);
        return "redirect:/personal_page/" + id;
    }

    @DeleteMapping(value = "/delete/{id}/{friendId}")
    public String deleteFriend(@ModelAttribute("user") User user, @PathVariable("id") int id, @PathVariable("friendId") int friendId) {
        System.out.println("deleting friend...");
        System.out.println("my id - " + id + " friend id -" + friendId);
        userDao.deleteFriend(id, friendId);
        return "redirect:/personal_page/" + id;
    }
}
