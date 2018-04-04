package com.sgadioux.word;

import java.io.*;
import java.util.*;

/**
 *
 * @author sebga
 */
public class WordVector {

	private final HashMap<String, double[]> datas;
	private final int dim;
	private final String[] keys;
	private final int wordNb;

	/**
	 *
	 * @param path
	 * @throws Exception
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
	 *
	 * @return
	 */
	public String getRandomWord(){
		return keys[(int)(Math.random() * wordNb)];
	}
	
	/**
	 *
	 * @return
	 */
	public int getDataSize(){
		return this.dim;
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public double[] getVector(String key){
		return this.datas.get(key);
	}
		
	/**
	 *
	 * @param s
	 * @return
	 */
	public boolean contains(String s){
		return datas.containsKey(s);
	}

	/**
	 *
	 * @param vectors
	 * @return
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
	 *
	 * @param v1
	 * @param v2
	 * @return
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
	 *
	 * @param keyWord
	 * @param number
	 * @return
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
