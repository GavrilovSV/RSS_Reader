package edu.gavrilov.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUrlsManager implements UrlsManager {

    private String filename = "RSS_URLs.txt";

    @Override
    public List<String> getUrlsList() throws IOException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(filename);
        List<String> channels = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            channels.add(currentLine);
        }

        reader.close();
        return channels;

    }
}
