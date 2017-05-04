package Medium;

public class StringToInteger {
	public static void main(String[] args){
		String s = "12";
		System.out.println("Turn string:"+s+" to integer:"+myAtoi(s));
	}
	
	public static int myAtoi(String str){
		int num = 0;
		int index = 0;
		int sign = 1;
		int INT_MAX = 0x7fffffff;
		int INT_MIN = 0x80000000;
		//处理空字符串
		if(str.length()==0){
			return 0;
		}
		//处理字符串最前面的空字符
		while(str.charAt(index)==' ' && index<str.length()){
			index++;
		}
		//处理数的正负符号
		if(str.charAt(index)=='-'||str.charAt(index)=='+'){
			sign = (str.charAt(index)=='-') ? -1:1;
			index++;
		}
		
		while(index<str.length()){
			int digit = str.charAt(index) - '0';
			//处理非法输入
			if(digit<0 || digit >9){
				break;
			}
			//处理溢出
			if(INT_MAX/10<num || (INT_MAX/10==num && INT_MAX%10<digit)){
				return (sign==1) ? INT_MAX:INT_MIN;
			}
			num = num*10+digit;
			index++;
		}
		return sign*num;
	}

}
