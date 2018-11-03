package edu.gavrilov.services.rss;

import edu.gavrilov.entity.rss.Channel;

import java.util.List;

/**
 * Интерфейс для управленя списком каналов
 */
public interface ChannelsManager {

    /**
     * Метод для получения списка объектов класса "Канал"
     * @return список каналов
     */
    List<Channel> getChannelsList();

    /**
     * Метод для получения списка ссылок на каналы
     * @return список ссылок
     */
    List<String> getChannelsUrlsList();

    /**
     * Метод для добавления канала
     * @param url - ссылка на канал
     */
    void addChannel(String url);

    /**
     * Метод для удаления канала
     * @param channel_id - идентификатор канала
     */
    void deleteChannel(int channel_id);

    /**
     * Метод для получения канала по идентификатору
     * @param channel_id - уникальный идентификатор канала
     * @return Channel object
     */
    Channel getChannelById(int channel_id);

}
