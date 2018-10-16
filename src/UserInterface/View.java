package UserInterface;

import chessboard.Board;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class View implements MouseListener, MouseMotionListener {
    private Board board;

    /**
     * Creates a new view of the given board.
     */
    public View(Board board) {
        this.board = board;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
        e.getX();
        e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        e.getX();
        e.getY();
    }
}
