package edu.gavrilov.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.rometools.rome.io.FeedException;
import edu.gavrilov.entity.rss.*;
import edu.gavrilov.services.rss.*;
import edu.gavrilov.validation.ChannelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@Controller
public class RSSController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    NewsManager newsManager;

    @Autowired
    ChannelsManager channelsManager;

    @Autowired
    ChannelValidator channelValidator;

    @GetMapping("/")
    public String main(Model model) {

        List<News> news = null;

        try {
            news = newsManager.listNews();
        } catch (FeedException | IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("news", news);
        return "index";

    }

    @GetMapping("/mychannels")
    public String myChannels(Model model) {

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
    public String addChannel(@RequestParam("newUrl") String url,
                             Model model) {

        if (!channelValidator.isValid(url)) {
            model.addAttribute("error", channelValidator.getMessage());
            System.out.println(channelValidator.getMessage());
            return "redirect:/mychannels";
        }

        channelsManager.addChannel(url);
        return "redirect:/mychannels";

    }



}
