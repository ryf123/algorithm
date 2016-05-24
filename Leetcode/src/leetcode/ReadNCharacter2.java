package leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. 
For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, 
implement the function int read(char *buf, int n) that reads n characters 
from the file.

Note:
The read function may be called multiple times.
*/
public class ReadNCharacter2 {
	char a = 'a';
	ArrayList<Character> buffer = new ArrayList<Character>();
	public char[] bufferChar = new char[4];
	public int read(char[] buf, int n){
		int counter = 0;
		if(buffer.size() > 0){
			while(counter<n && buffer.size()>0){
				buf[counter] = buffer.remove(0);
				counter++;
			}
			if(buffer.size() > 0)
				return n;
		}
//		System.out.println(counter);
		while(counter < n){
			char[] temp = new char[4];
			int r = read4(temp);
			int i;
			for(i=0;i<r && counter<n;i++){
				buf[counter++] = temp[i];
			}
			while(i < r){
				buffer.add(temp[i++]);
			}
			if(r < 4)
				break;
		}
		return counter;
	}
	
	private int read4(char[] buf){
		for(int i=0;i<4;i++){
			buf[i] = this.a++;
		}
		return 4;
	}
	public static void main(String[] args) {
		ReadNCharacter2 rc = new ReadNCharacter2();
		int n = 5;
		char[] buf = new char[n];
		rc.read(buf, n);
		System.out.println(buf);
		System.out.println(Arrays.toString(rc.bufferChar));
		rc.read(buf, n);
		System.out.println(buf);
		System.out.println(Arrays.toString(rc.bufferChar));
		rc.read(buf, n);
		System.out.println(buf);
		System.out.println(Arrays.toString(rc.bufferChar));
		rc.read(buf, n);
		System.out.println(buf);
	}
}
