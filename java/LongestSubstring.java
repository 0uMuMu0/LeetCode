import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.math.*;
public class LongestSubstring {
	public static void main(String[] args){
		String s1 = "abcabcbb";
		String s2 = "bbbbb";
		String s3 = "pwwkew";
		System.out.println("The length of Longest Substring of "+s1+" is: "
		                   +lengthOfLongestSubstring(s1));
		System.out.println("The length of Longest Substring of "+s2+" is: "
                +lengthOfLongestSubstring(s2));
		System.out.println("The length of Longest Substring of "+s3+" is: "
                +lengthOfLongestSubstring(s3));
		
		
	}
	
	public static int lengthOfLongestSubstring(String s){
		int ans=0;
		int i=0,j=0;
		//解法1
		Set<Character> set = new HashSet<>();
		while(i<s.length() && j<s.length()){
			if(!set.contains(s.charAt(j))){
				set.add(s.charAt(j));
				j++;
				ans = Math.max(ans, j-i);
			}else{
				set.remove(s.charAt(i));
				i++;
			}
		}
		
		return ans;
		
	}
	
	
	public static int lengthOfLongestSubstring2(String s){
		int ans=0;
		int i=0,j=0;
		
		//解法2：更好的解法
		Map<Character,Integer> map = new HashMap<>();
		while(i<s.length() && j<s.length()){
			if(map.containsKey(s.charAt(j))){
				i = Math.max(i, map.get(s.charAt(j))+1);
			}
			map.put(s.charAt(j), j);
			j++;
			ans = Math.max(ans, j-i);
		}
		return ans;
		
	} 

}
