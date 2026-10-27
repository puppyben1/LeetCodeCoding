package 链表;

/*
 * 234. 回文链表（Palindrome Linked List）
 *
 * 题目：给你一个单链表的头节点 head，请你判断该链表是否为回文链表。
 *      如果是，返回 true ；否则，返回 false 。
 *
 * 示例：
 *   输入：head = [1,2,2,1]
 *   输出：true
 *   输入：head = [1,2]
 *   输出：false
 *
 * 思路：快慢指针找中点 → 反转后半段 → 双指针逐节点比较。
 *   1) fast 从 head.next 起步，slow 从 head 起步，slow 最终落在「偏左的中点」；
 *   2) 从 slow.next 切开，反转后半段得到 revHeadB；
 *   3) head 和 revHeadB 同步前进比较值。
 *   因为前半段长度 >= 后半段，所以循环条件写成 head != null && revHeadB != null，
 *   后半段先走完就返回 true（奇数长度时中间那个节点无需比较）。
 *
 * 进阶：O(n) 时间 + O(1) 空间 —— 就是本解法。
 *   （若允许改链表可以这样写；要求「恢复原链表」时，比较完再把后半段反转接回去。）
 *
 * 复杂度：时间 O(n)，空间 O(1)。
 */
public class 回文链表_234 {
    static class Solution{
        static class ListNode{
    int val;
    ListNode next;
    ListNode(){};
    ListNode(int val){
        this.val=val;
    }
    ListNode(int val,ListNode next){
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
    ListNode dummy=new ListNode();
    ListNode cur=dummy;
    for(int val:vals){
        cur.next=new ListNode(val);
        cur=cur.next;
    }
    return dummy.next;}

        static boolean isPalindrome(ListNode head){
           ListNode slow=head;
           ListNode fast=head.next;
           while(fast!=null&&fast.next!=null){
               slow=slow.next;
               fast=fast.next.next;
           }
           ListNode headB=slow.next;
           slow.next=null;
           ListNode revHeadB=reverse(headB);
           while(head!=null&&revHeadB!=null){
               if(head.val!=revHeadB.val) return false;
               head=head.next;
               revHeadB=revHeadB.next;
           }
           return true;
        }
        static ListNode reverse(ListNode head){
            ListNode pre=null;
            ListNode cur=head;
            while(cur!=null){
                ListNode next=cur.next;
                cur.next=pre;
                pre=cur;
                cur=next;
            }
            return pre;
        }
        public static void main(String[] args){
            int[] vals=new int[]{1,2,2,2};
            ListNode head=toLinkedList(vals);
            System.out.println(isPalindrome(head));
        }
    }
}
