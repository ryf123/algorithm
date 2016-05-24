/*
 * 
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, 
it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) 
that reads n characters from the file.
Note:
The read function will only be called once for each test case.
*/
package leetcode;

import java.util.*;



public class ReadNCharacter {
	char a = 'a';
	public int read(char[] buf, int n){
		int counter = 0;
		while(counter < n){
			char[] temp = new char[4];
			int r  = read4(temp);
			for(int i=0;i<r && counter<n;i++){
				buf[counter] = temp[i];
				counter++;
			}
			if(r<4)
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
		ReadNCharacter rc = new ReadNCharacter();
		int n = 5;
		char[] buf = new char[n];
		rc.read(buf, n);
		System.out.println(buf);
	}
}
