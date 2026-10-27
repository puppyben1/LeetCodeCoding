package 链表;

/*
 * 19. 删除链表的倒数第 N 个节点（Remove Nth Node From End of List）
 *
 * 题目：给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 示例：
 *   输入：head = [1,2,3,4,5]，n = 2
 *   输出：[1,2,3,5]
 *   输入：head = [1]，n = 1
 *   输出：[]
 *   输入：head = [1,2]，n = 1
 *   输出：[1]
 *
 * 思路：哑节点 + 快慢指针（一次遍历）。
 *   快指针先走 n 步，然后快慢指针同速前进，直到 fast 走到最后一个节点（fast.next == null），
 *   此时 slow 正好停在待删节点的前一个，执行 slow.next = slow.next.next 即可。
 *
 * 为什么加哑节点：若删除的是头节点（如 n == 链表长度），slow 会停在哑节点上，
 *   直接改 slow.next 就能删掉 head，不用单独写分支 —— 这正是原 TODO 想提醒的边界。
 *
 * 复杂度：时间 O(L)，空间 O(1)。
 */
public class 删除链表的倒数第N个节点_19 {
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

        static ListNode removeNthFromEnd(ListNode head,int k){
          ListNode dum=new ListNode();
          dum.next=head;
          ListNode slow=dum,fast=dum;
          for(int i=0;i<k;i++){
              fast=fast.next;
          }
          while(fast!=null&&fast.next!=null){
              slow=slow.next;
              fast=fast.next;
          }
          slow.next=slow.next.next;
          return dum.next;
        }
        static ListNode removeLastNNode(ListNode head,int k){
            ListNode dum = new ListNode();
            dum.next =head;
            ListNode fast=dum,slow =dum;
            for(int i =0; i < k;i++){
                fast = fast.next;
            }
            while(fast.next!=null && slow !=null){
                fast=fast.next;
                slow =slow.next;
            }
            slow.next=slow.next.next;
            return dum.next;

        }
        public static void main(String[] args){
            int[] vals=new int[]{1,2,3,4,5};
            ListNode head=toLinkedList(vals);
            ListNode head1=removeNthFromEnd(head,2);
            printLinkedList(head1);
        }
    }
}
