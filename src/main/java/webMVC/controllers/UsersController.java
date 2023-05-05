package webMVC.controllers;

import webMVC.CRUD.DAO.UserDao;
import webMVC.CRUD.DAO.UserDaoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

    private UserDao userDao;

    @Autowired
    public UsersController(UserDaoList newUserDaoList) {
        this.userDao = newUserDaoList;
    }

    @GetMapping()
    public String getAllUsers(ModelMap module){
        module.addAttribute("users", userDao.getAllUsers());
        return "users/users";
    }
    @GetMapping(value = "/{id}")
    public String getUserById(@PathVariable ("id") int id, Model model){
        model.addAttribute("user", userDao.getUserById(id));
        return "users/user";
    }
}
