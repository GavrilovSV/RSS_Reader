package edu.gavrilov.rss;

public class Channel {

    private static int id_gen = 0;

    private int id = id_gen++;

    private String link;
    private String title;
    private String description;
    private String icon;

    public Channel(String icon, String link, String title, String description) {
        this.icon = icon;
        this.link = link;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
