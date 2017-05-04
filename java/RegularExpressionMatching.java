package Hard;

public class RegularExpressionMatching {
	public static void main(String[] args){
		String s = "aa";
		String p = ".*";
		System.out.println(isMatch(s,p));
	}
	
	public static boolean isMatch(String s, String p){
		/* 2D DP:
		   1.if p.charAt(j)==s.charAt(i): m[i][j] = m[i-1][j-1]
		   2.if p.charAt(j)=='.': m[i][j] = m[i-1][j-1]
		   3.if p.charAt(j)=='*':
		        3.1 if p.charAt(j-1)!=s.charAt(i): m[i][j] = m[i][j-2]
		       3.2 if p.charAt(j-1)==s.charAt(i) or p.charAt(j-1)='.':
		                m[i][j] = m[i-1][j]  in this case, a* counts as multiple a 
		             or m[i][j] = m[i][j-1]  in this case, a* counts as single a
		             or m[i][j] = m[i][j-2]  in this case, a* counts as empty           
		*/
		if(s==null||p==null){
			return false;
		}
		
		boolean[][] m = new boolean[s.length()+1][p.length()+1];
	    //≥ı ºªØ
		m[0][0] = true;
		for(int j=0;j<p.length();j++){
			if(p.charAt(j)=='*' && j>0 && m[0][j-1]){
				m[0][j+1] = true;
			}
		}
		
		for(int i=0;i<s.length();i++){
			for(int j=0;j<p.length();j++){
				if(p.charAt(j)==s.charAt(i)){
					m[i+1][j+1] = m[i][j];
				}
				if(p.charAt(j)=='.'){
					m[i+1][j+1] = m[i][j];
				}
				if(p.charAt(j)=='*'){
					if(p.charAt(j-1)!=s.charAt(i)){
						m[i+1][j+1] = m[i+1][j-1];
					}
					if(p.charAt(j-1)==s.charAt(i) || p.charAt(j-1)=='.'){
						m[i+1][j+1] = (m[i+1][j-1]||m[i+1][j]||m[i][j+1]);
					}
				}
			}
		}
		return m[s.length()][p.length()];
	}

}

