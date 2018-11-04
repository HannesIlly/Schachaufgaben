package view;

import java.awt.*;

public class Label {
    private static final int FONT_SIZE_STANDARD = 18;

    private int xPosition;
    private int yPosition;
    private int width;
    private int height;

    private Font font = new Font("Helvetica", Font.BOLD, FONT_SIZE_STANDARD);
    private String text = "";

    /**
     * Creates a new label with the given text.
     *
     * @param text the displayed text
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the with of the label
     * @param height the height of the label
     */
    public Label(String text, int x, int y, int width, int height) {
        this.text = text;
        this.xPosition = x;
        this.yPosition = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a new empty label.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the with of the label
     * @param height the height of the label
     */
    public Label(int x, int y, int width, int height) {
        this.xPosition = x;
        this.yPosition = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the x position of this label.
     *
     * @return the x position
     */
    public int getX() {
        return xPosition;
    }

    /**
     * Gets the y position of this label.
     *
     * @return the y position
     */
    public int getY() {
        return yPosition;
    }

    /**
     * Gets the width of this label.
     *
     * @return the with
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of this label.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the text of this label.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the text of this label.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the font of the text.
     *
     * @return the font
     */
    public Font getFont() {
        return font;
    }

    /**
     * Appends a string to the label's text.
     *
     * @param s the string that will be appended
     */
    public void append(String s) {
        text += s;
    }

    /**
     * Appends a single character to the label's text.
     *
     * @param c the character that will be appended
     */
    public void append(char c) {
        text += c;
    }

    /**
     * Shortens the text by removing the last character, if possible.
     */
    public void removeLastChar() {
        if (text.length() != 0) {
            text = text.substring(0 , text.length() - 1);
        }
    }
}
