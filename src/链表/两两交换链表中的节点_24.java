package 链表;

import java.util.List;

/*
 * 24. 两两交换链表中的节点（Swap Nodes in Pairs）
 *
 * 题目：给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 *      你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * 示例：
 *   输入：head = [1,2,3,4]
 *   输出：[2,1,4,3]
 *   输入：head = []
 *   输出：[]
 *   输入：head = [1]
 *   输出：[1]
 *
 * 思路：哑节点 + 三个指针。
 *   每轮让 a = p.next，b = a.next（即本次要交换的两个节点），next = b.next：
 *     p.next = b;  b.next = a;  a.next = next;
 *   交换后本对的尾节点是 a，令 p = a 继续下一对。
 *   循环条件是 p.next != null && p.next.next != null —— 保证后面至少还有两个节点；
 *   奇数个节点时，末尾那个自然留在原位。
 *
 * 复杂度：时间 O(n)，空间 O(1)。
 */
public class 两两交换链表中的节点_24 {
    static class Solution{
        static class ListNode{
            int val;
            ListNode next;
            public ListNode(){};
            public ListNode(int val,ListNode next){
                this.val=val;
                this.next=next;
            }
            public ListNode(int val){
                this.val=val;
            }
        }
        static ListNode createLinkedList(int[] vals){
            if(vals.length==0) return null;
            ListNode dum=new ListNode();
            ListNode p=dum;
            for(int val:vals){
                p.next=new ListNode(val);
                p=p.next;
            }
            return dum.next;
        }
        static void printLinkedList(ListNode head){
            if(head==null){
                System.out.println("null");
                return;
            }
            ListNode p=head;
            while(p!=null){
                if(p.next==null) System.out.println(p.val);
                else System.out.print(p.val+"->");
                p=p.next;
            }
        }

        static ListNode swapNext(ListNode head){
            ListNode dum=new ListNode();
            dum.next=head;
            ListNode p=dum;
            // 至少后面还有俩个节点
            while(p.next!=null&&p.next.next!=null){
                ListNode a=p.next;
                ListNode b=p.next.next;
                ListNode next=b.next;
                p.next=b;
                b.next=a;
                a.next=next;
                p=a;
            }return dum.next;
        }
        public static void main(String[] args){
            int[] vals=new int[]{1,2,3,4,5,6,7};
            ListNode test=createLinkedList(vals);
            printLinkedList(swapNext(test));
        }
    }
}
