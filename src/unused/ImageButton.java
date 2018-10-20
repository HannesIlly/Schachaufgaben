package unused;

import view.DisplayableImage;

import java.awt.event.ActionListener;
import java.util.List;

/**
 * An image that can be clicked and used as a button.
 */
public class ImageButton {
    private String title;
    private DisplayableImage image;
    private List<ActionListener> listeners;

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

    /**
     * Adds a listener to this button.
     *
     * @param listener the ActionListener
     */
    public void addListener(ActionListener listener) {
        this.listeners.add(listener);
    }

}
