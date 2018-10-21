package edu.gavrilov.controllers;

import edu.gavrilov.entity.User;
import edu.gavrilov.rss.Channel;
import edu.gavrilov.rss.News;
import edu.gavrilov.rss.NewsManager;
import edu.gavrilov.security.dao.UserDao;
import edu.gavrilov.services.ChannelsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class RSSController {

    @Autowired
    NewsManager newsManager;

    @Autowired
    ChannelsManager channelsManager;

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

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

    @PostMapping("/register")
    public String registerUserAccount(@RequestParam("email") String email,
                                      @RequestParam("password") String password,
                                      @RequestParam("confirmPassword") String confirmPassword) {

        if (!password.equals(confirmPassword))
            return "redirect:/login";

        User user = new User();
        user.setLogin(email);
        user.setPassword(password);

        userDao.createNewAccount(email, passwordEncoder.encode(password));

        return "redirect:/";

    }


}
