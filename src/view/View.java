package view;

import chessboard.*;
import io.SaveFile;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class View extends Frame implements MouseListener, MouseMotionListener {
    private static final String NAME = "Schachaufgaben erstellen";

    private Graphics2D g;
    private BufferedImage image;
    private BufferedImage background;

    private PieceType[] types = {PieceType.king, PieceType.queen, PieceType.rook, PieceType.bishop, PieceType.knight, PieceType.pawn};
    private Colour[] colours = {Colour.white, Colour.black};

    private Board board;
    private Piece currentPiece;

    private DisplayableImage[][] pieceButtons;
    private BufferedImage[][][] pieceImages;

    /**
     * Creates a new view of the given board.
     */
    public View(Board board) throws IOException {
        this.board = board;
        pieceImages = new BufferedImage[6][2][3];
        pieceButtons = new DisplayableImage[6][2];

        try {
            for (int i = 0; i < pieceImages.length; i++) {
                for (int j = 0; j < pieceImages[0].length; j++) {
                    pieceImages[i][j][0] = getImage(types[i], colours[j], Colour.white);
                    pieceImages[i][j][1] = getImage(types[i], colours[j], Colour.black);
                    pieceImages[i][j][2] = getImage(types[i], colours[j], Colour.print);
                    pieceButtons[i][j] = new DisplayableImage(pieceImages[i][j][0], 300 + i * 64, 15 + j * 64);
                }
            }
        } catch (IOException e) {
            throw new IOException("Couldn't load UserInterface (" + e.getMessage() + ")");
        }


        setTitle(NAME);
        setIconImage(pieceImages[0][0][0]);
        setLocation(224, 78);
        setSize(1024, 768);
        //setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setResizable(true);

        addMouseListener(this);
        addMouseMotionListener(this);
        addWindowListener(new WindowListener());

        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        g = image.createGraphics();

        try {
            background = SaveFile.getImage("chessboard.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        repaint();

    }

    /**
     * Window listener for closing the window.
     */
    class WindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            exit();
        }
    }

    /**
     * Loads a BufferedImage, corresponding to the given type and colour.
     *
     * @param type
     *         the pieceType
     * @param playerColour
     *         the colour
     * @return the BufferedImage that corresponds to the given information
     * @throws IOException
     *         if the image couldn't be read
     */
    private BufferedImage getImage(PieceType type, Colour playerColour, Colour fieldColour) throws IOException {
        return SaveFile.getImage(playerColour.toString() + "_" + type.toString() + "_" + fieldColour.toString().substring(0, 1) + ".png");
    }

    /**
     * Draws the content of the frame.
     *
     * @param graphics
     *         the graphics context onto which the content is drawn
     */
    @Override
    public void update(Graphics graphics) {
        // clear image
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());


        // draw all piece buttons
        for (DisplayableImage[] images : pieceButtons) {
            for (DisplayableImage image : images) {
                g.drawImage(image.getImage(), image.getXPosition(), image.getYPosition(), this);
            }
            if (currentPiece != null) {
                DisplayableImage currentButton = pieceButtons[currentPiece.getType().ordinal()][currentPiece.getColour().ordinal()];
                g.setColor(Color.black);
                g.drawRect(currentButton.getXPosition(), currentButton.getYPosition(), currentButton.getWidth(), currentButton.getHeight());
                g.drawRect(currentButton.getXPosition() + 1, currentButton.getYPosition() + 1, currentButton.getWidth() - 2, currentButton.getHeight() - 2);
            }
        }
        // draw the board
        int xOrigin = 200;
        int yOrigin = 145;
        g.drawImage(background, xOrigin, yOrigin, this);
        // draw the pieces of the board
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                try {
                    Field field = board.getField(x, y);
                    Piece piece = field.getPiece();
                    if (piece != null) {
                        g.drawImage(pieceImages[piece.getType().ordinal()][piece.getColour().ordinal()][field.getColour().ordinal()], xOrigin + 38 + 64 * x, yOrigin + 38 + 512 - 64 * (y + 1), this);
                    }
                } catch (IllegalArgumentException e) {
                    //todo deal with exception
                }
            }
        }


        graphics.drawImage(image, 5, 20, this);
    }

    /**
     * Stops the program.
     */
    public void exit() {
        this.dispose();

        //todo do something on exit?

        System.exit(0);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() - 5;
        int y = e.getY() - 20;
        for (int i = 0; i < pieceButtons.length; i++) {
            for (int j = 0; j < pieceButtons[i].length; j++) {
                boolean onImageX = x >= pieceButtons[i][j].getXPosition() && x <= pieceButtons[i][j].getXPosition() + pieceButtons[i][j].getWidth();
                boolean onImageY = y >= pieceButtons[i][j].getYPosition() && y <= pieceButtons[i][j].getYPosition() + pieceButtons[i][j].getHeight();
                if (onImageX && onImageY) {
                    currentPiece = new Piece(types[i], colours[j]);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
