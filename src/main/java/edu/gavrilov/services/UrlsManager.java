package edu.gavrilov.services;

import java.io.IOException;
import java.util.List;

public interface UrlsManager {

    List<String> getUrlsList() throws IOException;

}
