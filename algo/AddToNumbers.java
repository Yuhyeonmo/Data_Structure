
public class AddToNumbers {
	
	 public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        ListNode answer =null;
	        ListNode nextNode = null;
	        int carry=0;
	        while(l1!=null || l2!=null || carry>0){       
	            int val = (l1!=null ? l1.val : 0) + (l2!=null ? l2.val : 0) + carry;
	            carry = 0;
	            if(val>=10){
	                val -= 10;
	                carry = 1;
	            }
	           
	            if(answer==null){
	                answer = new ListNode(val);
	                nextNode = answer;
	            } else {
	                nextNode.next = new ListNode(val);
	                nextNode = nextNode.next;
	            }
	            
	            l1 = (l1!=null ? l1.next :null);
	            l2 = (l2!=null ? l2.next :null);
	        }

	        return answer;
	    }

    public static void main(String[] args) {
    	ListNode l1 = new ListNode(9);
//    	l1.next = new ListNode(4);
//    	l1.next.next = new ListNode(3);
    	
    	ListNode l2 = new ListNode(1);
    	l2.next = new ListNode(9);
    	l2.next.next = new ListNode(9);
    	
    	ListNode ln = addTwoNumbers(l1, l2);
    	
    	while( ln != null ){
    		System.out.print(ln.val);
    		ln = ln.next;
    	}
	}   
}
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}
