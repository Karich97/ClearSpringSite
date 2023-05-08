package webMVC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webMVC.CRUD.DAO.UserDao;
import webMVC.models.User;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/personal_page")
public class PageController {
    private final UserDao userDao;

    @Autowired
    public PageController(@Qualifier("userDaoHibernate") UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping(value = "/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDao.getUserById(id));
        return "pages/personalPage";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDao.getUserById(id));
        return "pages/edit";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) return "redirect:/personal_page/{id}/edit";
        userDao.updateInformation(id, user);
        return "redirect:/personal_page/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDao.delete(id);
        return "redirect:/";
    }
}
