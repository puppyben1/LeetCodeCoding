package 链表;

/*
 * 148. 排序链表（Sort List）
 *
 * 题目：给你链表的头结点 head，请将其按升序排列并返回排序后的链表。
 *
 * 示例：
 *   输入：head = [4,2,1,3]
 *   输出：[1,2,3,4]
 *   输入：head = [-1,5,3,4,0]
 *   输出：[-1,0,3,4,5]
 *   输入：head = []
 *   输出：[]
 *
 * 思路：归并排序（自顶向下）。
 *   1) 快慢指针找中点：slow 从 head、fast 从 head.next 起步，slow 停在偏左的中点；
 *   2) 从 slow 后切断（slow.next = null），递归排序左右两半；
 *   3) 复用「合并两个有序链表」的逻辑归并。
 *
 * 关键细节：fast 必须从 head.next 起步。若 fast 也从 head 起步，两个节点时
 *   slow 会走到第二个节点，切出来一边是 null、另一边是原链表，递归永不缩小，直接栈溢出。
 *
 * 进阶：O(n log n) 时间 + O(1) 空间 —— 需要改成自底向上归并（按 1,2,4,8… 段长两两合并），
 *   本文件的递归版额外空间是 O(log n) 的栈。
 *
 * 复杂度：时间 O(n log n)，空间 O(log n)。
 */
public class 排序链表_148 {
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

        static ListNode sortList(ListNode head){
           if(head==null||head.next==null) return head;
           ListNode slow=head,fast=head.next;
           while(fast!=null&&fast.next!=null){
               slow=slow.next;
               fast=fast.next.next;
           }
           ListNode headB=slow.next;
           slow.next=null;
           return merge(sortList(head),sortList(headB));
        }
        static ListNode merge(ListNode headA,ListNode headB){
            ListNode dum=new ListNode();
            ListNode p=dum;
            ListNode p1=headA,p2=headB;
            while(p1!=null&&p2!=null){
                if(p1.val>=p2.val){
                    p.next=p2;
                    p2=p2.next;
                }
                else{
                    p.next=p1;
                    p1=p1.next;
                }
                p=p.next;
            }
            if(p1!=null) p.next=p1;
            else p.next=p2;
            return dum.next;
        }

        public static void main(String[] args){
            int[] vals=new int[]{2,3,5,6,7,3,2,};
            ListNode head=toLinkedList(vals);
            printLinkedList(sortList(head));
        }
    }
}
