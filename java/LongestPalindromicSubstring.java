
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
	
	//������ַ���
	public static String longestPalindrome(String s){
		//�ⷨ1��������⣬����ÿһ���Ӵ����ᳬʱ,O(N3)
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
		//�ⷨ2:��̬�滮,ʱ�临�Ӷȣ�O(N2)���ռ临�Ӷȣ�O(N2)
		//ת�Ʒ��̣�P[i][j]=1����ʾ��s[i]��s[j]��һ�������ַ���
		//P[i][i]=1;
		//p[i][i+1]=1 if s[i]=s[i+1];
		//p[i][j]=1 if p[i+1][j-1]=1;
		int n = s.length();
		int maxLen = 1;
		int longestBegin = 0;
		boolean[][] table = new boolean[1000][1000];   //��Ŀ���Ѿ�������s�ĳ��Ȳ�����1000
		//��ʼ��
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
		//�ⷨ3:������չ��,ʱ�临�Ӷȣ�O(N2),�ռ临�Ӷȣ�O(1)
		//�Ը������ַ���s���ֱ���s�е�ÿһ���ַ�cΪ���ģ���������չ����¼�����ַ�cΪ���ĵĻ����Ӵ��ĳ���
		int n = s.length();
		int maxLen =1;
		int l,r;
		String substr = s.substring(0, 1);
		//Ҫ�������������"aba"��"abba"
		for(int i=0;i<n-1;i++){
			//���1
			l=i;r=i;
			while(l>=0&&r<=n-1&&(s.charAt(l)==s.charAt(r))){
				l--;
				r++;
			}
			if(r-l+1>maxLen){
				maxLen = r-l+1;
				substr = s.substring(l+1, r);
			}
			//���2��
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
		//�ⷨ4:Manacher�㷨��ʱ�临�Ӷȣ�O(N)
		StringBuffer str = new StringBuffer();
		//���ַ�����ÿ���ַ���û�ù����ַ��ָ���������'#',������ȫ��ת���������ַ���
		str.append('#');
		for(int i=0;i<s.length();i++){
			str.append(s.charAt(i));
			str.append('#');
		}
		//p[i]���ַ���str�����ַ�iΪ���ĵĻ��Ĵ��뾶��������i��  
		int[] p = new int[str.length()];  
		//��p[i+1]ʱ��Ҫ:
		//    ǰp[0:i]�ܴﵽ����Զ���룬��mx=max(p[k]+k),k=0..i
		//    ��Ҫ��¼�¾����k����id=k
		int mx = 1; 
		int id = 0;
		//���ַ�����ͷ��βɨ���Լ���p[i],����p[i]-1��������Ҫ���������ַ�������
		//�����������
		//����������i��ǰ��Ļ����ַ�����(��i<mx)
		//      i����ǰ��Ļ����ַ�����(��i>=mx)
		for(int i=0;i<str.length();i++){
			if(mx>i){
				p[i] = Math.min(p[id*2-i], mx-i);  //id*2-i���ַ�i����id�ĶԳ��ַ�
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
				maxR = p[i];   //maxR��str��������Ӵ��İ뾶��maxR-1����s��������Ӵ��ĳ���
				maxId = i;   
			}
		}
		StringBuffer out = new StringBuffer();
		maxR = maxR-1; //ʹ�뾶���ٰ��������ַ�
		for(int i=maxId-maxR;i<=maxId+maxR;i++){
			if(str.charAt(i)!='#'){
				out.append(str.charAt(i));
			}
		}
		
		return out.toString();
	}
	
}
