
public class ValidateUTF8 {
    public boolean validUtf8(int[] data) {
        int bytes = 0;
        int mask = 1<<7;
        int shift = 7;
        while(((mask & data[0])>>shift) == 1){
            bytes++;
            shift--;
            mask>>=1;
        }
        if(bytes != data.length -1)
            return false;
        System.out.println(bytes);
        mask = (1<<7) | (1<<6); //11000000
        int target = 1<<7;
        for(int i=1;i<bytes;i++){
            if((data[i] & mask) != target)
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
    	ValidateUTF8 vu = new ValidateUTF8();
    	int[] data = new int[]{230,136,145};
    	System.out.println(vu.validUtf8(data));
	}
}
