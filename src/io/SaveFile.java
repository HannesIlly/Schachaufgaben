package io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * This class is used to read and write files.
 *
 * @author Hannes Illy
 * @version 1.0
 */
public class SaveFile {
    private static final String SAVE_PATH = "./";

    private String name;
    private File file;

    private Properties properties = new Properties();

    /**
     * Creates a SaveFile object that represents a file on the computer and will create the file or
     * the directory, if they don't exist.
     *
     * @param name
     *         The name of the save file. The file ending (.saps), if not already present, will be added.
     * @throws IOException
     *         if an io-error occurs
     */
    public SaveFile(String name) throws IOException {
        this.name = name;
        if (name.endsWith(".saps")) {
            this.file = new File(SAVE_PATH + name);
        } else {
            this.file = new File(SAVE_PATH + name + ".saps");
        }

        if (!file.exists()) {
            file.mkdirs();
            file.createNewFile();
        }

        readProperties();
    }

    /**
     * Returns all file names of SaveFiles in the save directory.
     *
     * @return the array of all file names (without ending)
     */
    public static String[] getSaveFileNames(){
        String[] fileNames = new File(SAVE_PATH).list((dir, name1) -> name1.endsWith(".saps"));
        for (int i = 0; i < fileNames.length; i++) {
            if (fileNames[i].length() > 5) {
                fileNames[i] = fileNames[i].substring(0, fileNames[i].length() - 5);
            }
        }
        return fileNames;
    }

    /**
     * Writes the properties object to the file.
     *
     * @throws IOException if an io-error occurs
     */
    public void writeProperties() throws IOException {
        properties.store(new FileWriter(file), null);
    }

    /**
     * Reads the properties from the file.
     *
     * @throws IOException if an io-error occurs
     */
    public void readProperties() throws IOException {
        properties.load(new FileReader(file));
    }

    /**
     * Gets the name of this file (without file ending).
     *
     * @return the name of the file
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the property value of the key.
     *
     * @param key the key
     * @return the value
     * @throws IllegalArgumentException if the key is null or an empty string
     */
    public String get(String key) throws IllegalArgumentException {
        if (key == null ||key.length() == 0) {
            throw new IllegalArgumentException("Key muss vorhanden sein!");
        }
        return properties.getProperty(key);
    }

    /**
     * Checks if a property aleady exists.
     *
     * @param key the key
     * @return if the key already exists
     * @throws IllegalArgumentException if the key is null or an empty string
     */
    public boolean exists(String key) throws IllegalArgumentException {
        if (key == null ||key.length() == 0) {
            throw new IllegalArgumentException("Name muss vorhanden sein!");
        }
        return properties.getProperty(key) != null;
    }

    /**
     * Sets the property value of the key to the value.
     *
     * @param key the key
     * @return the value
     * @throws IllegalArgumentException if the key is null or an empty string
     */
    public void set(String key, String value) throws IllegalArgumentException {
        if (key == null ||key.length() == 0) {
            throw new IllegalArgumentException("Name muss vorhanden sein!");
        }
        properties.setProperty(key, value);
    }

    /**
     * Gets a set of all keys of the save file.
     *
     * @return the set containing all keys
     */
    public Set<String> getKeys() {
        return properties.stringPropertyNames();
    }

    /**
     * Reads an image from the given relative resource path.
     *
     * @param path
     *         the relative path of the image
     * @return the BufferedImage
     * @throws IOException
     *         if the image cannot be read
     */
    public static BufferedImage getImage(String path) throws IOException {
        return ImageIO.read(new File("resources/" + path));
    }

    /**
     * Writes an image with the given name (as .png).
     *
     * @param name
     *         the name of the image
     * @param image
     *         the BufferedImage
     * @return if a writer could be created
     * @throws IOException
     *         if the image cannot be written
     */
    public static boolean writeImage(String name, BufferedImage image) throws IOException {
        return ImageIO.write(image, "png", new File(name + ".png"));
    }
}
