package control;

import chessboard.Board;
import view.View;

import java.io.IOException;

public class Main implements Runnable{
    private Thread t;

    private Board board;
    private View view;

    /**
     * Creates a new instance of Main - the controller for board.
     */
    public Main(Board board, View view) {
        this.board = board;
        this.view = view;



        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    /**
     * Starts the program.
     *
     * @param args the commandline arguments
     */
    public static void main(String[] args){
        try {
            // todo add: add pieces, move pieces, starting position, add name/text, save/load, export image
            // todo test
            //Board board = new Board();
            Board board = Board.createFromPositionString("Ke1 Da5 Tg3 Kg8 Df6 f7 g7 h7");
            //Board board = Board.createFromFEN("6k1/5ppp/5q2/Q7/8/6R1/8/4K3");
            View view = new View(board);
            Main main = new Main(board, view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void run() {
        Thread me = Thread.currentThread();
        while (me == t) {

            //todo fill the thread?

            view.repaint();

            try {
                t.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
