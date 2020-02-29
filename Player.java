public interface Player{

	/**
	* This player is being fired upon. Given a coordinate, updates board(s) accordingly.
	* 
	* @param x - the coordinate that is being fired upon
	* @return M for miss, otherwise the ship's char representation
	*/
	public char fireUpon(Coordinate x);

	/**
	* Returns a coordinate that this player wished to guess.
	* ask for input
	* parse input --> x and y
	* store in Coordinate
	* return coordinate (like it says below) object which contains x and y
	* 
	* @return A coordinate object 
	*/
	public Coordinate fire();
	
	/**
	* Callback method to notify player whether last fire() attempt was successful or not.
	*  
	* @param result 'M' if the last fire() resulted in a miss, otherwise the character code of the ship
	*/
	public void fireResult(char result);
	
	/**
	* Places all the ships for this player
	*/
	public void placeShips();
	
	/**
	* Returns if this player has lost 
	* (print board, too maybe)
	*
	* @return true if this player has lost, false otherwise
	*/
	public boolean lost();
	
	/**
	* Prints this player's Board B. 
	*
	*/
	public void printBoard();
}