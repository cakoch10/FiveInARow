package controller;

import model.Board;
import model.Game;
import model.Location;
import model.NotImplementedException;
import model.Player;

/**
 * A DumbAI is a Controller that always chooses the blank space with the
 * smallest column number from the row with the smallest row number.
 */
public class DumbAI extends Controller {

	public DumbAI(Player me) {
		super(me);
	}

	protected @Override Location nextMove(Game g) {
		// Note: Calling delay here will make the CLUI work a little more
		// nicely when competing different AIs against each other.
		
		
		
		Board b = g.getBoard();
		int cols = b.NUM_COLS;
		int rows = b.NUM_ROWS;
		for (int i = 0; i < rows; i++) {
			for (int k = 0; k < cols; k++) {
				if (b.get(i, k) == null) {
					Location l = new Location(i,k);
					return l;
				}
			}
		}
		
		return null;
	}
}
