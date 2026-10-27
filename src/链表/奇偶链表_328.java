package 链表;

/*
 * 328. 奇偶链表（Odd Even Linked List）
 *
 * 题目：给定单链表的头节点 head，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，
 *      然后返回重新排序的列表。
 *      第一个节点的索引被认为是奇数，第二个节点的索引为偶数，以此类推。
 *      请注意，奇数组和偶数组内部的相对顺序应该与输入中的相对顺序相同。
 *      你必须在 O(1) 额外空间复杂度和 O(n) 时间复杂度下解决这个问题。
 *
 * 示例：
 *   输入：head = [1,2,3,4,5]
 *   输出：[1,3,5,2,4]
 *   输入：head = [2,1,3,5,6,4,7]
 *   输出：[2,3,6,7,1,5,4]
 *
 * 思路：奇偶双指针各自串成一条链。
 *   odd 从 head 出发、even 从 head.next 出发，每轮：
 *     odd.next = even.next;  odd = odd.next;      // 奇数节点接下一个奇数
 *     even.next = odd.next;  even = even.next;    // 偶数节点接下一个偶数
 *   循环条件 even != null && even.next != null（偶数节点总在奇数节点之后，用它判断不会越界）。
 *   最后 odd.next = evenHead，把偶链挂到奇链尾部。
 *
 * 注意：奇链的头就是原 head，所以返回 oddHead 而不是 odd（odd 已经跑到奇链末尾了）。
 *
 * 复杂度：时间 O(n)，空间 O(1)。
 */
public class 奇偶链表_328 {
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

        // 使用偶数节点前判
        static ListNode ddEvenList(ListNode head){
          ListNode oddHead=head;
          ListNode evenHead=head.next;
          ListNode even=evenHead;
          ListNode odd=oddHead;
          while(even!=null&&even.next!=null){
              odd.next=even.next;;
              odd=odd.next;
              even.next=even.next.next;
              even=even.next;
          }
          odd.next=evenHead;
          return oddHead;
        }

        public static void main(String[] args){
            int[] vals=new int[]{1,2,3,4,5,6,7};
            ListNode node=toLinkedList(vals);
            printLinkedList(ddEvenList(node));
        }
    }
}
