package 链表;

/*
 * 83. 删除排序链表中的重复元素（Remove Duplicates from Sorted List）
 *
 * 题目：给定一个已排序的链表的头 head，删除所有重复的元素，
 *      使每个元素只出现一次。返回已排序的链表。
 *
 * 示例：
 *   输入：head = [1,1,2]
 *   输出：[1,2]
 *   输入：head = [1,1,2,3,3]
 *   输出：[1,2,3]
 *
 * 思路：单指针跳过。
 *   p 指向当前保留的节点：若 p.val == p.next.val，说明后面重复，令 p.next = p.next.next
 *   （只删掉后面那个，p 不动，继续判断新的 p.next）；否则 p = p.next 前移。
 *
 * 注意：链表已排序，重复元素一定相邻，所以一趟遍历就够。
 *   与 82 题的区别：本题保留每个值的一个副本，82 题是把重复过的值全部删光。
 *
 * 复杂度：时间 O(n)，空间 O(1)。
 */
public class 有序链表删除重复元素1_83 {
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

        static ListNode deleteDuplicates(ListNode head){
            ListNode p=head;
            while(p!=null&&p.next!=null){
                if(p.val==p.next.val) p.next=p.next.next;
                else p=p.next;
            }
            return head;
        }

        public static void main(String[] args){
            int[] vals=new int[]{1,1,3,3,3,6,7,8,8,9};
            ListNode head=toLinkedList(vals);
            ListNode head1=deleteDuplicates(head);
            printLinkedList(head1);
        }
    }
}
