
public class ToHex {
    public String toHex(int num) {
        int mask = 15; // 1111
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<8;i++){// break 32 bits into 8 4 bits
            int n = num & mask;
            if(n >= 10){
                char c = 'a' + (char)(n -10);
                sb.append(c);
            }
            else{
                sb.append(n);
            }
            num = num>>4;
        }
        return sb.reverse().toString();
    }
}
