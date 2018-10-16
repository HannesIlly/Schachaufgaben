package UserInterface;

import java.awt.image.BufferedImage;

/**
 * Represents a BufferedImage with position data, that can be drawn.
 */
public class DisplayableImage {
    int x;
    int y;
    BufferedImage image;

    /**
     * Creates a new DisplayableImage from the given image and position.
     *
     * @param image the image that will be drawn
     * @param x the x position
     * @param y the y position
     */
    public DisplayableImage(BufferedImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the displayableImage.
     *
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Gets the x position of the image.
     *
     * @return the x position
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y position of the image.
     *
     * @return the y position
     */
    public int getY() {
        return y;
    }

}
