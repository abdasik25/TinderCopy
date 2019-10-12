/**
 * Created by Alexander Lomat
 */


package by.bsuir.tinder.pojo;

/**
 * The type Tinder image.
 */
public class TinderImage {

    private String title;
    private String imageUrl;
    private float distance;

    /**
     * Instantiates a new Tinder image.
     *
     * @param title    the title
     * @param imageUrl the image url
     * @param distance the distance
     */
    public TinderImage(String title, String imageUrl, float distance) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.distance = distance;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets image url.
     *
     * @param imageUrl the image url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets distance.
     *
     * @return the distance
     */
    public float getDistance() {
        return distance;
    }

    /**
     * Sets distance.
     *
     * @param distance the distance
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }
}
