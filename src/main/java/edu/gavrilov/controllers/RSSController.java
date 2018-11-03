package edu.gavrilov.controllers;

import com.rometools.rome.io.FeedException;
import edu.gavrilov.entity.rss.*;
import edu.gavrilov.services.rss.*;
import edu.gavrilov.validation.ChannelValidator;
import edu.gavrilov.validation.ChannelWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * Контроллер осноной логики приложения
 * Обрабатывает запросы на отображение списка новостей/каналов
 * Обрабатывает запрос на добавление и удаление каналов
 */

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


    @RequestMapping(value = "/addchannel", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public @ResponseBody String addChannel(@RequestBody ChannelWrapper channelWrapper) {

        String url = channelWrapper.getUrl();

        if (!channelValidator.isValid(url)) {
            return channelValidator.getMessage();
        }

        channelsManager.addChannel(url);
        return "Канал успешно добавлен";

    }

}
