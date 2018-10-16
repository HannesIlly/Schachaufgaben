package UserInterface;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;

/**
 * An image that can be clicked and used as a button.
 */
public class ImageButton {
    private String title;
    private DisplayableImage image;
    // private List<Consumer<>> todo listener?

    /**
     * Creates a new ImageButton with the given text and {@link DisplayableImage}.
     *
     * @param title the title of the ImageButton
     * @param image the image of the ImageButton
     */
    public ImageButton(String title, DisplayableImage image) {
        this.title = title;
        this.image = image;
    }

    /**
     * Creates a new ImageButton with the given {@link DisplayableImage}.
     *
     * @param image the image of the button
     */
    public ImageButton(DisplayableImage image) {
        this.image = image;
    }
}
