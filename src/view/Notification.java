package view;


import java.awt.*;

public class Notification {
    private static final int FONT_SIZE_STANDARD = 18;

    private Font font = new Font("Helvetica", Font.BOLD, FONT_SIZE_STANDARD);
    private Color textColor;
    private String message;

    private long expirationTime;

    /**
     * Creates a new notification.
     *
     * @param message the message of the notification
     * @param timeToLive the time in milliseconds, before the notification expires
     */
    private Notification(String message, long timeToLive) {
        this(message, timeToLive, Color.black);
    }

    /**
     * Creates a new notification.
     *
     * @param message the message of the notification
     * @param timeToLive the time in milliseconds, before the notification expires
     */
    private Notification(String message, long timeToLive, Color textColor) {
        expirationTime = System.currentTimeMillis() + timeToLive;
        this.message = message;
        this.textColor = textColor;
    }

    /**
     * Creates a new notification with the given message and time the notification should exist.
     *
     * @param text the message of the notification
     * @param timeToLive the time in seconds before the notification expires
     * @return the notification
     */
    public static Notification create(String text, long timeToLive) {
        return new Notification(text, timeToLive * 1000);
    }

    /**
     * Creates a new notification with the given message and default expiration time.
     *
     * @param text the message of the notification
     * @return the notification
     */
    public static Notification create(String text) {
        return new Notification(text, 6_000);
    }

    /**
     * Creates a new error notification with the given message and time the notification should exist.
     *
     * @param text the message of the notification
     * @param timeToLive the time in seconds before the notification expires
     * @return the notification
     */
    public static Notification createError(String text, int timeToLive) {
        return new Notification(text, timeToLive * 1000, Color.red);
    }

    /**
     * Creates a new error notification with the given message default expiration time.
     *
     * @param text the message of the notification
     * @return the notification
     */
    public static Notification createError(String text) {
        return new Notification(text, 10_000, Color.red);
    }

    /**
     * Returns, whether the notification is expired.
     *
     * @return if the notification is expired
     */
    public boolean isExpired() {
        return expirationTime < System.currentTimeMillis();
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
     * Gets the font of this notification.
     *
     * @return the font
     */
    public Font getFont() {
        return font;
    }

    /**
     * Gets the message of this notification.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
