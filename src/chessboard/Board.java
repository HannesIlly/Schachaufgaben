package chessboard;

import java.io.IOException;
import java.util.List;

public class Board {
    Field[][] board;
    List<Piece> pieces;

    /**
     * Creates a new board with the initial positions of the pieces.
     */
    public Board() throws IOException {
        board = new Field[8][8];
        //All empty fields
        for (int y = 2; y < board.length - 2; y++) { // this works, because the board is a square.
            for (int x = 0; x < board.length; x++) {
                createField(x, y);
            }
        }
        Piece piece;
        //Black pawns
        for (int x = 0; x < board.length; x++) {
            piece = new Piece(PieceType.pawn, Colour.black);
            createField(x, 6, piece);
            pieces.add(piece);
        }
        //White pawns
        for (int x = 0; x < board.length; x++) {
            piece = new Piece(PieceType.pawn, Colour.white);
            createField(x, 1, piece);
            pieces.add(piece);
        }
        //All other pieces
        //White
        piece = new Piece(PieceType.rook, Colour.white);
        createField(0, 0, piece);
        pieces.add(piece);
        piece = new Piece(PieceType.knight, Colour.white);
        createField(1, 0, piece);
        pieces.add(piece);
        piece = new Piece(PieceType.bishop, Colour.white);
        createField(2, 0, piece);
        pieces.add(piece);
        piece = new Piece(PieceType.queen, Colour.white);
        createField(3, 0, piece);
        pieces.add(piece);
        piece = new Piece(PieceType.king, Colour.white);
        createField(4, 0, piece);
        pieces.add(piece);
        piece = new Piece(PieceType.bishop, Colour.white);
        createField(5, 0, piece);
        pieces.add(piece);
        piece = new Piece(PieceType.knight, Colour.white);
        createField(6, 0, piece);
        pieces.add(piece);
        piece = new Piece(PieceType.rook, Colour.white);
        createField(7, 0, piece);
        pieces.add(piece);
        //Black
        piece = new Piece(PieceType.rook, Colour.black);
        createField(0, 7, piece);
        pieces.add(piece);
        piece = new Piece(PieceType.knight, Colour.black);
        createField(1, 7, piece);
        pieces.add(piece);
        piece = new Piece(PieceType.bishop, Colour.black);
        createField(2, 7, piece);
        pieces.add(piece);
        piece = new Piece(PieceType.queen, Colour.black);
        createField(3, 7, piece);
        pieces.add(piece);
        piece = new Piece(PieceType.king, Colour.black);
        createField(4, 7, piece);
        pieces.add(piece);
        piece = new Piece(PieceType.bishop, Colour.black);
        createField(5, 7, piece);
        pieces.add(piece);
        piece = new Piece(PieceType.knight, Colour.black);
        createField(6, 7, piece);
        pieces.add(piece);
        piece = new Piece(PieceType.rook, Colour.black);
        createField(7, 7, piece);
        pieces.add(piece);
    }

    /**
     * Creates a new board with the given fields. The board will assume the state of the fields e.g.
     * ff the fields have pieces on it or are in an illegal state, the board will as well.
     *
     * @param fields the 8x8 array of fields
     */
    public Board(Field[][] fields) {
        if (fields.length == 8 && fields[5].length == 8 && fields[5][7] != null) {
            this.board = fields.clone();
        } else {
            throw new IllegalArgumentException("The board cannot be created with the give number of fields.\nThe fields have to exist and be a 8x8 square.");
        }
    }

    /**
     * Creates a chessboard from the position String.
     *
     * @param position the position data
     * @return the board with the given position on it
     */
    public static Board createFromPosition(String position) {
        //todo create from position
        return null;
    }

    /**
     * Creates a chessboard from the position String in the FEN format.
     * @param fen the position data in the FEN format
     * @return the board with the given position on it
     */
    public static Board createFromFEN(String fen) {
        //todo create from fen
        return null;
    }

    /**
     * Returns the position as a string.
     *
     * @return the position
     */
    public String getPosition() {
        //todo get position as string
        return null;
    }

    /**
     * Returns the position in FEN format.
     * @return the position
     */
    public String getFEN() {
        //todo get fen from position
        return null;
    }

    /**
     * Creates a field with the gieven piece and the correct color from the coordinates.
     */
    private void createField(int x, int y, Piece piece) {
        this.board[x][y] = new Field((x + y) % 2 == 0 ? Colour.black : Colour.white, piece);
    }

    /**
     * Creates a field with the correct color from the coordinates.
     */
    private void createField(int x, int y) {
        this.board[x][y] = new Field((x + y) % 2 == 0 ? Colour.black : Colour.white);
    }
}
