package 链表;

/*
 * 160. 相交链表（Intersection of Two Linked Lists）
 *
 * 题目：给你两个单链表的头节点 headA 和 headB，请你找出并返回两个单链表相交的起始节点。
 *      如果两个链表不存在相交节点，返回 null。
 *      注意：从各自的表头开始算起，链表 A 和 B 在节点 c1 开始相交；
 *           函数返回结果后，链表必须保持其原始结构。
 *
 * 示例：
 *   输入：intersectVal = 8，listA = [4,1,8,4,5]，listB = [5,6,1,8,4,5]
 *   输出：相交节点 c1（值为 8）
 *   输入：intersectVal = 2，listA = [1,9,1,2,4]，listB = [3,2,4]
 *   输出：相交节点 c1（值为 2）
 *   输入：intersectVal = 0，listA = [2,6,4]，listB = [1,5]
 *   输出：null
 *
 * 思路：双指针「互换跑道」。
 *   p1 从 A 出发，走完 A 后接着走 B；p2 从 B 出发，走完 B 后接着走 A。
 *   两者都恰好走 a + b + c 步（a、b 是各自独有段长，c 是公共段长），
 *   所以有交点时会在交点相遇；无交点时会在 null 处同时抵达。
 *   循环条件直接写 while (p1 != p2) —— 比较的是节点引用，不是值。
 *
 * 复杂度：时间 O(m+n)，空间 O(1)。（也可用哈希集合存 A 的节点，O(m) 空间。）
 */
public class 相交链表_160 {
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

        static ListNode getIntersectionNode(ListNode headA,ListNode headB){
          ListNode p1=headA,p2=headB;
          while(p1!=p2){
              p1=p1==null?headB:p1.next;
              p2=p2==null?headA:p2.next;
          }
          return p1;
        }

        public static void main(String[] args){
            int[] common=new int[]{1,2,3,4,5};
            ListNode commonList=createLinkedList(common);
            int[] vals1=new int[]{5,7,9};
            int[] vals2=new int[]{8,6,2};
            ListNode headA=createLinkedList(vals1);
            ListNode tailA=headA;
            while(tailA.next!=null) tailA=tailA.next;
            tailA.next=commonList;
            ListNode headB=createLinkedList(vals2);
            ListNode tailB=headB;
            while(tailB.next!=null) tailB=tailB.next;
            tailB.next=commonList;
            ListNode res=getIntersectionNode(headA,headB);
            System.out.println(res!=null?res.val:"null");
        }
    }
}
