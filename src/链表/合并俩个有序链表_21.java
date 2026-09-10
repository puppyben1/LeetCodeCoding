package 链表;

import java.util.List;

public class 合并俩个有序链表_21 {
    public static class Solution{
        static class ListNode{
            int val;
            ListNode next;
            ListNode(int val){
                this.val=val;
            }
            ListNode(int val, ListNode next){
                this.val=val;
                this.next=next;
            }
        }
        static void printLinkedList(ListNode head){
            StringBuilder sb=new StringBuilder();
            ListNode cur=head;
            while(cur!=null){
                if(cur.next!=null) sb.append(cur.val+"->");
                else sb.append(cur.val);
                cur=cur.next;
            }System.out.println(sb.toString());
        }
        static ListNode toLinkedList(int[] vals){
            ListNode dummy=new ListNode(0);
            ListNode cur=dummy;
            for(int val:vals){
                cur.next=new ListNode(val);
                cur=cur.next;
            }
            return dummy.next;
        }
        static ListNode mergeTwoLists(ListNode headA,ListNode headB){
            // TODO: 在这里重写你的实现
            ListNode dummy = new ListNode(-1), p = dummy;
            ListNode p1 = headA, p2 = headB;
            while(p1 != null && p2 != null){
                if(p1.val > p2.val){
                    p.next = p2;
                    p2 = p2.next;
                }else{
                    p.next = p1;
                    p1 = p1.next;
                }
                p = p.next;
            }
            if(p1 != null){
                p.next = p1;
            }
            if(p2 != null){
                p.next  = p2;
            }
            return dummy.next;
        }
        public static void main(String[] args){
            int[] val1=new int[]{1,2,4};
            int[] val2=new int[]{1,3,4};
            ListNode ans=mergeTwoLists(toLinkedList(val1),toLinkedList(val2));
            printLinkedList(ans);
        }
    }
}
