package view;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class TextField {
    private static final int FONT_SIZE_STANDARD = 18;

    private int xPosition;
    private int yPosition;
    private int width;
    private int height;

    private Font font = new Font("Helvetica", Font.PLAIN, FONT_SIZE_STANDARD);
    private String text = "";

    private boolean active;

    private int blinkInterval = 500;
    private long nextCursorChange = System.currentTimeMillis() + blinkInterval;
    private boolean cursorVisible = true;

    /**
     * Creates a new text field with the given text.
     *
     * @param text
     *         the displayed text
     * @param x
     *         the x-coordinate
     * @param y
     *         the y-coordinate
     * @param width
     *         the with of the text field
     * @param height
     *         the height of the text field
     */
    public TextField(String text, int x, int y, int width, int height) {
        this.text = text;
        this.xPosition = x;
        this.yPosition = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a new empty text field.
     *
     * @param x
     *         the x-coordinate
     * @param y
     *         the y-coordinate
     * @param width
     *         the with of the text field
     * @param height
     *         the height of the text field
     */
    public TextField(int x, int y, int width, int height) {
        this.xPosition = x;
        this.yPosition = y;
        this.width = width;
        this.height = height;
    }

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
     * Sets the text of this text field.
     *
     * @param text
     *         the text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the text of this text field.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the text of this text field.
     *
     * @return the text, including the blinking cursor.
     */
    public String showText() {
        if (active) {
            if (System.currentTimeMillis() >= nextCursorChange) {
                cursorVisible = !cursorVisible;
                nextCursorChange += blinkInterval;
            }
            if (cursorVisible) {
                return text + "|";
            }
        }
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
     * Appends a string to the textfield's text.
     *
     * @param s
     *         the string that will be appended
     */
    public void append(String s) {
        text += s;
    }

    /**
     * Appends a single character to the textfield's text.
     *
     * @param c
     *         the character that will be appended
     */
    public void append(char c) {
        text += c;
    }

    /**
     * Shortens the text by removing the last character, if possible.
     */
    public void removeLastChar() {
        if (text.length() != 0) {
            text = text.substring(0, text.length() - 1);
            cursorVisible = true;
            nextCursorChange = System.currentTimeMillis() + blinkInterval;
        }
    }

    /**
     * Copies the text to the system clipboard.
     */
    public void copyToClipboard() {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    /**
     * Returns if the given coordinates are on the text field.
     *
     * @param x
     *         the x-coordinate
     * @param y
     *         the y-coordinate
     * @return if the coordinates are within the boundaries of this text field
     */
    public boolean isOnTextField(int x, int y) {
        boolean onFieldX = xPosition <= x && x <= xPosition + width;
        boolean onFieldY = yPosition <= y && y <= yPosition + height;
        return onFieldX && onFieldY;
    }

    /**
     * Returns if this text field is active.
     *
     * @return if this text field is active or not
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active state to the given value.
     *
     * @param active
     *         if the text field is active
     */
    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            cursorVisible = true;
            nextCursorChange = System.currentTimeMillis() + blinkInterval;
        }
    }
}
