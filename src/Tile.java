import java.util.HashSet;
import com.sgadioux.style.*;
public class Tile {
	
	private HashSet<Player> players;
	private LaunchStyle launcher;
	private LandStyle lander;
	private int number;

	public Tile(LaunchStyle lau, LandStyle lan, int nb){
		this.launcher = lau;
		this.lander = lan;
		this.players = new HashSet<Player>();
		this.number = nb;
	}

	public boolean isEmpty(){
		return this.players.size() == 0;
	}

	public int launch(Player p, Dice d){
		this.players.remove(p);
		return this.launcher.launch(d) + p.applyMod();
	}

	public int land(Player p, Dice d){
		p.moveTo(number);
		return this.lander.land(p,d);
	}

	public void fall(Player p){
		p.moveTo(number);
		this.players.add(p);
	}

}
