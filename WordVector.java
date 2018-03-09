import java.io.*;
public class WordVector {

	private HashMap<String, double[]> datas;

	public WordVector(String path){
		try {
			FileReader fReader= new FileReader(path);
			BufferedReader bReader = new BufferedReader(bReader);
			String line = bReader.nextLine();
			Scanner sc = new Scanner(line);
			int nb = sc.nextInt();
			int dim = sc.nextInt();
			this.datas = new HashMap<String, double[]>(nb+1, 1);
			for(int i = 0; i < nb ; i++){
				sc = new Scanner(bReader.nextLine());
				String word = sc.next();
				double[] data = new double[dim];
				for(int j = 0 ; j < dim ; j++ ){
					data[j] = sc.nextDouble();
				}
				this.datas.put(word, data);
			}	
		}
		catch (Exception e){
			e.printStackTrace();
		}

	}

	public static double[] moyenne(double[][] vectors){
		int nb = vectors.length;
		int dataLength = vectors[0].length;
		double[] total = new double[datalength];
		for(int i = 0 ; i < nb ; i){
			for(int j = 0 ; j < dataLength ; j++){
				total[j]+=vectors[i].coords[j]/n;
			}
		}

		return total;
	
	}

}
