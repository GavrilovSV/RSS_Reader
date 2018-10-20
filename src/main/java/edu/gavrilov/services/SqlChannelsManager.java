package edu.gavrilov.services;

import edu.gavrilov.rss.Channel;

import java.util.ArrayList;
import java.util.List;

public class SqlChannelsManager implements ChannelsManager {

    @Override
    public List<Channel> getChannelsList() {
        List<Channel> channels = new ArrayList<>();
        return channels;
    }

    @Override
    public void addChannel(String url) {

    }

    @Override
    public void deleteChannel(int channel_id) {

    }
}
