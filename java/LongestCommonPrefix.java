package Easy;

public class LongestCommonPrefix {
	public static void main(String[] args){
		String[] strs = {"abca","abab","abdfe"};
		System.out.println(longestCommonPrefix(strs));
		System.out.println(longestCommonPrefix2(strs));
	}
	
	public static String longestCommonPrefix(String[] strs){
		if(strs.length==0){
			return "";
		}
		int shortIndex = 0;
		for(int i=0;i<strs.length;i++){
			if(strs[shortIndex].length()>strs[i].length()){
				shortIndex = i;
			}
		}
		int maxLen = 0;
		for(int j=0;j<strs[shortIndex].length();j++){
			for(int i=0;i<strs.length;i++){
				if(strs[i].charAt(j)!=strs[shortIndex].charAt(j)){
					return strs[shortIndex].substring(0, j);
				}
			}
		}
		return strs[shortIndex];
	}
	
	public static String longestCommonPrefix2(String[] strs){
		if(strs.length==0||strs==null){
			return "";
		}
		
		String pre = strs[0];
		int i = 1;
		while(i<strs.length){
			while(strs[i].indexOf(pre)!=0){
				pre = pre.substring(0, pre.length()-1);
			}
			i++;
		}
		return pre;
		
	}

}
