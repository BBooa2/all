package BoaHancock.GUI.forms;

public class ModelLocation {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    public ModelLocation(String title, String description, String videopath) {
        this.title = title;
        this.description = description;
        this.videopath = videopath;
    }
    private String title;
    private String description;
    private String videopath;
}
