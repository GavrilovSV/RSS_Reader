package edu.gavrilov.validation;

import com.rometools.rome.io.FeedException;
import edu.gavrilov.repositories.rss.RssDao;
import edu.gavrilov.repositories.security.UserDao;
import edu.gavrilov.services.rss.NewsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class ChannelValidator {

    @Autowired
    UserDao userDao;

    @Autowired
    RssDao rssDao;

    @Autowired
    NewsManager newsManager;

    private String message;

    public String getMessage() {
        return message;
    }

    public boolean isValid(String url) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        int user_id = userDao.getUserIdByUsername(currentPrincipalName);

        int channel_id = rssDao.getChannelIdByUrl(url);

        if ((channel_id != 0) && (rssDao.currentUserIsSignedToChannel(user_id, url))) {
                message = "Вы уже подписаны на этот канал";
            return false;
        }

        try {
            newsManager.listNews();
        } catch (IOException | FeedException e) {
            message = "Ссылка не является валидным RSS-каналом";
            return false;
        }

        return true;

    }
}
