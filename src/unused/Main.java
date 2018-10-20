package unused;

import chessboard.Colour;
import chessboard.PieceType;
import unused.Chessboard;

/**
 * main class
 * 
 * @author Hannes Illy
 * @version 0
 */
public class Main implements Runnable {
	
	/**
	 * constructor
	 */
	public Main() {
		
	}
	
	/**
	 * main method
	 * 
	 * @param args
	 *            args
	 */
	public static void main(String[] args) {
		String name = "board";
		boolean fen = false;
		int start = 0;
		if (args.length > 0) {
			name = args[0];
			start = 1;
			if (args.length > 1) {
				fen = Boolean.valueOf(args[1]);
				start = 2;
			}
		}
		
		Chessboard board1 = new Chessboard(name);
		
		printPosition(board1, args, start, fen);
	}
	
	public static void printPosition(Chessboard board, String[] position, int start, boolean fen) {
		board.clearBoard();
		
		PieceType pieceType = PieceType.king;
		Colour player = Colour.black;
		int x = 0;
		int y = 7;
		boolean drawPiece = false;
		
		if (fen) {
			String pos = position[start];
			while (pos != null && !pos.equals("")) {
				drawPiece = true;
				switch (pos.charAt(0)) {
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
					 x += Integer.valueOf(pos.substring(0, 1)) - 1;
					 drawPiece = false;
				}
				if (drawPiece) {
					board.drawPiece(pieceType, player, x, y);
				}
				pos = pos.substring(1, pos.length());
				x++;
			}
		} else {
			for (int i = start; i < position.length; i++) {
				String current = position[i];
				
				if (current.length() == 3) {
					switch (current.substring(0, 1)) {
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
					
				} else if (current.length() == 2) {
					pieceType = PieceType.pawn;
					
					x = current.charAt(0) - 97;
					y = Integer.parseInt(current.substring(1, 2)) - 1;
				}
				
				board.drawPiece(pieceType, player, x, y);
			}
		}
	}

    @Override
    public void run() {

    }
}