package webMVC.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webMVC.models.User;
import webMVC.services.UsersService;

@Controller
@RequestMapping(value = "/personal_page")
public class PageController {
    private final UsersService usersService;

    @Autowired
    public PageController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(value = "/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));
        return "pages/personalPage";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));
        return "pages/edit";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute("user")  @Valid User user, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) return "redirect:/personal_page/{id}/edit";
        usersService.updateInformation(id, user);
        return "redirect:/personal_page/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        usersService.delete(id);
        return "redirect:/";
    }
}
