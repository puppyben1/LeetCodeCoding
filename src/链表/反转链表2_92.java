package 链表;

import javax.swing.event.ListDataEvent;
import java.util.List;

/*
 * 92. 反转链表 II（Reverse Linked List II）
 *
 * 题目：给你单链表的头指针 head 和两个整数 left 和 right，其中 left <= right。
 *      请你反转从位置 left 到位置 right 的链表节点，返回反转后的链表。
 *      节点编号从 1 开始。
 *
 * 示例：
 *   输入：head = [1,2,3,4,5]，left = 2，right = 4
 *   输出：[1,4,3,2,5]
 *   输入：head = [5]，left = 1，right = 1
 *   输出：[5]
 *
 * 思路：哑节点 + 定位 + 整段翻转。
 *   slow 走到 left 的前一个节点，fast 走到 right 节点；
 *   记下 next = fast.next，把 [left, right] 从链表上切下来（fast.next = null，slow.next = null），
 *   整段 reverse 后再接回去：slow.next = 新头，原区间头（现在是尾）-> next。
 *   用哑节点是为了兼容 left == 1（反转从 head 开始）的情况。
 *
 * 复杂度：时间 O(n)，空间 O(1)。
 */
public class 反转链表2_92 {
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

        static ListNode reverseLR(ListNode head,int left,int right){
            ListNode dum=new ListNode();
            dum.next=head;
            ListNode slow=dum,fast=dum;
            for(int i=0;i<right-left;i++) fast=fast.next;
            for(int i=0;i<left-1;i++){
                slow=slow.next;
                fast=fast.next;
            }
            ListNode next=fast.next.next;
            fast.next.next=null;
            ListNode head1=slow.next;
            slow.next=null;
            slow.next=reverse(head1);
            head1.next=next;
            return dum.next;
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
            int[] vals=new int[]{1,2,3,4,5};
            ListNode head=toLinkedList(vals);
            ListNode res=reverseLR(head,2,5);
            printLinkedList(res);
        }
    }
}