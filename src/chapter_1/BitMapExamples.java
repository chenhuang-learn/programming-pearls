package chapter_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;

/** some examples of bit map
 * @author chenhuang
 */
public class BitMapExamples {
	
	/** get an int array filled with n shuffled ints */
	public static int[] getNShuffledInts(int n) {
		int[] result = new int[n];
		for(int i=0; i<n; i++) {
			result[i] = i;
		}
		Random rand = new Random();
		for(int i=0; i<n; i++) {
			int randIndex = rand.nextInt(n-i) + i;
			int tmp = result[i];
			result[i] = result[randIndex];
			result[randIndex] = tmp;
		}
		return result;
	}
	
	
	/** get an file contains k ints from [0, n), every line is a int 
	 * @throws IOException */
	public static void getInputFile(String fileName, int n, int k) throws IOException {
		PrintWriter pw = new PrintWriter(new File(fileName));
		int[] shuffledArray = getNShuffledInts(n);
		for (int i = 0; i < k; i++) {
			pw.println(shuffledArray[i]);
		}
		shuffledArray = null;
		pw.close();
		pw = null;
	}
	
	/** fin is a stream contains many ints, one int every line.
	 *  get ints in range[rangeStart, rangeEnd) from fin.
	 *  sort use BitMap, then write to fout
	 * @throws IOException */
	public static void sortInputFile(InputStream fin, OutputStream fout,
			int rangeStart, int rangeEnd) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(fin));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fout));
		BitMap bitMap = new BitMap(rangeEnd - rangeStart);
		String line = null;
		while((line = br.readLine()) != null) {
			int number = Integer.parseInt(line);
			if(number >= rangeStart && number < rangeEnd) {
				bitMap.set(number-rangeStart);
			}
		}
		for(int i=0; i<(rangeEnd-rangeStart); i++) {
			if(bitMap.test(i)) {
				bw.write((i+rangeStart) + "\n");
			}
		}
		bw.close();
		br.close();
		bitMap = null;
	}
	
	public static void problem_3(String inputFileName, String outputFileName) {
		try {
			getInputFile(inputFileName, 10000000, 1000000);
			sortInputFile(new FileInputStream(inputFileName),
					new FileOutputStream(outputFileName), 0, 10000000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void problem_5(String inputFileName, String outputFileName) {
		try {
			getInputFile(inputFileName, 10000000, 1000000);
			sortInputFile(new FileInputStream(inputFileName),
					new FileOutputStream(outputFileName),
					0, 5000000);
			sortInputFile(new FileInputStream(inputFileName),
					new FileOutputStream(outputFileName, true),
					5000000, 10000000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		problem_5("shuffledInts.data", "sortedInts.data");
	}
	
}
