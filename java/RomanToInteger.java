package Easy;

import java.util.HashMap;

public class RomanToInteger {
	public static void main(String[] args){
		String s1 = "MCMXCIX";
		String s2 = "MM";
		System.out.println(romanToInt(s1));
		System.out.println(romanToInt2(s2));
	}
	
	public static int romanToInt(String s){
		int[] number = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
		String[] roman = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0;i<roman.length;i++){
			map.put(roman[i], number[i]);
		}
		
		int num = 0;
		int index = 0;
		String key;
		while(index<s.length()){
			key = s.substring(index, Math.min(s.length(),index+2)); //防止超出边界
			if(!map.containsKey(key)){
				key = s.substring(index, index+1);
			}
			num += map.get(key);
			index += key.length();
		}
		return num;
	}
	
	public static int romanToInt2(String s){
		//更直观一点的解法
		int[] num = new int[s.length()];
		for(int i=0;i<s.length();i++){
			switch(s.charAt(i)){
			    case 'M':
				    num[i]=1000;
				    break;
			    case 'D':
				    num[i]=500;
				    break;
			    case 'C':
				    num[i]=100;
				    break;
			    case 'L':
			    	num[i]=50;
			    	break;
			    case 'X':
			    	num[i]=10;
			    	break;
			    case 'V':
			    	num[i]=5;
			    	break;
			    case 'I':
			    	num[i]=1;
			    	break;
			}
		}
		int sum=0;
		for(int i=0;i<num.length-1;i++){
			if(num[i]<num[i+1]){
				sum -= num[i];
			}else{
				sum += num[i];
			}
		}
		sum += num[num.length-1];
		return sum;
	}
	
	
	
	

}
