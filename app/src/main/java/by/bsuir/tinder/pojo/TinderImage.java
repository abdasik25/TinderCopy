package by.bsuir.tinder.pojo;

public class TinderImage {

    private String title;
    private String imageUrl;
    private float distance;

    public TinderImage(String title, String imageUrl, float distance) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.distance = distance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
