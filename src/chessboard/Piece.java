package chessboard;

import io.SaveFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A chess piece whith its type, color and field. It can be moved freely and doesn't follow the
 * chess rules.
 */
public class Piece {
    private Field field;
    private PieceType type;
    private Colour colour;
    private BufferedImage pieceWhite;
    private BufferedImage pieceBlack;
    private BufferedImage piecePrint;

    /**
     * Creates a new piece from the given type and colour, that is located on the given field.
     *
     * @param type
     *         the type of the piece
     * @param colour
     *         the piece's colour
     * @param field
     *         the field on which the piece is located
     */
    public Piece(PieceType type, Colour colour, Field field) {
        this.type = type;
        this.colour = colour;
        this.field = field;
        field.setPiece(this);
    }

    /**
     * Creates a new piece from the given type and colour, without it being on any field.
     *
     * @param type
     *         the type of the piece
     * @param colour
     *         the piece's colour
     */
    public Piece(PieceType type, Colour colour) {
        this.type = type;
        this.colour = colour;
    }

    /**
     * Gets the field of this piece.
     *
     * @return the field of the piece, or {@code null} if there is none
     */
    public Field getField() {
        return field;
    }

    /**
     * Sets the field to the given value. Can create an illegal state where the piece's field and
     * the field's piece don't match!
     *
     * @param field
     *         the field of the piece, or {@code null} if it isn't on any field
     */
    public void setField(Field field) {
        this.field = field;
    }

    /**
     * Moves this piece to the given field without createing an illegal state
     * {@link public void setField(Field field)}.
     *
     * @param field
     *         the field to which the piece is moved, or null if the piece is removed from the
     *         board.
     */
    public void move(Field field) {
        if (field != null) {
            if (getField() != null) {
                getField().setPiece(null);
            }
            setField(field);
            field.setPiece(this);
        }
    }

    /**
     * Gets the type of this piece
     *
     * @return the PieceType
     */
    public PieceType getType() {
        return type;
    }

    /**
     * Gets the colour of this piece
     *
     * @return the colour
     */
    public Colour getColour() {
        return colour;
    }
}
