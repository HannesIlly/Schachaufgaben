package view;

public class TextField {
    private int xPosition;
    private int yPosition;
    private int width;
    private int height;

    private String text;

    /**
     * Gets the x position of this text field.
     *
     * @return the x position
     */
    public int getX() {
        return xPosition;
    }

    /**
     * Gets the y position of this text field.
     *
     * @return the y position
     */
    public int getY() {
        return yPosition;
    }

    /**
     * Gets the width of this text field.
     *
     * @return the with
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of this text field.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the text of this text field.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }
}
