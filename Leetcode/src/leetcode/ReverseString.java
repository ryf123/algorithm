package leetcode;
/*
 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?
 */
class ReverseString{
    public void reverseWords(char[] s){
        int i=0; int y=0;
        while(i<s.length&&y<s.length){
            if(s[y]!=' ') y++;
            else{
                reverse(s, i, y-1);
                i = y+1;
                y = y+1;
            }
            if(y==s.length-1){
                reverse(s, i, y);
            }
        }
        reverse(s, 0, s.length-1);
    }
    private void reverse(char[] s, int i, int j){
        while(i<j){
            swap(s,i++,j--);
        }
    }
    private void swap(char[] s,int i,int j){
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
    public static void main(String[] args) {
		String s = "blue sky";
		char[] c = s.toCharArray();
		ReverseString rs = new ReverseString();
		rs.reverseWords(c);
		System.out.println(new String(c));
	}
}
