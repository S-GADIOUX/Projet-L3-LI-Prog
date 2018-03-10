public class Game{
	
	public Player[] players;
	public Tile[] board;
	public int bLength;
	public Dice dice;

	public Game(String[] playersName, int boardLength, Dice d, double pOfRelaunch, double pOfBackpush){
		this.dice = d;
		this.bLength = boardLength;
		this.players= new Player[playersName.length];

		for(int i=0; i < playersName.length; i ++){
			this.players[i] = new Player(playersName[i]);
		}

		this.board = new Tile[boardLength];
		board[0] = new Tile(new NormalLaunch(), new NormalLand());
		for(int i = 1; i < bLength-1 ; i++){
			LandStyle lan = new NormalLand();
			double rand = Math.random();
			if (rand < pOfRelaunch){
				lan = new RelaunchLand();
			}else if(rand < pOfRelaunch + pOfBackpush){
				lan = new BackpushLand(3);
			}
			board[i] = new Tile(new NormalLaunch(), lan);
		}
		board[bLength-1] = new Tile(new NormalLaunch(), new NormalLand());
		
	
	}

	public void play(){
		Tile end = this.board[this.board.length-1];
		int mv, effect;
		while(end.isEmpty()){
			for(Player p : this.players ){
				mv = this.board[p.tileNumber].launch(p,dice);
				effect = this.board[Math.min(bLength-1, Math.max(p.tileNumber + mv, 0))].land(p,dice);
				this.board[Math.min(this.board.length-1,Math.max(p.tileNumber + effect, 0))].fall(p);
			}
		}
	
	}
}
