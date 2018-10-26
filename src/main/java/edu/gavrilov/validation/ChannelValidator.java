package edu.gavrilov.validation;

import com.rometools.rome.io.FeedException;
import edu.gavrilov.repositories.rss.RssDao;
import edu.gavrilov.services.rss.NewsManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;

public class ChannelValidator {

    @Autowired
    RssDao rssDao;

    @Autowired
    NewsManager newsManager;

    private String message;

    public String getMessage() {
        return message;
    }

    public boolean isValid(String url) {

        if (rssDao.getChannelIdByUrl(url) != 0) {
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
