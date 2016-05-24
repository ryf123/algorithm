package leetcode;
/*Given a string, find the 
 * longest substring that contains only 
 * two unique characters. For example, 
 * given "abcbbbbcccbdddadacb", 
 * the longest substring that contains 2 unique character 
 * is "bcbbbbcccb".
*/
public class LongestSubStringTwoDistinceChar {
	public int lengthOfLongestSubstringTwoDistinct(String s){
		int start = 0;
		int end = 0;
		int counter = 0;
		int maxLength = 0;
		int[] map = new int[256];
		for (int i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);
			
			int index = c;
			if (map[index] == 0)
				counter++;
			map[index]++;
			while(counter >2){
				map[s.charAt(start)]--;
				if(map[s.charAt(start)] == 0)
					counter--;
				start++;
			}
			if(counter == 2){
				if(i-start+1 > maxLength){
					end = i;
					maxLength = end-start+1;
				}
			}
		}
		return maxLength;
	}
	public static void main(String[] args) {
		LongestSubStringTwoDistinceChar longestSubStringTwoDistinceChar = new LongestSubStringTwoDistinceChar();
		System.out.println(longestSubStringTwoDistinceChar.lengthOfLongestSubstringTwoDistinct("abcbbbbZZZZ"));
	}
}
