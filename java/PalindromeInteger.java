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
        //x==rev������λ���ȣ�x==rev/10��ż��λ���ȡ�
		return ((x==rev)||(x==rev/10));
	}

}
