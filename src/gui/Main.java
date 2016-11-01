/* NetId(s): cak247. Time spent: 13 hours, 35 minutes. */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import controller.*;
import model.*;
import model.Board.State;

/** Main class for GUI */
public class Main extends JFrame implements ActionListener, GameListener {
	
	private Controller playerX; // the person (or AI) playing X
	private Controller playerO; // the person playing O
	
	private Game g; // global instance of the game
	
	// this box contains contents currently on display on the GUI
	private Box mainBoxGame = new Box(BoxLayout.X_AXIS);
	
	
	/** Run the game by creating an instance of the class. */
	public static void main (String[] args) {
		Main gui = new Main();
	}
	
	/** Run setup procedures
	 *  create the first GUI interface that allows
	 *  user to select players. */
	public Main() {
		super("Five in a Row");
		// buttons box contains buttons
		Box buttons = new Box(BoxLayout.X_AXIS);
		
		// buttons go in the mainBox which goes in mainBoxGame
		Box mainBox = new Box(BoxLayout.Y_AXIS);
		
		JButton j1 = new JButton("Human as X");
		j1.addActionListener(this);
		JButton j2 = new JButton("Dumb AI as X");
		j2.addActionListener(this);
		JButton j3 = new JButton("Random AI as X");
		j3.addActionListener(this);
		JButton j4 = new JButton("Smart AI as X");
		j4.addActionListener(this);
		buttons.add(j1);
		buttons.add(j2);
		buttons.add(j3);
		buttons.add(j4);
		
		mainBox.add(buttons);
		JButton y1 = new JButton("Human as O");
		y1.addActionListener(this);
		JButton y2 = new JButton("Dumb AI as O");
		y2.addActionListener(this);
		JButton y3 = new JButton("Random AI as O");
		y3.addActionListener(this);
		JButton y4 = new JButton("Smart AI as O");
		y4.addActionListener(this);
		
		Box buttons2 = new Box(BoxLayout.X_AXIS);
		buttons2.add(y1);
		buttons2.add(y2);
		buttons2.add(y3);
		buttons2.add(y4);
		
		mainBox.add(buttons);
		mainBox.add(buttons2);
		JButton next = new JButton("Next");
		//next.setPreferredSize(new Dimension(150,50));
		
		next.addActionListener(this);
		mainBox.add(next);
		mainBoxGame.add(mainBox);
		
		// display GUI
		getContentPane().add(mainBoxGame, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
	
	/** Process a click of a button */
    public void actionPerformed(ActionEvent ae) {
        Object source= ae.getSource();
        if (!(source instanceof JButton))
            return;
        JButton jb= (JButton) source;
        String text= jb.getText();
        
        
        // determine whether we need to set player or update GUI
        // if we need to set player also adjust button text color
        // so the user knows what is selected
        if (text == "Human as X") {
        	if (jb.getForeground() != Color.RED)
        		jb.setForeground(Color.RED);
        	else
        		jb.setForeground(Color.BLACK);
        	
        	playerX = new human(Player.X);
        	
        }
        else if (text == "Dumb AI as X") {
        	if (jb.getForeground() != Color.RED)
        		jb.setForeground(Color.RED);
        	else
        		jb.setForeground(Color.BLACK);
        	
        	playerX = new DumbAI(Player.X);
        	
        }
        else if (text == "Random AI as X") {
        	if (jb.getForeground() != Color.RED)
        		jb.setForeground(Color.RED);
        	else
        		jb.setForeground(Color.BLACK);
        	
        	playerX = new RandomAI(Player.X);
        }
        else if (text == "Smart AI as X") {
        	if (jb.getForeground() != Color.RED)
        		jb.setForeground(Color.RED);
        	else
        		jb.setForeground(Color.BLACK);
        	
        	playerX = new SmartAI(Player.X);
        	
        }
        else if (text == "Human as O") {
        	if (jb.getForeground() != Color.RED)
        		jb.setForeground(Color.RED);
        	else
        		jb.setForeground(Color.BLACK);
        	
        	playerO = new human(Player.O);
        }
        else if (text == "Dumb AI as O") {
        	if (jb.getForeground() != Color.RED)
        		jb.setForeground(Color.RED);
        	else
        		jb.setForeground(Color.BLACK);
        	
        	playerO = new DumbAI(Player.O);
        	
        }
        else if (text == "Random AI as O") {
        	if (jb.getForeground() != Color.RED)
        		jb.setForeground(Color.RED);
        	else
        		jb.setForeground(Color.BLACK);
        	
        	playerO = new RandomAI(Player.O);
        	
        }
        else if (text == "Smart AI as O") {
        	if (jb.getForeground() != Color.RED)
        		jb.setForeground(Color.RED);
        	else
        		jb.setForeground(Color.BLACK);
        	
        	playerO = new SmartAI(Player.O);
        }
        else if (text == "Next") {
        	setupBoard(); // update GUI
        }
        else {
        	if (jb.getForeground() != Color.RED)
        		jb.setForeground(Color.RED);
        	else
        		jb.setForeground(Color.BLACK);
        }
    }
    
    /** Update GUI after players have been selected. */
    private void setupBoard() {
    	// reset mainBoxGame
    	mainBoxGame.removeAll();
    	
    	// establish a new game
    	g = new Game();
    	g.addListener(this);
    	g.addListener(playerX);
    	g.addListener(playerO);
    }
    
    /** If a change occurs update the board. */
	@Override public void gameChanged(Game g) {
		updateGameBoard(g.getBoard());
	}
	
	/** Update the GUI according to Board b. */
	public void updateGameBoard(Board b) {
		// reset everything
		mainBoxGame.removeAll();

    	// create a JPanel to house 9x9 grid
		JPanel center = new JPanel();
    	center.setLayout(new GridLayout(9,9));
		
    	// Line checks whether there is 5 in a row
		Line wins = null;
		
		if (b.getState() == State.HAS_WINNER) {
			wins = b.getWinner().line;
		}
		else if (b.getState() == State.DRAW) {
			drawCase(b);
			return;
		}
		
		// populate JPanel
		for (int row = 0; row < Board.NUM_ROWS; row++) {
			for (int col = 0; col < Board.NUM_COLS; col++) {
				Boolean c = false;
				if (wins != null && wins.contains(row, col)) c = true;
				if(b.get(row, col) == Player.O)
					center.add(new boardSquare(c, Player.O, row, col));
				else if (b.get(row, col) == Player.X)
					center.add(new boardSquare(c, Player.X, row, col));
				else {
					boardSquare bs = new boardSquare(false, null, row, col);
					center.add(bs);
					bs.addMouseListener(new MouseEvents());
				}
			}
		}
		
		// Make new GUI visible
		mainBoxGame.add(center);
		getContentPane().add(mainBoxGame, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
	
	// Interpret mouse events
	class MouseEvents extends MouseInputAdapter {
        public void mouseClicked(MouseEvent e) {
            Object ob = e.getSource();
            if (ob instanceof boardSquare) {
            	boardSquare bSquare = (boardSquare) ob;
            	Location loc = bSquare.getLoc();
            	
            	//first figure out if the person of next turn is a human
            	Player p1 = g.nextTurn();
            	if (p1 == Player.O) {
            		if (playerO instanceof human) {
            			g.submitMove(Player.O, loc);
            		}
            	}
            	else {
            		if (playerX instanceof human) {
            			g.submitMove(Player.X, loc);
            		}
            	}	
            }
        }
    }
	
	/** This method updates the GUI if the
	 *  game is a draw. */
	void drawCase(Board b) {
		this.setTitle("DRAW");
		mainBoxGame.removeAll();
		JPanel center = new JPanel();
    	center.setLayout(new GridLayout(9,9));
		
    	// makes all squares the same color
		for (int row = 0; row < Board.NUM_ROWS; row++) {
			for (int col = 0; col < Board.NUM_COLS; col++) {
				if(b.get(row, col) == Player.O)
					center.add(new boardSquare(true, Player.O, row, col));
				else if (b.get(row, col) == Player.X)
					center.add(new boardSquare(true, Player.X, row, col));
				else {
					boardSquare bs = new boardSquare(false, null, row, col);
					center.add(bs);
					bs.addMouseListener(new MouseEvents());
				}
			}
		}
		
		mainBoxGame.add(center);
		getContentPane().add(mainBoxGame, BorderLayout.CENTER);
		pack();
		setVisible(true);	
	}
}







