package Easy;

public class ReverseInteger {
	public static void main(String[] args){
		int x = 1534236469;
		System.out.println("the reverse of "+x+" is: "+reverse(x));
	}
	public static int reverse(int x){
		//x is assumed to be a 32-bit signed integer. 
		//return 0 when the reversed integer overflows.
		int INT_MAX = 0x7fffffff;
		int INT_MIN = 0x80000000;
		long re_x = 0;
		int tmp = 0;
		while(x!=0){
			tmp = x%10;
			re_x = re_x*10 + tmp;
			if(re_x<INT_MIN || re_x>INT_MAX){
				return 0;
			}
			x = x/10;
		}
		return (int)re_x;
	}

}
