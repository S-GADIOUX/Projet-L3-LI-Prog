package com.sgadioux.word;


/** 
 * Une cellule est un objet utilisé pour trier les différents mots.
 * Il s'agit d'une implémentation naïve à base de cellule liée.
 * Ajouter un élément dans la liste trié est semi constant.
 * En sortir un est instantané.
 * @see 
 * Cell#add( Cell cell, int max )
 * 
 * @author sebga
 */
public class Cell {
	
	/**
	 * Le mot contenu par la cellule.
	 */
	private final String word;
	
	/**
	 * Le score associé au mot.
	 */
	private final double score;
	
	/**
	 * La cellule suivante dans la liste.
	 */
	private Cell next = null;
	
	/**
	 * Crée une cellule contenant le mot passé en paramètre et le score associé.
	 * 
	 * @param world
	 *	Le mot contenu dans la cellule.
	 * @param score
	 *	Le score associé au mot.
	 */
	public Cell (String world, double score){
		this.word = world;
		this.score = score;
	}
	
	/**
	 * Setteur de la prochaine cellule.
	 * @param n
	 *	La cellule suivante.
	 */
	public void setNext( Cell n ){
		this.next = n;
	} 
	
	/**
	 * Getteur du mot.
	 * @return Le mot contenu par la cellule.
	 */
	public String getWord(){return this.word;}
		
	/**
	 * Getteur de la celluce suivante.
	 * @return La cellule suivante.
	 */
	public Cell getNext(){ return this.next;}
	
	/**
	 * Getteur du score.
	 * @return Le score associé au mot.
	 */
	public double getScore(){
		return this.score;
	}
	
	/**
	 * Methode pour ajouter une cellule dans la liste.
	 * La méthode est récursive.
	 * Cette methode a une sécurité pour éviter d'ajouter inutilement des cellules.
	 * Cette sécurité ne vérifie pas le nombre d'élément dans la liste.
	 * Il est donc possible qu'il y ai plus d'éléments dans la liste que la valeur de max.
	 * En revanche, le nombre d'iteration maximale est O(max).
	 * 
	 * @param cell
	 *	La cellule à ajouter dans la liste.
	 * @param max
	 *	Le nombre maximum d'éléments possible dans la liste.
	 * @return La cellule avec le score le plus élevé entre les deux.
	 */
	public Cell add( Cell cell, int max ){
		if(max<1)
			return null;
		if (this.score > cell.getScore()){
			if (this.next == null){
				this.next = cell;
				return this;
			}
			this.next = this.next.add(cell, max-1);
			return this;
		}
		cell.setNext(this);
		return cell;
	}
}
