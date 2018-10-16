package edu.gavrilov.rss;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsManager {

    private String filename = "RSS_URLs.txt";

    public List getURLs() throws IOException {

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

    public void createFile() throws IOException {

        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }

    }

    public void addChannel(String url) throws IOException {

        FileWriter fileWriter = new FileWriter(filename, true);
        createFile();

        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.append(url);
        writer.newLine();
        writer.flush();
        writer.close();

    }

    public void deleteChannel(String channelToRemove) throws IOException {

        File channel = new File(filename);
        File file = new File(channel.getAbsolutePath() + ".tmp");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(channel));
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            if (!line.trim().equals(channelToRemove)) {
                writer.println(line);
                writer.flush();
            }
        }
        writer.close();
        bufferedReader.close();
        channel.delete();
        file.renameTo(channel);


    }




}
