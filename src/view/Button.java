package view;


import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A button that can display a text, an image or both. This is a passive implementation, that can be
 * asked, whether given coordinates are within the border of this button, but does't do anythin on
 * its own.
 */
public class Button {
    private static final int FONT_SIZE_STANDARD = 18;

    private String text;
    private Font font = new Font("Helvetica", Font.BOLD, FONT_SIZE_STANDARD);
    private Color textColor = Color.black;
    private Color backgroundColor = Color.lightGray;

    private BufferedImage image;

    private int xPosition;
    private int yPosition;
    private int width;
    private int height;

    /**
     * Creates a new button with the given text and image.
     *
     * @param text the displayed text
     * @param image the displayed image
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the with of the button
     * @param height the height of the button
     */
    public Button(String text, BufferedImage image, int x, int y, int width, int height) {
        this.text = text;
        this.image = image;

        this.xPosition = x;
        this.yPosition = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a new button with the given text and image.
     *
     * @param image the displayed image
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the with of the button
     * @param height the height of the button
     */
    public Button(BufferedImage image, int x, int y, int width, int height) {
        this.image = image;

        this.xPosition = x;
        this.yPosition = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a new button with the given text and image.
     *
     * @param text the displayed text
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the with of the button
     * @param height the height of the button
     */
    public Button(String text, int x, int y, int width, int height) {
        this.text = text;

        this.xPosition = x;
        this.yPosition = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the text color.
     *
     * @return the text Color
     */
    public Color getTextColor() {
        return textColor;
    }

    /**
     * Sets the text color.
     *
     * @param textColor the text Color
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    /**
     * Gets the background color.
     *
     * @return the background Color
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the background color.
     *
     * @param backgroundColor the background Color
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Returns if the given coordinates are on the button.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return if the coordinates are within the boundaries of this button
     */
    public boolean isOnButton(int x, int y) {
        boolean onButtonX = xPosition <= x && x <= xPosition + width;
        boolean onButtonY = yPosition <= y && y <= yPosition + height;
        return onButtonX && onButtonY;
    }

    /**
     * Gets the font of this button.
     *
     * @return the font
     */
    public Font getFont() {
        return font;
    }

    /**
     * Gets the x position of this button.
     *
     * @return the x position
     */
    public int getX() {
        return xPosition;
    }

    /**
     * Gets the y position of this button.
     *
     * @return the y position
     */
    public int getY() {
        return yPosition;
    }

    /**
     * Gets the width of this button.
     *
     * @return the with
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of this button.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the text of this button.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }
}
