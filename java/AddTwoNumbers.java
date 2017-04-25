
public class AddTwoNumbers {
	public static void main(String[] args){
		int[] num1 = {2,4,5,9};
		int[] num2 = {5,6,4};
 		ListNode l1 = new ListNode(num1[0]);
		ListNode p1 = l1;
		for(int i =1; i<num1.length; i++){
			p1.next = new ListNode(num1[i]);
			p1 = p1.next;
		}
		ListNode l2 = new ListNode(num2[0]);
		ListNode p2 = l2;
		for(int i=1; i<num2.length; i++){
			p2.next = new ListNode(num2[i]);
			p2 = p2.next;
		}
		ListNode sum = addTwoNumbers(l1,l2);
		ListNode p3 = sum;
		while(p3!=null){
			System.out.print(p3.val+" ");
			p3 = p3.next;
		}
		System.out.println();
		
		
	}
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
		int carry = 0;  //¿¼ÂÇ½øÎ»
		if(l1 == null){
			return l2;
		}
		if(l2 == null){
			return l1;
		}
		
		ListNode newHead = new ListNode(0);
		ListNode l3 = newHead;
		
		while(l1!=null || l2!=null){
			if(l1!=null){
				carry += l1.val;
				l1 = l1.next;
			}
			if(l2!=null){
				carry += l2.val;
				l2 = l2.next;
			}
			
			l3.next = new ListNode(carry%10);
			l3 = l3.next;
			carry /= 10;
		}
		
		if(carry == 1){
			l3.next = new ListNode(carry);
		}
		
		return newHead.next;
	}

}


class ListNode{
	int val;
	ListNode next;
	ListNode(int val){
		this.val = val;
	}
}