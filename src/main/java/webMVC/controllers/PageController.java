package webMVC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import webMVC.CRUD.DAO.UserDao;
import webMVC.CRUD.DAO.UserDaoList;

@Controller
@RequestMapping(value = "/personal_page")
public class PageController {
    private UserDao userDao;

    @Autowired
    public PageController(UserDaoList newUserDaoList) {
        this.userDao = newUserDaoList;
    }

    @GetMapping(value = "/{id}")
    public String getUserById(@PathVariable ("id") int id, Model model){
        model.addAttribute("user", userDao.getUserById(id));
        return "pages/personalPage";
    }
}
