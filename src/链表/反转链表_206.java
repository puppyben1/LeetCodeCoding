package 链表;

import java.util.List;

/*
 * 206. 反转链表（Reverse Linked List）
 *
 * 题目：给你单链表的头节点 head，请你反转链表，并返回反转后的链表。
 *
 * 示例：
 *   输入：head = [1,2,3,4,5]
 *   输出：[5,4,3,2,1]
 *   输入：head = [1,2]
 *   输出：[2,1]
 *   输入：head = []
 *   输出：[]
 *
 * 思路：三指针迭代。
 *   pre 指向已反转部分的头（初值 null），cur 是当前待处理节点：
 *     先存 next = cur.next（防止改指针后丢掉后继）；
 *     再反转 cur.next = pre；
 *     然后 pre、cur 一起前移。
 *   循环结束时 cur == null，pre 就是新链表的头。
 *
 * 注意：反转类题目（92、234、143、25）都是在这个函数的基础上组合出来的，
 *   把它练成肌肉记忆即可。
 *
 * 复杂度：时间 O(n)，空间 O(1)。
 */
public class 反转链表_206 {
static class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val=val;
    }
    ListNode(int val,ListNode next){
        this.val=val;
        this.next=next;
    }
}
static class Solution{
    static ListNode reverse(ListNode head){
        ListNode pre=null;
        ListNode cur=head;
        while(cur!=null){
            ListNode next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }return pre;
    }
    public static void main(String[] args){
        int[] test1=new int[]{1,2,3,4,5};
        ListNode head1=ToLinkedList(test1);
        PrintLinkedList(reverse(head1));
        int[] test2=new int[0];
        ListNode head2=ToLinkedList(test2);
        PrintLinkedList(reverse(head2));
    }
    static ListNode ToLinkedList(int[] vals){
        if(vals.length==0) return null;
        ListNode dum=new ListNode(0);
        ListNode cur=dum;
        for(int val:vals){
            cur.next=new ListNode(val);
            cur=cur.next;
        }
        return dum.next;
    }
    static void PrintLinkedList(ListNode head){
        StringBuilder sb=new StringBuilder();
        ListNode cur=head;
        while(cur!=null){
            sb.append(cur.val);
            if(cur.next!=null) sb.append("->");
            cur=cur.next;
        }
        System.out.println(sb.toString());
    }
}
}
