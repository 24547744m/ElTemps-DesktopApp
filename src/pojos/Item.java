package pojos;

import javax.swing.text.html.ImageView;

/**
 * Created by dam on 15/11/15.
 */
public class Item {
    private ImageView image;
    private Time time;


    public Item(ImageView image, Time time) {
        this.image = image;
        this.time = time;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
