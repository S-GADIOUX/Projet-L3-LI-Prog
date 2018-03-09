public class WordVector {
	
	String word;
	double[] coords;

	public WordVector(String word, double[] data){
		this.word = word;
		this.coords = coords;

	}

	public static double[] moyenne(WordVector[] vectors){
		int nb = vectors;
		int dataLength = vectors[0].coords.length;
		double[] total = new double[nb];
		for(int i = 0 ; i < nb ; i){
			for(int j = 0 ; j < dataLength ; j++){
				total[j]+=vectors[i].coords[j]/n;
			}
		}

		return total;
	
	}

}
