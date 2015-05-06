package chapter_1;

import java.util.Arrays;
import java.util.Random;

/** some examples of bit map
 * @author chenhuang
 */
public class BitMapExamples {
	
	/** get an int array filled with n shuffled ints*/
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
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(getNShuffledInts(10)));
	}
	
}
