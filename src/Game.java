public class Game{
	
	public Player[] players;
	public Tile[] board;
	public int bLength;
	public Dice dice;

	public Game(String[] playersName, int boardLength, Dice d){
		this.dice = d;
		this.bLength = boardLength;
		this.players= new Player[playersName.length];

		for(int i=0; i < playersName.length; i ++){
			this.players[i] = new Player(playersName[i]);
		}

		this.board = new Tile[boardLength];
	
	}

	public void play(){
		Tile end = this.board[this.board.length-1];
		int mv, effect;
		while(end.player == null ){
			for(Player p : this.players ){
				mv = this.board[p.tileNumber].launch(p,dice);
				effect = this.board[Math.min(bLength-1, Math.max(p.tileNumber + mv, 0))].land(p,dice);
				this.board[Math.min(this.board.length-1,Math.max(p.tileNumber + effect, 0))].fall(p);
			}
		}
	
	}
}
