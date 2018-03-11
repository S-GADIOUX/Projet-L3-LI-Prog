import java.io.*;
import java.util.*;
public class WordVector {

	private HashMap<String, double[]> datas;

	public WordVector(String path){
		try {
			FileReader fReader= new FileReader(path);
			BufferedReader bReader = new BufferedReader(fReader);
			String line = bReader.readLine();
			Scanner sc = new Scanner(line);
			int nb = sc.nextInt();
			int dim = sc.nextInt();
			this.datas = new HashMap<String, double[]>(nb+1, 1);
			for(int i = 0; i < nb ; i++){
				sc = new Scanner(bReader.readLine());
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
	
	public boolean contains(String s){
		return datas.containsKey(s);
	}

	public static double[] moyenne(double[][] vectors){
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

}
