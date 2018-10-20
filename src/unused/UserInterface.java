package unused;

import io.SaveFile;
import view.DisplayableImage;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * A class to display info and stuff.
 * 
 * @author Hannes Illy
 * @version 1
 */
@SuppressWarnings("serial")
public class UserInterface extends Frame {
    private Graphics2D g;
    private BufferedImage image;
    String name;

    BufferedImage background;
    List<DisplayableImage> images = new ArrayList();
    
    /**
     * Creates a new Frame with the given name, and size.
     * @param name the name of the frame
     * @param sizeX the horizontal size of the frame
     * @param sizeY the vertical size of the frame
     */
    public UserInterface(String name, int sizeX, int sizeY) {
        this.name = name;
        setTitle(name);
        setSize(610, 625);
        setVisible(true);
        setResizable(true);
        addWindowListener(new WindowListener());
        image = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_USHORT_GRAY);
        g = image.createGraphics();

        try {
            background = SaveFile.getImage("chessboard.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        repaint();
    }

    /**
     * Creates a new Frame, whose size is maximized.
     * @param name the name of the frame
     */
    public UserInterface(String name) {
        this.name = name;
        setTitle(name);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setResizable(true);
        addWindowListener(new WindowListener());
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_USHORT_GRAY);
        g = image.createGraphics();

        repaint();
    }
    
    /**
     * Window listener for closing the window
     */
    class WindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            exit();
        }
    }

    /**
     * Draws the content of the frame
     *
     * @param graphics the graphics context onto which the content is drawn.
     */
    public void paint(Graphics graphics) {
        
        graphics.drawImage(image, 10, 20, this);
    }

    /**
     * Draws the content of the frame
     *
     * @param graphics the graphics context onto which the content is drawn.
     */
    @Override
    public void update(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.drawRect(0, 0, getWidth(), getHeight());

        graphics.drawImage(image, 5, 20, this);
    }
    
    public void drawImage(int x, int y, BufferedImage image) {
        g.drawImage(image, x, y, this);
        repaint();
    }
    
    
    
    /**
     * Stops the program.
     */
    public void exit() {
        this.dispose();
        String desktop = System.getProperty("user.home") + "//Desktop//";
        try {
            ImageIO.write(image, "png", new File(desktop + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}