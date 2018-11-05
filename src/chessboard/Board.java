package chessboard;


public class Board {
    private Field[][] board;

    /**
     * Creates a new empty board.
     */
    public Board() {
        board = new Field[8][8];
        //All empty fields
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                board[x][y] = createField(x, y);
            }
        }
    }

    /**
     * Creates a new board with the given fields. The board will assume the state of the fields e.g.
     * if the fields have pieces on it or are in an illegal state, the board will as well.
     *
     * @param fields
     *         the 8x8 array of fields
     * @throws IllegalArgumentException
     *         if the given fields don't match the size of a chessboard
     */
    public Board(Field[][] fields) throws IllegalArgumentException {
        if (fields.length == 8 && fields[5].length == 8 && fields[5][7] != null) {
            this.board = fields.clone();
        } else {
            throw new IllegalArgumentException("The board cannot be created with the give number of fields.\nThe fields have to exist and be a 8x8 square.");
        }
    }

    /**
     * Creates a chessboard from the position String.
     *
     * @param position
     *         the position data
     * @return the board with the given position on it
     */
    public static Board createFromPositionString(String position) {
        PieceType pieceType = PieceType.king;
        Colour player = Colour.black;
        Field[][] fields = new Field[8][8];
        for (int x = 0; x < fields.length; x++) {
            for (int y = 0; y < fields[x].length; y++) {
                fields[x][y] = createField(x, y);
            }
        }
        String[] positionData = position.split(" ");
        int x = 0;
        int y = 7;

        for (int i = 0; i < positionData.length; i++) {
            String current = positionData[i];
            if (current.length() == 3) {
                switch (current.substring(0, 1).toUpperCase()) {
                    case "K":
                        pieceType = PieceType.king;
                        break;
                    case "D":
                        pieceType = PieceType.queen;
                        break;
                    case "T":
                        pieceType = PieceType.rook;
                        break;
                    case "L":
                        pieceType = PieceType.bishop;
                        break;
                    case "S":
                        pieceType = PieceType.knight;
                        break;
                    default:
                        break;
                }
                if (pieceType == PieceType.king) {
                    if (player == Colour.white) {
                        player = Colour.black;
                    } else {
                        player = Colour.white;
                    }
                }

                x = current.charAt(1) - 97;
                y = Integer.parseInt(current.substring(2, 3)) - 1;

                new Piece(pieceType, player).move(fields[x][y]);
            } else if (current.length() == 2) {
                pieceType = PieceType.pawn;

                x = current.charAt(0) - 97;
                y = Integer.parseInt(current.substring(1, 2)) - 1;

                new Piece(pieceType, player).move(fields[x][y]);
            }
        }

        return new Board(fields);
    }

    /**
     * Creates a chessboard from the position String in the FEN format.
     *
     * @param fen
     *         the position data in the FEN format
     * @return the board with the given position on it
     */
    public static Board createFromFEN(String fen) {
        PieceType pieceType = PieceType.king;
        Colour player = Colour.black;
        Field[][] fields = new Field[8][8];
        for (int x = 0; x < fields.length; x++) {
            for (int y = 0; y < fields[x].length; y++) {
                fields[x][y] = createField(x, y);
            }
        }
        int x = 0;
        int y = 7;
        boolean drawPiece;

        while (fen != null && !fen.equals("")) {
            drawPiece = true;
            switch (fen.charAt(0)) {
                case 'K':
                    pieceType = PieceType.king;
                    player = Colour.white;
                    break;
                case 'Q':
                    pieceType = PieceType.queen;
                    player = Colour.white;
                    break;
                case 'R':
                    pieceType = PieceType.rook;
                    player = Colour.white;
                    break;
                case 'B':
                    pieceType = PieceType.bishop;
                    player = Colour.white;
                    break;
                case 'N':
                    pieceType = PieceType.knight;
                    player = Colour.white;
                    break;
                case 'P':
                    pieceType = PieceType.pawn;
                    player = Colour.white;
                    break;
                case 'k':
                    pieceType = PieceType.king;
                    player = Colour.black;
                    break;
                case 'q':
                    pieceType = PieceType.queen;
                    player = Colour.black;
                    break;
                case 'r':
                    pieceType = PieceType.rook;
                    player = Colour.black;
                    break;
                case 'b':
                    pieceType = PieceType.bishop;
                    player = Colour.black;
                    break;
                case 'n':
                    pieceType = PieceType.knight;
                    player = Colour.black;
                    break;
                case 'p':
                    pieceType = PieceType.pawn;
                    player = Colour.black;
                    break;
                case '/':
                    y--;
                    x = -1;
                    drawPiece = false;
                    break;
                default:
                    x += Integer.valueOf(fen.substring(0, 1)) - 1;
                    drawPiece = false;
            }
            if (drawPiece) {
                new Piece(pieceType, player).move(fields[x][y]);
            }
            fen = fen.substring(1, fen.length());
            x++;
        }

        return new Board(fields);
    }

    /**
     * Returns the position as a string.
     *
     * @return the position
     */
    public String getPositionString() {
        String white = "";
        String black = "";
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                Piece piece = board[x][y].getPiece();
                if (piece != null) {
                    String currentString = "";
                    switch (piece.getType()) {
                        case king:
                            currentString += "K";
                            break;
                        case queen:
                            currentString += "D";
                            break;
                        case rook:
                            currentString += "T";
                            break;
                        case bishop:
                            currentString += "L";
                            break;
                        case knight:
                            currentString += "S";
                            break;
                        default:
                    }
                    currentString = currentString + ((char) (x + 97)) + (y + 1) + " ";
                    if (piece.getColour() == Colour.white) {
                        if (piece.getType() == PieceType.king) {
                            white = currentString + white;
                        } else {
                            white += currentString;
                        }
                    } else {
                        if (piece.getType() == PieceType.king) {
                            black = currentString + black;
                        } else {
                            black += currentString;
                        }
                    }
                }
            }
        }
        return white + black;
    }

    /**
     * Returns the position in FEN format.
     *
     * @return the position
     */
    public String getFEN() {
        //todo get fen from position
        return null;
    }

    /**
     * Clears this board, so that all fields are empty afterwards.
     */
    public void clear() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                board[x][y].clear();
            }
        }
    }

    /**
     * Creates an board with empty fields.
     *
     * @return An empty board.
     */
    public static Board createEmpty(){
        return Board.createFromPositionString("");
    }

    /**
     * Creates a field with the gieven piece and the correct color from the coordinates.
     */
    private static Field createField(int x, int y, Piece piece) {
        return new Field((x + y) % 2 == 0 ? Colour.black : Colour.white, piece);
    }

    /**
     * Creates a field with the correct color from the coordinates.
     */
    private static Field createField(int x, int y) {
        return new Field((x + y) % 2 == 0 ? Colour.black : Colour.white);
    }

    /**
     * Gets the field at the given position.
     *
     * @param x
     *         the x-coordinate
     * @param y
     *         the y-coordinate
     * @return the field at the given coordinates
     */
    public Field getField(int x, int y) throws IllegalArgumentException {
        if (x < board.length && y < board.length) {
            return board[x][y];
        }
        throw new IllegalArgumentException("The dimensions of the board are 8x8. Cannot return field " + x + ", " + y);
    }

}
