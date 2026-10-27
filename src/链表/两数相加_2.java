package 链表;

import java.lang.classfile.attribute.CodeAttribute;
import java.util.List;

/*
 * 2. 两数相加（Add Two Numbers）
 *
 * 题目：给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，
 *      并且每个节点只能存储一位数字。请你将两个数相加，并以相同形式返回一个表示和的链表。
 *      你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *   输入：l1 = [2,4,3]，l2 = [5,6,4]
 *   输出：[7,0,8]      解释：342 + 465 = 807
 *   输入：l1 = [0]，l2 = [0]
 *   输出：[0]
 *   输入：l1 = [9,9,9,9,9,9,9]，l2 = [9,9,9,9]
 *   输出：[8,9,9,9,0,0,0,1]
 *
 * 思路：哑节点 + 进位。
 *   逆序存储正好让个位在链表头，可以边遍历边加，天然从头对齐。
 *   同时遍历两个链表，短的一方缺位补 0；sum = carry + v1 + v2，
 *   当前位存 sum % 10，进位 carry = sum / 10 留到下一轮。
 *   循环结束后如果 carry != 0，必须再补一个节点（示例 3 的坑）。
 *
 * 复杂度：时间 O(max(m,n))，空间 O(1)（不含返回结果）。
 */
public class 两数相加_2 {
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

        static ListNode plusTwo(ListNode headA,ListNode headB){
            ListNode dum=new ListNode();
            ListNode p=dum;
            ListNode p1=headA,p2=headB;
            int c=0;
            while(p1!=null||p2!=null){
                int val1=p1==null?0:p1.val;
                int val2=p2==null?0:p2.val;
                int sum=c+val1+val2;
                c=sum/10;
                p.next=new ListNode(sum%10);
                p=p.next;
                if(p1!=null) p1=p1.next;
                if(p2!=null) p2=p2.next;
            }
            if(c!=0) p.next=new ListNode(c);
            return dum.next;
        }
        static ListNode plustwo(ListNode A,ListNode B){
            ListNode dum = new ListNode();
            ListNode p =dum;
            ListNode p1 =A,p2=B;
            int carry = 0;
            while (p1 !=null || p2 !=null){
                int val1 = p1 == null ? 0 : p1.val;
                int val2 = p2 == null ? 0 : p2.val;
                int sum = val1+val2+carry;
                carry = sum / 10;
                p.next = new ListNode(sum %10);
                if(p1 !=null) p1=p1.next;
                if(p2 !=null) p2 =p2.next;
                p=p.next;
            }
            if(carry != 0) p.next = new ListNode(carry);
            return  dum.next;

        }

        public static void main(String[] args){
            int[] vals1=new int[]{9,4,2};
            int[] vals2=new int[]{7,4,3,9,1};
            ListNode headA=createLinkedList(vals1);
            ListNode headB=createLinkedList(vals2);
            printLinkedList(plusTwo(headA,headB));
        }
    }
}
