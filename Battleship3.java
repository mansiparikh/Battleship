
public class Battleship3 {
	/**
	 * @param args 
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.print("Do you want to play with another (h)uman or (a)rtificial intelligence? ");
		String opponent = IO.readString();
		
		Player p1;
		Player p2;
		
		if (opponent.charAt(0) == 'h') {
		p1 = new Player_mnp78();
		p2 = new Player_mnp78();
		} else {
		p1 = new Player_mnp78();
		p2 = new AIPlayer_mnp78();	
		}
		
		p1.placeShips();
		p2.placeShips();
		
		Coordinate x;
		char result;
		
		while (true) {
		
			x = p1.fire();
			result = p2.fireUpon(x);
			p1.fireResult(result);
			p1.printBoard();
			
			x = p2.fire();
			result = p1.fireUpon(x);
			p2.fireResult(result);
			p2.printBoard();
			
			if (p2.lost() == true) {
				System.out.print("Player 1 wins!");
				break; 
			} 
			else if (p1.lost() == true) {
				System.out.print("Player 2 wins!");
				break; 
			}
		
		}
		
	}

	
}
