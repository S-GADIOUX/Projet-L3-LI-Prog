import com.sgadioux.style.*;
public class Game{
	
	public Player[] players;
	public Tile[] board;
	public int bLength;
	public Dice dice;
	public int tryNb;
	public int clueNb

	public Game(String[] playersName, int boardLength, Dice d, int tNb, int cNb,  double pOfRelaunch, double pOfBackpush){
		this.dice = d;
		this.clueNb = cNb;
		this.tryNb = tNb;
		this.bLength = boardLength;
		this.players= new Player[playersName.length];

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
				while(b){
					mv = this.board[p.tileNumber].launch(p,dice);
					effect = this.board[Math.min(bLength-1, Math.max(p.tileNumber + mv, 0))].land(p,dice);
					this.board[Math.min(this.board.length-1,Math.max(p.tileNumber + effect, 0))].fall(p);
					System.out.println(""+p+" played.");
					i = 0;
					b = false
					while(i< this.tryNb && !b){
						b = this.guess();
						i++;
					}
				
				}
			}
			System.out.println("A turn is done.\n");
		}
	
	}

	public boolean guess(){
		Scanner sc = new Scanner(System.in);
		retunr false;
	}
}
