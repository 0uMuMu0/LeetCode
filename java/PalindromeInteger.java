package Easy;

public class PalindromeInteger {
	public static void main(String[] args){
		int x = 10;
		System.out.println(x+" is palindromic? "+isPalindrome(x));
	}
	
	public static boolean isPalindrome(int x){
		//we assume all negative numbers are NOT palindromic.
		if((x<0)||(x!=0&&x%10==0)){
			return false;
		}
		//solve without extra space
        int rev = 0;
        while(x>rev){
        	rev = rev*10 + x%10;
        	x = x/10;
        }
        //x==rev是奇数位长度，x==rev/10是偶数位长度。
		return ((x==rev)||(x==rev/10));
	}

}
