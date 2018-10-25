package edu.gavrilov.controllers;

import edu.gavrilov.rss.Channel;
import edu.gavrilov.rss.News;
import edu.gavrilov.rss.NewsManager;
import edu.gavrilov.security.dao.UserDao;
import edu.gavrilov.services.ChannelsManager;
import edu.gavrilov.validation.LoginForm;
import edu.gavrilov.validation.RegisterForm;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Controller
public class RSSController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    NewsManager newsManager;

    @Autowired
    ChannelsManager channelsManager;

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/")
    public String main(Model model) throws IOException {

        List<News> news = newsManager.listNews();
        model.addAttribute("news", news);
        return "index";

    }


    @GetMapping("/mychannels")
    public String myChannels(Model model) throws IOException {

        List<Channel> channels = channelsManager.getChannelsList();
        model.addAttribute("channels", channels);
        return "mychannels";

    }

    @GetMapping("/delete/{channel_id}")
    public String deleteChannel(@PathVariable int channel_id, Model model) {

        channelsManager.deleteChannel(channel_id);
        return "redirect:/mychannels";

    }


    @PostMapping("/addchannel")
    public String addChannel(@RequestParam("newUrl") String url, Model model) {

        System.out.println( "here");
        channelsManager.addChannel(url);
        return "redirect:/mychannels";

    }

    @GetMapping("/login")
    public String login(Model model, String error) {

        if (error != null)
            model.addAttribute("error", "Неверный email или пароль");

        return "login";
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

        UserDetails userDetails = userDetailsService.loadUserByUsername(registerForm.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,
                                                        passwordEncoder.encode(registerForm.getPassword()),
                                                        userDetails.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        return "redirect:/";

    }


}
