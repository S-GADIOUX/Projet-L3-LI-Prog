package com.sgadioux.word;

import java.io.*;
import java.util.*;

/**
 * Un objet WordVector contient la liste de tous les mots avec leur vecteur associé.
 * Il utilise une HashMap pour lier les mots aux vecteurs et une liste quand il faut itérer sur les mots.
 * Les vecteurs sont représentés pas des tableaux de double.
 * @author sebga
 */
public class WordVector {
	
	/**
	 * The hash linking words to their position.
	 */
	private final HashMap<String, double[]> datas;
	
	/**
	 * The dimension of the vectorial space which represent words.
	 */
	private final int dim;
	
	/**
	 * The list of all known words.
	 */
	private final String[] keys;
	
	/**
	 * The number of known words.
	 */
	private final int wordNb;

	/**
	 * Génère un objet WordVector à partir d'un fichier bien formé. Renvoie une erreur dans le cas contraire.
	 * @param path
	 *	Le chemin vers le ficher à lire
	 * @throws Exception Dans le cas où le fichier n'est pas bien formé.
	 */
	public WordVector(String path) throws Exception{
		FileReader fReader= new FileReader(path);
		BufferedReader bReader = new BufferedReader(fReader);
		String line = bReader.readLine();
		Scanner sc = new Scanner(line);
		this.wordNb = sc.nextInt();
		this.dim = sc.nextInt();
		this.datas = new HashMap<>(wordNb+1, 1);
		this.keys = new String[this.wordNb];
		for(int i = 0; i < wordNb ; i++){
			String buff = bReader.readLine();
			if (!buff.equals("") ){
				sc = new Scanner(buff);
				String word = sc.next();
				double[] data = new double[dim];
				for(int j = 0 ; j < dim ; j++ ){
					data[j] = Double.parseDouble(sc.next());
				}
				keys[i] = word;
				this.datas.put(word, data);
			}
			else {
			i--;
			}
		}
	}

	/**
	 * Renvoie un mot aléatoire connu. 
	 * @return Un mot aléatoire sous forme de string.
	 */
	public String getRandomWord(){
		return keys[(int)(Math.random() * wordNb)];
	}
	
	/**
	 * Renvoie la dimension des vecteurs de mot.
	 * @return La dimension sous forme d'entier.
	 */
	public int getDataSize(){
		return this.dim;
	}

	/**
	 * Renvoie le vecteur associé à un mot.
	 * @param key
	 *	Le mot dont le vecteur doit être récupéré
	 * @return Le vecteur associé sous la forme d'un tableau de double.
	 */
	public double[] getVector(String key){
		return this.datas.get(key);
	}
		
	/**
	 * Vérifie si un mot est connu.
	 * @param s
	 *	Le mot à tester
	 * @return
	 *	True si le mot est connue, False sinon.
	 */
	public boolean contains(String s){
		return datas.containsKey(s);
	}

	/**
	 * Calcule la moyenne entre les vecteurs passés en arguments.
	 * @param vectors
	 *	Un tableau de vecteurs.
	 * @return Un vecteur sous la forme d'un tableau de double.
	 */
	public static double[] average(double[][] vectors){
		int nb = vectors.length;
		int dataLength = vectors[0].length;
		double[] total = new double[dataLength];
		for(int i = 0 ; i < nb ; i++){
			for(int j = 0 ; j < dataLength ; j++){
				total[j]+=vectors[i][j]/nb;
			}
		}

		return total;
	
	}

	/**
	 * Calcule la similarité cosinus entre deux vecteurs
	 * @param v1
	 *	Le premier vecteur.
	 * @param v2
	 *	Le second vecteur.
	 * @return Un double entre 0 et 1 inclus.
	 */
	public static double similarity(double [] v1, double[] v2){
		double num = 0 ;
		double det1 = 0 ;
		double det2 = 0 ;
		for(int i = 0; i<v1.length; i++){
			num += v1[i]*v2[i];
			det1 += Math.pow(v1[i],2);
			det2 += Math.pow(v2[i],2);

		}
		return num/(Math.sqrt(det1*det2));
	
	}

	/**
	 * Calcule et renvoie les mots les plus proche au vecteur passé en argument.
	 * @param keyWord
	 *	Le vecteur à comparer, sous la forme d'un tableau de double.
	 * @param number
	 *	Le nombre de mot devant être renvoyé.
	 * @return Une hashmap contenant les -number- mots les proche du vecteur avec les scores associés.
	 */
	public HashMap<String, Double> nearest(double[] keyWord, int number){
		Cell head = new Cell("", 0.0);
		for (String s : keys){
			head = head.add( new Cell (s, similarity(datas.get(s), keyWord)), number);
		}
		HashMap<String, Double> nMap= new HashMap<>(number+1, 1);
		for (int i = 0 ; i < number ; i++){
			nMap.put(head.getWord(), head.getScore());
			head = head.getNext();
		}
		return nMap;
	}


}
