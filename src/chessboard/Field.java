package chessboard;

/**
 * A field of a chess board consitsting of its colour and the piece that might be on it.
 */
public class Field {
    private Colour colour;
    private Piece piece;
    private Mark mark;

    /**
     * Creates a new field with the given piece on it.
     *
     * @param colour
     *         the colour of the field
     * @param piece
     *         the piece on the field
     */
    public Field(Colour colour, Piece piece) {
        this.colour = colour;
        this.piece = piece;
        piece.setField(this);
    }

    /**
     * Creates a new empty field with the given colour.
     *
     * @param colour
     *         the colour of the field
     */
    public Field(Colour colour) {
        this.colour = colour;
    }

    /**
     * Gets the piece of this field.
     *
     * @return the piece of the field, or {@code null} if there is none
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Sets the piece to the given value. Can create an illegal state where the piece's field and
     * the field's piece don't match!
     *
     * @param piece
     *         the piece of the field, or {@code null} if it is empty
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Sets the mark of this field or removes it, if it is {@code null}.
     *
     * @param mark the mark of this field
     */
    public void setMark(Mark mark) {
        this.mark = mark;
    }

    /**
     * Removes any mark of this field.
     */
    public void unmark() {
        setMark(null);
    }
}
