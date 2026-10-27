package 链表;

/*
 * 21. 合并两个有序链表（Merge Two Sorted Lists）
 *
 * 题目：将两个升序链表合并为一个新的升序链表并返回。
 *      新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 *   输入：l1 = [1,2,4]，l2 = [1,3,4]
 *   输出：[1,1,2,3,4,4]
 *   输入：l1 = []，l2 = []
 *   输出：[]
 *   输入：l1 = []，l2 = [0]
 *   输出：[0]
 *
 * 思路：哑节点 + 双指针。
 *   p1、p2 分别指向两条链表当前节点，每次把较小者「接」到结果链尾（p.next = 较小节点）；
 *   某一条走完后，把另一条剩下的整段直接接到链尾（链表自带顺序，无需逐个复制）。
 *
 * 注意：这里接的是原节点，不是 new ListNode，所以额外空间 O(1)；
 *   对比 23 题的 merge 用了 new ListNode，多花了 O(N) 空间。
 *
 * 复杂度：时间 O(m+n)，空间 O(1)。
 */
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
            ListNode dummy=new ListNode(0);
            ListNode p=dummy;
            ListNode p1=headA,p2=headB;
            while(p1!=null&&p2!=null){
                if(p1.val>=p2.val){
                    p.next=p2;
                    p2=p2.next;
                }else{
                    p.next=p1;
                    p1=p1.next;
                }p=p.next;
            }
            if(p1!=null) p.next=p1;
            else p.next=p2;
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
