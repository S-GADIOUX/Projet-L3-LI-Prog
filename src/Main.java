import com.sgadioux.style.*;
public class Main {
	
	public static void main(String[] args){
		Dice d = new Dice(6, false);
		String[] players = {"Alpha","Delta","Gamma","Echo","Blade","Fire"};
		Game g = new Game(players, 100, d, 3, 3, 10, 0.2 , 0.1, new WordVector("../vec0.txt"));
		g.play();
	
	}

}
