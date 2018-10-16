package edu.gavrilov.rss;

public class News {

    private String title;
    private String url;
    private String picture;
    private String description;
    private long time;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public News(String title, String url, String picture, String description, long time) {
        this.title = title;
        this.url = url;
        this.picture = picture;
        this.description = description;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return title;
    }
}
