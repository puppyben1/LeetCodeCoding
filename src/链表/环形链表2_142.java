package 链表;

/*
 * 142. 环形链表 II（Linked List Cycle II）
 *
 * 题目：给定一个链表的头节点 head，返回链表开始入环的第一个节点。
 *      如果链表无环，则返回 null。
 *      说明：不允许修改给定的链表。
 *
 * 示例：
 *   输入：head = [3,2,0,-4]，pos = 1
 *   输出：返回索引为 1 的节点（值为 2）
 *   输入：head = [1,2]，pos = 0
 *   输出：返回索引为 0 的节点（值为 1）
 *   输入：head = [1]，pos = -1
 *   输出：返回 null（无环）
 *   注：pos 仅用于表示环的入口位置，不作为参数传入。
 *
 * 思路：快慢指针两阶段。
 *   阶段一：slow 走 1 步、fast 走 2 步，若相遇则说明有环；fast 先到 null 说明无环。
 *   阶段二：把一个指针放回 head，两个指针都改成每次走 1 步，再次相遇处就是入环点。
 *
 * 为什么成立：设头到入口距离为 a，入口到相遇点为 b，环长为 L。
 *   slow 走了 a+b，fast 走了 a+b+nL，且 fast = 2 × slow：
 *   2(a+b) = a+b+nL  =>  a = nL - b = (n-1)L + (L-b)。
 *   也就是说：从头出发走 a 步，和从相遇点出发走 (L-b) 步，会同时到达入口。
 *
 * 复杂度：时间 O(n)，空间 O(1)。
 */
public class 环形链表2_142 {
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

        static ListNode cycleList(ListNode head){
            ListNode slow=head;
            ListNode fast=head;
            while(fast!=null&&fast.next!=null){
                slow=slow.next;
                fast=fast.next.next;
                if(slow==fast) break;
            }
            if(slow!=fast) return null;
            slow=head;
            while(slow!=fast){
                slow=slow.next;
                fast=fast.next;
            }
            return slow;
        }

        public static void main(String[] args){
            int[] vals=new int[]{1,2,3,4,5,6,7,8,};
            ListNode head=createLinkedList(vals);
            int pos=0;
            ListNode p=head,last=head;
            while(last.next!=null) last=last.next;
            for(int i=0;i<pos;i++) p=p.next;
            last.next=p;
            System.out.println(cycleList(head).val);
        }
    }
}
