public class Main {
	
	public static void main(String[] args){
		Dice d = new Dice(6, false);
		String[] players = {"Alpha","Delta","Gamma","Echo","Blade","Fire"};
		Game g = new Game(players, 100, d, 0.2 , 0.1);
		g.play();
	
	}

}
