package 链表;

/*
 * 82. 删除排序链表中的重复元素 II（Remove Duplicates from Sorted List II）
 *
 * 题目：给定一个已排序的链表的头 head，删除原始链表中所有重复数字的节点，
 *      只留下不同的数字。返回已排序的链表。
 *
 * 示例：
 *   输入：head = [1,2,3,3,4,4,5]
 *   输出：[1,2,5]
 *   输入：head = [1,1,1,2,3]
 *   输出：[2,3]
 *
 * 思路：哑节点 + 前驱指针跳过整段重复。
 *   p 是「已确认保留」的最后一个节点，cur 是当前检查位置：
 *   - 若 cur 与 cur.next 值不同：cur 是独立值，p = cur，cur = cur.next；
 *   - 若相同：记下该值，while 跳过所有等于它的节点，再令 p.next = cur（整段一次摘掉）。
 *   哑节点的作用是应对「头节点也被删」的情况（例如 [1,1,2] 直接返回 [2]）。
 *
 * 题眼：不要逐个删除节点，直接让前驱 p.next 越过整段重复即可。
 *
 * 复杂度：时间 O(n)，空间 O(1)。
 */
public class 有序链表删除重复元素2_82 {
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

        // 题眼在于不要用删除的方式去除节点，直接跳过即可
        static ListNode deleteDuplicates(ListNode head){
          ListNode dum=new ListNode();
          dum.next=head;
          ListNode p=dum;
          ListNode cur=head;
          while(cur!=null&&cur.next!=null){
              if(cur.val==cur.next.val){
                  int val=cur.val;
                  while(cur!=null&&cur.val==val) cur=cur.next;
                  p.next=cur;
              }else{
                  p=cur;
                  cur=cur.next;
              }
          }return dum.next;
        }
        public static void main(String[] args){
            int[] vals=new int[]{1,2,3};
            ListNode head=toLinkedList(vals);
            printLinkedList(deleteDuplicates(head));
        }
    }
}
