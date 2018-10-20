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
            Board board = new Board();
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
