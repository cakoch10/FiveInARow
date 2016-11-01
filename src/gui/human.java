package gui;

import controller.Controller;
import model.Game;
import model.Location;
import model.Player;

/** An instance of a human player */
public class human extends Controller {
	
	Location currentMove = null;
	
	/** Constructed with the player that should play as human */
	public human (Player me) {
		super(me);
	}
	
	/** Return the move that human is to play */
	protected Location nextMove(Game g) {
		return currentMove;
	}
	
	/** Update the location that represents this human's next move */
	public void updateMove (Location loc) {
		currentMove = loc;
	}
}
