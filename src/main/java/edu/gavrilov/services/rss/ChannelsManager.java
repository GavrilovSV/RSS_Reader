package edu.gavrilov.services.rss;

import edu.gavrilov.entity.rss.Channel;

import java.util.List;

public interface ChannelsManager {

    List<Channel> getChannelsList();

    List<String> getChannelsUrlsList();

    void addChannel(String url);

    void deleteChannel(int channel_id);

    Channel getChannelById(int channel_id);

}
