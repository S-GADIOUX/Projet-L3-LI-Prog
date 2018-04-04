import com.sgadioux.style.Player;
import com.sgadioux.style.*;
import java.util.*;
public class Game{
	
	public Player[] players;
	public Tile[] board;
	public int bLength;
	public Dice dice;
	public int tryNb;
	public int clueNb;
	public int nearestNb;
	public WordVector datas;

	/**
	 *
	 * @param playersName
	 * @param boardLength
	 * @param d
	 * @param tNb
	 * @param cNb
	 * @param nNb
	 * @param pOfRelaunch
	 * @param pOfBackpush
	 * @param data
	 */
	public Game(String[] playersName, int boardLength, Dice d, int tNb, int cNb, int nNb, double pOfRelaunch, double pOfBackpush, WordVector data){
		this.players= new Player[playersName.length];
		this.bLength = boardLength;
		this.dice = d;
		this.tryNb = tNb;
		this.clueNb = cNb;
		this.nearestNb = nNb;
		this.datas = data;
		
		for(int i=0; i < playersName.length; i ++){
			this.players[i] = new Player(playersName[i]);
		}

		this.board = new Tile[boardLength];
		board[0] = new Tile(new NormalLaunch(), new NormalLand(), 0);
		for(int i = 1; i < bLength-1 ; i++){
			LandStyle lan = new NormalLand();
			double rand = Math.random();
			if (rand < pOfRelaunch){
				lan = new RelaunchLand();
			}else if(rand < pOfRelaunch + pOfBackpush){
				lan = new BackpushLand(3);
			}
			board[i] = new Tile(new NormalLaunch(), lan, i);
		}
		board[bLength-1] = new Tile(new NormalLaunch(), new NormalLand(), bLength-1);
		
	
	}

	public void play(){
		Tile end = this.board[this.board.length-1];
		int mv, effect, i;
		boolean b;
		while(end.isEmpty()){
			for(Player p : this.players ){
				b = true;
				System.out.println("It's turn of "+p+".");
				while(b){
					mv = this.board[p.tileNumber].launch(p,dice);
					effect = this.board[Math.min(bLength-1, Math.max(p.tileNumber + mv, 0))].land(p,dice);
					this.board[Math.min(this.board.length-1,Math.max(p.tileNumber + effect, 0))].fall(p);
					if (p.tileNumber == bLength-1){
						System.out.println("Incredible "+p+", you finished the game, let's see if someone esle can do it in the same turn.");
						break;
					}
					i = 0;
					b = false;
					String tg = datas.getRandomWord();
					while(i< this.tryNb && !b){
						i++;
						System.out.println("Try " + i + "/"+tryNb+ " : ");
						b = this.guess(tg);
					}
					if(b)
						System.out.println("Well played, let's continue "+p);
					else
						System.out.println(""+p+" played.");
				
				}
			}
			System.out.println("A turn is done.\n");
		}
	
	}

	public boolean guess(String toGuess){
		System.out.println("Type " + this.clueNb + " word" + (this.clueNb>1 ? "s" : "") + " : ");
		Scanner sc = new Scanner(System.in);
		String[] words = new String[clueNb];
		for(int i = 0; i<clueNb ; i++){
			words[i] = sc.next();
		}
		for(int j = 0; j<clueNb ; j++){
			while(!datas.contains(words[j])){
				System.out.println("\n" + words[j] + " is not a known word. Please enter an other word : ");
				words[j] = sc.next();
			}
		}
		double[][] vectors = new double[clueNb][];
		for(int k = 0; k < clueNb ; k++)
			vectors[k] = datas.getVector(words[k]);
		double[] average = datas.average(vectors);
		HashMap<String, Double> nearestMap = datas.nearest( average , nearestNb);
		System.out.println(Arrays.asList(nearestMap));
		return nearestMap.containsKey(toGuess);
	}
}
