package Medium;

public class IntegerToRoman {
	public static void main(String[] args){
		int num = 1999;
		System.out.println(intToRoman(num));
	}
	
	public static String intToRoman(int num){
		int[] number = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
		String[] roman = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
		StringBuffer result = new StringBuffer();
		for(int i=0;i<number.length;i++){
			while(num>=number[i]){
				result.append(roman[i]);
				num = num-number[i];
			}
		}
		return result.toString();
	}

}
