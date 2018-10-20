package unused;

import chessboard.Colour;
import chessboard.Mark;
import chessboard.PieceType;
import io.SaveFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Chessboard {
    private BufferedImage chessboard;
    private BufferedImage chessboardPrint;

    private UserInterface board;
    private UserInterface boardPrint;

    private BufferedImage[][][] pieces = new BufferedImage[2][6][3];

    public Chessboard(String name) {
        try {
            chessboard = SaveFile.getImage("chessboard.png");
            chessboardPrint = SaveFile.getImage("chessboard_print.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String file = "";
        for (int x = 0; x < pieces.length; x++) {
            file = "";
            if (x == 0) {
                file += "white";
            } else {
                file += "black";
            }
            for (int y = 0; y < pieces[x].length; y++) {
                file = file.substring(0, 5);
                switch (y) {
                    case 0:
                        file += "_king";
                        break;
                    case 1:
                        file += "_queen";
                        break;
                    case 2:
                        file += "_rook";
                        break;
                    case 3:
                        file += "_bishop";
                        break;
                    case 4:
                        file += "_knight";
                        break;
                    case 5:
                        file += "_pawn";
                        break;
                }
                for (int z = 0; z < pieces[x][y].length; z++) {
                    switch (z) {
                        case 0:
                            file += "_w";
                            break;
                        case 1:
                            file += "_b";
                            break;
                        case 2:
                            file += "_p";
                            break;
                    }
                    try {
                        pieces[x][y][z] = SaveFile.getImage(file + ".png");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    file = file.substring(0, file.length() - 2);
                }
            }
        }
    }

    /**
     * Draws a pieceType on the board.
     *
     * @param pieceType
     *         the pieceType
     * @param player
     *         the color of the player
     * @param xField
     *         the x position (0-7)
     */
    public void drawPiece(PieceType pieceType, Colour player, int xField, int yField) {
        int a = 0;
        int b = 0;

        int xPos = 38 + 64 * xField;
        int yPos = 38 + 512 - 64 * (yField + 1);

        if (player == Colour.black) {
            a = 1;
        }
        switch (pieceType) {
            case queen:
                b = 1;
                break;
            case rook:
                b = 2;
                break;
            case bishop:
                b = 3;
                break;
            case knight:
                b = 4;
                break;
            case pawn:
                b = 5;
                break;
            default:
                break;
        }
        if ((xField + yField) % 2 == 1) {
            this.board.drawImage(xPos, yPos, pieces[a][b][0]);
            this.boardPrint.drawImage(xPos, yPos, pieces[a][b][0]);
        } else {
            this.board.drawImage(xPos, yPos, pieces[a][b][1]);
            this.boardPrint.drawImage(xPos, yPos, pieces[a][b][2]);
        }
    }

    /**
     * Clears the board, so that now an empty chessboard is drawn.
     */
    public void clearBoard() {
        this.board.drawImage(0, 0, chessboard);
        this.boardPrint.drawImage(0, 0, chessboardPrint);
    }

    /**
     * Marks the field with the specified sign
     */
    public void markField(int x, int y, Mark mark) {
        //TODO Create marking method
    }

}
