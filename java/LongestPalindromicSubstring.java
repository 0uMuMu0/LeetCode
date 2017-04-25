
public class LongestPalindromicSubstring {
	public static void main(String[] args){
		String s1 = "abcdefgedcba";
		String s2 = "ababd";
		System.out.println("the longest palindromic substring of "+s1+" is: "+
		                   longestPalindrome(s1));
		System.out.println("the longest palindromic substring of "+s1+" is: "+
                longestPalindromeDP(s1));
		System.out.println("the longest palindromic substring of "+s1+" is: "+
                longestPalindrome3(s2));
		System.out.println("the longest palindromic substring of "+s1+" is: "+
                longestPalindrome4(s2));
		
	}
	
	//最长回文字符串
	public static String longestPalindrome(String s){
		//解法1：暴力求解，遍历每一个子串，会超时,O(N3)
		String substr;
		String result="";
		int i,j,k;
		int len;
		boolean flag;
		for(i=0;i<s.length();i++){
			for(j=i+1;j<=s.length();j++){
				substr = s.substring(i, j);
				flag = true;
				len = substr.length();
				for(k=0;k<len/2;k++){
					if(substr.charAt(k)!=substr.charAt(len-1-k)){
						flag = false;
						break;
					}
				}
				if(flag){
					if(substr.length()>result.length()){
						result = substr;
					}
				}
			}
		}
		
		return result;
	}

	
	public static String longestPalindromeDP(String s){
		//解法2:动态规划,时间复杂度：O(N2)，空间复杂度：O(N2)
		//转移方程：P[i][j]=1：表示从s[i]到s[j]是一个回文字符串
		//P[i][i]=1;
		//p[i][i+1]=1 if s[i]=s[i+1];
		//p[i][j]=1 if p[i+1][j-1]=1;
		int n = s.length();
		int maxLen = 1;
		int longestBegin = 0;
		boolean[][] table = new boolean[1000][1000];   //题目中已经限制了s的长度不超过1000
		//初始化
		for(int i=0;i<n;i++){
			table[i][i] = true;
		}
		for(int i=0;i<n-1;i++){
			if(s.charAt(i)==s.charAt(i+1)){
				table[i][i+1]=true;
				maxLen = 2;
				longestBegin = i;
			}
		}
		
		for(int len=3;len<=n;len++){
			for(int i=0;i<n-len+1;i++){
				int j = i+len-1;
				if((s.charAt(i)==s.charAt(j))&&(table[i+1][j-1])){
					table[i][j] = true;
					maxLen = len;
					longestBegin = i;
				}
			}
		}
		return s.substring(longestBegin, longestBegin+maxLen);
	}
	
	public static String longestPalindrome3(String s){
		//解法3:中心扩展法,时间复杂度：O(N2),空间复杂度：O(1)
		//对给定的字符串s，分别以s中的每一个字符c为中心，向两边扩展，记录下以字符c为中心的回文子串的长度
		int n = s.length();
		int maxLen =1;
		int l,r;
		String substr = s.substring(0, 1);
		//要考虑两种情况："aba"和"abba"
		for(int i=0;i<n-1;i++){
			//情况1
			l=i;r=i;
			while(l>=0&&r<=n-1&&(s.charAt(l)==s.charAt(r))){
				l--;
				r++;
			}
			if(r-l+1>maxLen){
				maxLen = r-l+1;
				substr = s.substring(l+1, r);
			}
			//情况2：
			l=i;r=i+1;
			while(l>=0&&r<=n-1&&(s.charAt(l)==s.charAt(r))){
				l--;
				r++;
			}
			if(r-l+1>maxLen){
				maxLen = r-l+1;
				substr = s.substring(l+1, r);
			}
		}
		return substr;
		
	}
	
	
	public static String longestPalindrome4(String s){
		//解法4:Manacher算法，时间复杂度：O(N)
		StringBuffer str = new StringBuffer();
		//将字符串的每个字符以没用过的字符分隔开，比如'#',这样就全部转换成奇数字符串
		str.append('#');
		for(int i=0;i<s.length();i++){
			str.append(s.charAt(i));
			str.append('#');
		}
		//p[i]：字符串str中以字符i为中心的回文串半径长（包括i）  
		int[] p = new int[str.length()];  
		//求p[i+1]时需要:
		//    前p[0:i]能达到的最远距离，即mx=max(p[k]+k),k=0..i
		//    还要记录下具体的k，即id=k
		int mx = 1; 
		int id = 0;
		//将字符串从头到尾扫描以计算p[i],最大的p[i]-1就是我们要求的最长回文字符串长度
		//有两种情况：
		//　　　　　i在前面的回文字符串内(即i<mx)
		//      i不在前面的回文字符串内(即i>=mx)
		for(int i=0;i<str.length();i++){
			if(mx>i){
				p[i] = Math.min(p[id*2-i], mx-i);  //id*2-i是字符i关于id的对称字符
			}else{
				p[i] = 1;
			}
			while((i-p[i]>=0)&&(i+p[i]<str.length())&&(str.charAt(i-p[i])==str.charAt(i+p[i]))){
				p[i]++;
			}
			if(i+p[i]>mx){
				mx = i+p[i];
				id = i;
			}
		}
		int maxR = 1;
		int maxId = 0;
		for(int i=0;i<str.length();i++){
			if(p[i]>maxR){
				maxR = p[i];   //maxR是str中最长回文子串的半径，maxR-1即是s中最长回文子串的长度
				maxId = i;   
			}
		}
		StringBuffer out = new StringBuffer();
		maxR = maxR-1; //使半径不再包含中心字符
		for(int i=maxId-maxR;i<=maxId+maxR;i++){
			if(str.charAt(i)!='#'){
				out.append(str.charAt(i));
			}
		}
		
		return out.toString();
	}
	
}
