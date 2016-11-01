package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import model.Location;
import model.Player;

/** A class that represents a square on the board */
public class boardSquare extends JPanel {
	public static final int HEIGHT= 100;  // height and 
    public static final int WIDTH= 100;   // width of square
    
    private int x, y; // Coordinates of square on board
       
    // indicates whether the square at x,y is a winning square
    private boolean isWinningSquare = false;
    
    // indicates the player playing at this square
    // if null, the square is empty
    private Player squarePlayer = null;
    
    /** Constructor: a square at position x,y 
     *  with player p. Is a winning square if win is true */
    public boardSquare(boolean win, Player p, int xPos, int yPos) {
        isWinningSquare = win;
        if (p != null)
        	squarePlayer = p;
        
        x = xPos;
        y = yPos;
        
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
    }
    
    /** return the location of this square */
    public Location getLoc() {
    	return new Location(x,y);
    }
    
    /** paint this square using g. The system calls
     paint whenever the square has to be redrawn.*/
    public void paint(Graphics g) {
//        g.setColor(Color.white);
//        g.fillRect(0, 0, WIDTH-1, HEIGHT-1);
        
        g.setColor(Color.black);
        g.drawRect(0, 0, WIDTH-1,HEIGHT-1);
        
        if (squarePlayer != null) {
        	if (!isWinningSquare) {
        		if (squarePlayer == Player.O) {
        			g.setColor(Color.BLUE);
                	g.drawOval(7, 7, WIDTH-14, HEIGHT-14);
        		}
        		else {
        			g.setColor(Color.RED);
                	g.drawLine(0, 0, WIDTH, HEIGHT);
                	g.drawLine(WIDTH, 0, 0, HEIGHT);
        		}
        	}
        	else if (squarePlayer == Player.O) {
            	g.setColor(Color.yellow);
                g.fillRect(0, 0, WIDTH-1, HEIGHT-1);
                g.setColor(Color.BLACK);
            	g.drawOval(7, 7, WIDTH-14, HEIGHT-14);
        	}
        	else {
            	g.setColor(Color.yellow);
                g.fillRect(0, 0, WIDTH-1, HEIGHT-1);
                g.setColor(Color.BLACK);
            	g.drawLine(0, 0, WIDTH, HEIGHT);
            	g.drawLine(WIDTH, 0, 0, HEIGHT);
        	}
        }
        
//        
//        if(hasO && hasX) {
//        	g.setColor(Color.YELLOW);
//        	g.fillRect(0, 0, WIDTH-1, HEIGHT-1);
//        }
//        else if (hasO) {
//        	g.setColor(Color.BLUE);
//        	g.drawOval(7, 7, WIDTH-14, HEIGHT-14);
//        }
//        else if (hasX) {
//        	g.setColor(Color.RED);
//        	g.drawLine(0, 0, WIDTH, HEIGHT);
//        	g.drawLine(WIDTH, 0, 0, HEIGHT);
//        }       
    }
}
