package edu.gavrilov.controllers;

import edu.gavrilov.rss.Channel;
import edu.gavrilov.rss.News;
import edu.gavrilov.rss.NewsManager;
import edu.gavrilov.services.ChannelsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

@Controller
public class RSSController {

    @Autowired
    NewsManager newsManager;

    @Autowired
    ChannelsManager channelsManager;

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


}
