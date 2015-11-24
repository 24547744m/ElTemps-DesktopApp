package pojos;


import javafx.scene.image.ImageView;

/**
 * Created by dam on 15/11/15.
 */
public class Item {
    private ImageView image;
    private String dateTime;


    public Item(ImageView image, String dateTime) {
        this.image = image;
        this.dateTime = dateTime;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
