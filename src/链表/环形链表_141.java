package 链表;

/*
 * 141. 环形链表（Linked List Cycle）
 *
 * 题目：给你一个链表的头节点 head，判断链表中是否有环。
 *      如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 *      如果链表中存在环，则返回 true；否则，返回 false。
 *      （评测系统用整数 pos 表示链表尾连接到的位置，pos 不作为参数传递。）
 *
 * 示例：
 *   输入：head = [3,2,0,-4]，pos = 1
 *   输出：true
 *   输入：head = [1,2]，pos = 0
 *   输出：true
 *   输入：head = [1]，pos = -1
 *   输出：false
 *
 * 思路：快慢指针（Floyd 判圈）。
 *   slow 每次 1 步、fast 每次 2 步，有环的话 fast 一定会在环内追上 slow；
 *   若无环，fast 会先走到 null（或 fast.next 为 null）。
 *   循环条件必须同时判断 fast != null && fast.next != null，因为 fast 一次跨两步。
 *
 * 另一种写法：哈希表记录走过的节点，遇到已访问节点即有环 —— 时间 O(n)，空间 O(n)。
 *   快慢指针的优点是空间 O(1)。入环点则要继续做 142 题。
 *
 * 复杂度：时间 O(n)，空间 O(1)。
 */
public class 环形链表_141 {
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
        static ListNode makeCycle(ListNode head,int pos){
            if(head==null||pos<0) return head;
            ListNode tail=head;
            while(tail.next!=null) tail=tail.next;
            ListNode entry=head;
            for(int i=0;i<pos;i++) entry=entry.next;
            tail.next=entry;
            return head;
        }
        static boolean hasCycle(ListNode head){
            ListNode slow=head,fast=head;
            while(fast!=null&&fast.next!=null){
                slow=slow.next;
                fast=fast.next.next;
                if(slow==fast) return true;
            }
            return false;
        }
        // 所有环都可以看作从尾部连接到某个位置（pos）
        public static void main(String[] args){
            int[] vals=new int[]{1,2,3,4};
            ListNode testHead=createLinkedList(vals);
            int pos=1;
            ListNode p=testHead,last=testHead;
            while(last.next!=null) last=last.next;
            for(int i=0;i<pos;i++) p=p.next;
            last.next=p;
            System.out.println(hasCycle(testHead));
        }
    }
}
