package edu.gavrilov.controllers;

import edu.gavrilov.repositories.security.UserDao;
import edu.gavrilov.services.security.UserService;
import edu.gavrilov.validation.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SecurityController {

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(Model model, String error) {

        if (error != null)
            model.addAttribute("error", "Неверный email или пароль");

        return "login";
    }

    @GetMapping("/logout")
    public String logout() {

        return "redirect:/login?logout";

    }

    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("registerForm", new RegisterForm());
        return "register";

    }

    @PostMapping("/register")
    public String registerUserAccount(@Valid @ModelAttribute("registerForm") RegisterForm registerForm,
                                      BindingResult bindingResult,
                                      HttpServletRequest request,
                                      Model model) {

        if (!registerForm.getPassword().equals(registerForm.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "passwords.mismatch", "Пароли не совпадают");
        }

        if (userDao.getUserIdByUsername(registerForm.getUsername()) != 0) {
            bindingResult.rejectValue("username", "email.alreadyused", "Такой email уже зарегистрирован");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userDao.createNewAccount(registerForm.getUsername(), passwordEncoder.encode(registerForm.getPassword()));

        userService.autoLogin(registerForm.getUsername(), passwordEncoder.encode(registerForm.getPassword()));

        return "redirect:/";

    }



}
