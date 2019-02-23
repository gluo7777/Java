public class Lists{

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){ this.val = val;}
    }

    public static void main(String[] args) {
        
    }

    /**
     * In a list with a cycle, a reversed list will have the same root node.
     * The cycle forces the undoing of the reversal
     */
    static boolean hasCycle(ListNode head) {
        return head == reversedLinkedList(head);
    }

    /**
     * Reverses a linked list and returns head of reversed list.
     */
    static ListNode reversedLinkedList(ListNode head){
        ListNode prev=null; // used to reverse direction of each node
        ListNode forward=null; // used to store each node's next node before the connection is broken
        while(head != null){
            forward = head.next;
            head.next = prev;
            prev = head;
            head = forward;
        }
        return prev; 
    }
}