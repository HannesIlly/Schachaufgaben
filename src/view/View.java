package view;

import chessboard.*;
import io.SaveFile;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Properties;

public class View extends Frame implements MouseListener, MouseMotionListener, KeyListener {
    private static final String NAME = "Schachaufgaben erstellen";
    private static final int IMAGE_OFFSET_X = 8;
    private static final int IMAGE_OFFSET_Y = 31;
    private static final int X_ORIGIN = 200;
    private static final int Y_ORIGIN = 40;
    private static final int PADDING_BOARD_SMALL = 38;
    private static final int PADDING_BOARD_LARGE = 46;
    private static final int FIELD_SIZE = 64;

    private Graphics2D g;
    private BufferedImage image;
    private BufferedImage background;

    private PieceType[] types = {PieceType.king, PieceType.queen, PieceType.rook, PieceType.bishop, PieceType.knight, PieceType.pawn};
    private Colour[] colours = {Colour.white, Colour.black};
    private BufferedImage[][][] pieceImages;

    private Properties saves = new Properties();

    private Board board;
    private Piece currentPiece;
    private boolean move = false;

    private DisplayableImage[][] pieceButtons;
    private List<DisplayableImage> imageButtons;
    private List<Button> buttons;
    private List<TextField> textFields;
    private List<Label> labels;
    private List<Notification> notifications;

    private TextField exerciseName;

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
                    pieceButtons[i][j] = new DisplayableImage(pieceImages[i][j][0], 20 + j * FIELD_SIZE, Y_ORIGIN + PADDING_BOARD_SMALL + FIELD_SIZE + i * FIELD_SIZE);
                }
            }
        } catch (IOException e) {
            throw new IOException("Couldn't load UserInterface (" + e.getMessage() + ")");
        }
        notifications = new LinkedList<Notification>();
        imageButtons = new ArrayList<DisplayableImage>();
        imageButtons.add(new DisplayableImage(SaveFile.getImage("cursor_arrow.png"), 20, Y_ORIGIN + PADDING_BOARD_SMALL));
        imageButtons.add(new DisplayableImage(SaveFile.getImage("cursor_hand.png"), 20 + FIELD_SIZE, Y_ORIGIN + PADDING_BOARD_SMALL));
        buttons = new LinkedList<Button>();
        buttons.add(new Button("Speichern", 20, Y_ORIGIN + PADDING_BOARD_SMALL + 7 * FIELD_SIZE, 128, 64));
        buttons.add(new Button("Laden", 20, Y_ORIGIN + PADDING_BOARD_SMALL + 8 * FIELD_SIZE, 128, 64));
        textFields = new LinkedList<TextField>();
        textFields.add(new TextField(X_ORIGIN + PADDING_BOARD_SMALL + 100, Y_ORIGIN + PADDING_BOARD_SMALL + PADDING_BOARD_LARGE + 8 * FIELD_SIZE + 15, 8 * FIELD_SIZE - 100, 32));
        textFields.add(new TextField(X_ORIGIN + PADDING_BOARD_SMALL + 100, Y_ORIGIN + PADDING_BOARD_SMALL + PADDING_BOARD_LARGE + 8 * FIELD_SIZE + 15 + 32, 8 * FIELD_SIZE - 100, 32));
        textFields.add(exerciseName = new TextField(X_ORIGIN + PADDING_BOARD_SMALL + 20, Y_ORIGIN - 33, 8 * FIELD_SIZE - 40, 32));
        labels = new LinkedList<Label>();
        labels.add(new Label("FEN:", X_ORIGIN, Y_ORIGIN + PADDING_BOARD_SMALL + PADDING_BOARD_LARGE + 8 * FIELD_SIZE + 15, 100, 32));
        labels.add(new Label("Position Text:", X_ORIGIN, Y_ORIGIN + PADDING_BOARD_SMALL + PADDING_BOARD_LARGE + 8 * FIELD_SIZE + 15 + 32, 100, 32));
        labels.add(new Label("Titel:", X_ORIGIN, Y_ORIGIN - 33, 50, 32));

        try {
            saves.load(new FileReader(new File("Gespeicherte_Stellungen.saps")));
        } catch (IOException e1) {
            notifications.add(Notification.createError(e1.getMessage()));
        }

        setTitle(NAME);
        setIconImage(pieceImages[0][0][0]);
        setLocation(224, 78);// todo remove numbers
        setSize(1366, 768);
        //setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setResizable(true);

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addWindowListener(new WindowListener());

        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
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

        // display all notifications
        int notificationOriginX = X_ORIGIN + PADDING_BOARD_SMALL + PADDING_BOARD_LARGE + 8 * FIELD_SIZE;
        int notificationOriginY = this.getHeight() - IMAGE_OFFSET_Y - IMAGE_OFFSET_X - 10;
        for (Notification n : notifications) {
            g.setColor(n.getTextColor());
            g.setFont(n.getFont());
            g.drawString(n.getMessage(), notificationOriginX, notificationOriginY);
            notificationOriginY -= n.getFont().getSize() * 2;
            if (notificationOriginY < 0) {
                notificationOriginY = 0;
            }
        }
        if (!notifications.isEmpty() && notifications.get(0).isExpired()) {
            notifications.remove(0);
        }
        // draw all buttons
        for (Button button : buttons) {
            g.setColor(button.getBackgroundColor());
            g.fillRect(button.getX(), button.getY(), button.getWidth(), button.getHeight());
            g.setColor(button.getTextColor());
            g.setFont(button.getFont());
            g.drawString(button.getText(), button.getX() + 10, button.getY() + button.getHeight() + (int) ((button.getFont().getSize() * 0.75 - button.getHeight()) / 2));
            g.drawRect(button.getX(), button.getY(), button.getWidth(), button.getHeight());
            if (button.isPressed()) {
                g.setColor(Color.black);
                g.drawRect(button.getX(), button.getY(), button.getWidth(), button.getHeight());
                g.drawRect(button.getX() + 1, button.getY() + 1, button.getWidth() - 2, button.getHeight() - 2);
            }
        }
        // draw all text fields
        for (TextField textField : textFields) {
            g.setColor(Color.white);
            g.fillRect(textField.getX(), textField.getY(), textField.getWidth(), textField.getHeight());
            g.setColor(Color.black);
            g.setFont(textField.getFont());
            g.drawString(textField.showText(), textField.getX() + 10, textField.getY() + textField.getHeight() + (int) ((textField.getFont().getSize() * 0.75 - textField.getHeight()) / 2));
            g.drawRect(textField.getX(), textField.getY(), textField.getWidth(), textField.getHeight());
        }
        // draw all labels
        for (Label label : labels) {
            g.setColor(Color.white);
            g.fillRect(label.getX(), label.getY(), label.getWidth(), label.getHeight());
            g.setColor(Color.black);
            g.setFont(label.getFont());
            g.drawString(label.getText(), label.getX() + 5, label.getY() + label.getHeight() + (int) ((label.getFont().getSize() * 0.75 - label.getHeight()) / 2));
        }
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
        // draw all image buttons
        for (DisplayableImage image : imageButtons) {
            g.drawImage(image.getImage(), image.getXPosition(), image.getYPosition(), this);
        }
        DisplayableImage currentButton;
        if (move) {
            currentButton = imageButtons.get(1);
        } else {
            currentButton = imageButtons.get(0);
        }
        g.setColor(Color.black);
        g.drawRect(currentButton.getXPosition(), currentButton.getYPosition(), currentButton.getWidth(), currentButton.getHeight());
        g.drawRect(currentButton.getXPosition() + 1, currentButton.getYPosition() + 1, currentButton.getWidth() - 2, currentButton.getHeight() - 2);
        // draw the board
        g.drawImage(background, X_ORIGIN, Y_ORIGIN, this);
        // draw the pieces of the board
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                try {
                    Field field = board.getField(x, y);
                    Piece piece = field.getPiece();
                    if (piece != null) {
                        g.drawImage(pieceImages[piece.getType().ordinal()][piece.getColour().ordinal()][field.getColour().ordinal()],
                                X_ORIGIN + PADDING_BOARD_SMALL + FIELD_SIZE * x, Y_ORIGIN + PADDING_BOARD_SMALL + FIELD_SIZE * (7 - y), this);
                    }
                } catch (IllegalArgumentException e) {
                    //todo deal with exception
                }
            }
        }


        graphics.drawImage(image, IMAGE_OFFSET_X, IMAGE_OFFSET_Y, this);
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
        int xMouse = e.getX() - IMAGE_OFFSET_X;
        int yMouse = e.getY() - IMAGE_OFFSET_Y;

        // check the piece buttons for click
        for (int i = 0; i < pieceButtons.length; i++) {
            for (int j = 0; j < pieceButtons[i].length; j++) {
                boolean onImageX = xMouse >= pieceButtons[i][j].getXPosition() && xMouse <= pieceButtons[i][j].getXPosition() + pieceButtons[i][j].getWidth();
                boolean onImageY = yMouse >= pieceButtons[i][j].getYPosition() && yMouse <= pieceButtons[i][j].getYPosition() + pieceButtons[i][j].getHeight();
                if (onImageX && onImageY) {
                    currentPiece = new Piece(types[i], colours[j]);
                    move = false;
                }
            }
        }
        // check the image buttons for click
        for (DisplayableImage image : imageButtons) {
            boolean onImageX = xMouse >= image.getXPosition() && xMouse <= image.getXPosition() + image.getWidth();
            boolean onImageY = yMouse >= image.getYPosition() && yMouse <= image.getYPosition() + image.getHeight();
            if (onImageX && onImageY) {
                switch (imageButtons.indexOf(image)) {
                    case 0:
                        move = false;
                        break;
                    case 1:
                        move = true;
                        currentPiece = null;
                        break;
                    default:
                }
            }
        }
        // check the buttons for click
        for (Button button : buttons) {
            boolean onButtonX = xMouse >= button.getX() && xMouse <= button.getX() + button.getWidth();
            boolean onButtonY = yMouse >= button.getY() && yMouse <= button.getY() + button.getHeight();
            if (onButtonX && onButtonY) {
                switch (buttons.indexOf(button)) {
                    case 0:
                        try {
                            BufferedImage chessboardImage = this.image.getSubimage(X_ORIGIN, Y_ORIGIN,
                                    PADDING_BOARD_SMALL + PADDING_BOARD_LARGE + 8 * FIELD_SIZE,
                                    PADDING_BOARD_SMALL + PADDING_BOARD_LARGE + 8 * FIELD_SIZE);
                            SaveFile.writeImage("Aufgabe.png", chessboardImage);
                            notifications.add(Notification.create("Bild exportiert als " + exerciseName.getText() + ".png"));

                            try {
                                if (saves.getProperty(exerciseName.getText()) != null) {
                                    throw new IOException("Aufgabe \"" + exerciseName.getText() + "\" existiert bereits!");
                                }
                                saves.setProperty(exerciseName.getText(), board.getPositionString());
                                saves.store(new FileWriter(new File("Gespeicherte_Stellungen.saps")), null);
                            } catch (IOException e1) {
                                notifications.add(Notification.createError(e1.getMessage()));
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace(); // todo handle exception
                        }
                        break;
                    case 1:
                        notifications.add(Notification.create("Macht grad nix")); // todo implement loading
                        break;
                    default:
                }
            }
        }
        // check the text field for click
        for (TextField textField : textFields) {
            boolean onButtonX = xMouse >= textField.getX() && xMouse <= textField.getX() + textField.getWidth();
            boolean onButtonY = yMouse >= textField.getY() && yMouse <= textField.getY() + textField.getHeight();
            if (onButtonX && onButtonY) {
                textField.setActive(true);
            } else {
                textField.setActive(false);
            }
        }
        // check the fields for click
        if (X_ORIGIN + PADDING_BOARD_SMALL <= xMouse && xMouse <= X_ORIGIN + PADDING_BOARD_SMALL + 8 * FIELD_SIZE) {
            if (Y_ORIGIN + PADDING_BOARD_SMALL <= yMouse && yMouse <= Y_ORIGIN + PADDING_BOARD_SMALL + 8 * FIELD_SIZE) {
                int xField = xMouse - X_ORIGIN - PADDING_BOARD_SMALL;
                xField /= FIELD_SIZE;
                int yField = 8 * FIELD_SIZE - yMouse + Y_ORIGIN + PADDING_BOARD_SMALL;
                yField /= FIELD_SIZE;

                if (!move) {
                    if (currentPiece != null && board.getField(xField, yField).getPiece() == null) {
                        currentPiece.move(board.getField(xField, yField));
                        currentPiece = new Piece(currentPiece.getType(), currentPiece.getColour());
                    } else {
                        board.getField(xField, yField).setPiece(null);
                    }
                } else {
                    if (currentPiece == null) {
                        currentPiece = board.getField(xField, yField).getPiece();
                    } else {
                        currentPiece.move(board.getField(xField, yField));
                        currentPiece = null;
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int xMouse = e.getX() - IMAGE_OFFSET_X;
        int yMouse = e.getY() - IMAGE_OFFSET_Y;

        for (Button button : buttons) {
            boolean onButtonX = xMouse >= button.getX() && xMouse <= button.getX() + button.getWidth();
            boolean onButtonY = yMouse >= button.getY() && yMouse <= button.getY() + button.getHeight();
            if (onButtonX && onButtonY) {
                button.setPressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (Button button : buttons) {
            button.setPressed(false);
        }
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

    @Override
    public void keyTyped(KeyEvent e) {
        // check the text fields for input
        for (TextField textField : textFields) {
            if (textField.isActive()) {
                switch (e.getKeyChar()) {
                    case KeyEvent.VK_BACK_SPACE:
                        textField.removeLastChar();
                        break;
                    default:
                        textField.append(e.getKeyChar());
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
