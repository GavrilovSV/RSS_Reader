package edu.gavrilov.controllers;

import com.rometools.rome.feed.synd.SyndEntry;
import edu.gavrilov.rss.News;
import edu.gavrilov.rss.NewsManager;
import edu.gavrilov.rss.SettingsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class RSSController {

    @GetMapping("/")
    public String main(Model model) throws IOException {

        SettingsManager settingsManager = new SettingsManager();
        List urls = settingsManager.getURLs();
        NewsManager newsManager = new NewsManager();
        List<News> news = newsManager.listNews(urls);
        model.addAttribute("news", news);
        return "index";

    }

}
