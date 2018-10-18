package edu.gavrilov.controllers;

import com.rometools.rome.feed.synd.SyndEntry;
import edu.gavrilov.rss.Channel;
import edu.gavrilov.rss.News;
import edu.gavrilov.rss.NewsManager;
import edu.gavrilov.rss.SettingsManager;
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
    SettingsManager settingsManager;

    @GetMapping("/")
    public String main(Model model) throws IOException {

       // SettingsManager settingsManager = new SettingsManager();
        List urls = settingsManager.getURLs();
       // NewsManager newsManager = new NewsManager();
        List<News> news = newsManager.listNews(urls);
        model.addAttribute("news", news);
        return "index";

    }

    @GetMapping("/mychannels")
    public String myChannels(Model model) throws IOException {

     //   SettingsManager settingsManager = new SettingsManager();
        List urls = settingsManager.getURLs();
      //  NewsManager newsManager = new NewsManager();
        List<Channel> channels = newsManager.getChannelList(urls);
        model.addAttribute("channels", channels);
        return "mychannels";

    }

    @GetMapping("/delete/{channel_id}")
    public String deleteChannel(@PathVariable long channel_id, Model model) {



        return "redirect:/mychannels";
    }


}
