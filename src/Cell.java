public class Cell {
	
	private final String word;
	private final double score;
	private Cell next = null;
	
	public Cell (String w, double sco){
		this.word = w;
		this.score = sco;
	}
	
	public void setNext( Cell n){
		this.next = n;
	} 
	
	public String getWord(){return this.word;}
		
	public Cell getNext(){ return this.next;}
	
	public double getScore(){
		return this.score;
	}
	
	public Cell add( Cell c, int max ){
		if(max<1)
			return null;
		if (this.score > c.getScore()){
			if (this.next == null){
				this.next = c;
				return this;
			}
			this.next = this.next.add(c, max-1);
			return this;
		}
		c.setNext(this);
		return c;
	}
}
