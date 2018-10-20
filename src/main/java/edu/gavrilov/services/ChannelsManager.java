package edu.gavrilov.services;

import edu.gavrilov.rss.Channel;

import java.util.List;

public interface ChannelsManager {

    List<Channel> getChannelsList();

    List<String> getChannelsUrlsList();

    void addChannel(String url);

    void deleteChannel(int channel_id);

}
