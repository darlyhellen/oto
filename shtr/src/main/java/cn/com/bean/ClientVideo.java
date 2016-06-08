package cn.com.bean;

public class ClientVideo {
    private Integer id;

    private String videoName;

    private String videoUrl;

    private String videoImage;

    private String videoDescripe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName == null ? null : videoName.trim();
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public String getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(String videoImage) {
        this.videoImage = videoImage == null ? null : videoImage.trim();
    }

    public String getVideoDescripe() {
        return videoDescripe;
    }

    public void setVideoDescripe(String videoDescripe) {
        this.videoDescripe = videoDescripe == null ? null : videoDescripe.trim();
    }
}