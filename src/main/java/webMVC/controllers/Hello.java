package webMVC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import webMVC.CRUD.DAO.UserDao;
import webMVC.CRUD.DAO.UserDaoList;
import webMVC.models.User;

@Controller
public class Hello {
    private final UserDao userDao;

    @Autowired
    public Hello(UserDaoList newUserDaoList) {
        this.userDao = newUserDaoList;
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
    public String create(@ModelAttribute("user") User user){
        if (user.getAge() == 0 || user.getPassword().equals("") || user.getName().equals("") || user.getLogin().equals("")){
            return "redirect:/new";
        }
        userDao.addNewUser(user);
        return "redirect:/";
    }
    @PostMapping(value = "/")
    public String login(@ModelAttribute("user") User user){
        int id = userDao.findUserByLoginAndPassword(user.getLogin(), user.getPassword());
        if (id == 0){
            return "redirect:/";
        }
        else return "redirect:/personal_page/" + id;
    }
}
