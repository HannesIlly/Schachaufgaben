package io;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * 
 * this class is used to read and write files
 * 
 * @author Hannes Illy
 * @version 1.0
 *
 */
public class SaveFile {
    private String path;
    private File file;

    /**
     * creates a SaveFile object that represents a file on the computer and will create the file or the directory too,
     * if they don't exist
     * 
     * @param path
     *            the path of the save file
     */
    public SaveFile(String path) {
        this.path = path;
        file = new File(this.path);

        if (!file.exists()) {
            try {
                file.mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * writes a list of strings into a file. Each string (list cell content) will be written into a separate line
     * 
     * @param list
     *            the list, that will be written in the file
     */
    public void write(List<String> list) {
        try {
            FileWriter fileWriter = new FileWriter(file); // create file- and print writer
            PrintWriter out = new PrintWriter(fileWriter);

            Iterator<String> iterator = list.iterator(); // create new iterator (so the cursor is at the start of the list)
            while (iterator.hasNext()) { // while there is a next list cell
                out.println(iterator.next()); // write it in the file
            }

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads a file and returns its content in a list, in which each list cell contains a line of the file
     * 
     * @return the content of the file as a list of strings
     */
    public List<String> read() {
        List<String> list = new LinkedList<String>();

        try {
            FileReader fileReader = new FileReader(file); // create file- and buffered reader
            BufferedReader in = new BufferedReader(fileReader);

            list.add(in.readLine()); // add every new line at the end of the list

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Reads an image from the given relative resource path.
     *
     * @param path the relative path of the image
     * @return the BufferedImage
     * @throws IOException if the image cannot be read
     */
    public static BufferedImage getImage(String path) throws IOException {
        return ImageIO.read(new File("resources/" + path));
    }
}
