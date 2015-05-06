package chapter_1;

import com.google.common.base.Preconditions;

/**
 * @author chenhuang
 */
public class BitMap {
	
	private int[] bitMap;
	int n;
	
	public BitMap(int n) {
		this.n = n;
		bitMap = new int[1 + n/32];
	}
	
	public void set(int i) {
		Preconditions.checkElementIndex(i, n);
		bitMap[i>>5] |= (1 << (i & 0x1f));
	}
	
	public void clr(int i) {
		Preconditions.checkElementIndex(i, n);
		bitMap[i>>5] &= ~(1 << (i & 0x1f));
	}
	
	public boolean test(int i) {
		Preconditions.checkElementIndex(i, n);
		if((bitMap[i>>5] & (1<<(i&0x1f))) != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("n = ");
		builder.append(n);
		builder.append("\n");
		for (int i = 0; i < bitMap.length; i++) {
			StringBuffer buffer = new StringBuffer(Integer.toBinaryString(bitMap[i]));
			builder.append(buffer.reverse().toString());
			builder.append(" ");
		}
		String result = builder.deleteCharAt(builder.length()-1).toString();
		return result;
	}
	
	public static void main(String[] args) {
		BitMap bitMap = new BitMap(100);
		for(int i=0; i<100; i++) {
			bitMap.set(i);
		}
		for (int i=0; i<100; i+=2) {
			bitMap.clr(i);
		}
		for (int i=0; i<100; i++) {
			if(i % 2 == 0) {
				Preconditions.checkState(!bitMap.test(i), "index %s", i);
			} else {
				Preconditions.checkState(bitMap.test(i), "index %s", i);
			}
		}
		System.out.println(bitMap);
	}

	
	
}
