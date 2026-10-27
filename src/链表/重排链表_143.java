package 链表;

/*
 * 143. 重排链表（Reorder List）
 *
 * 题目：给定一个单链表 L 的头节点 head，单链表 L 表示为：
 *      L0 → L1 → … → Ln-1 → Ln
 *      请将其重新排列后变为：
 *      L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 *      不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例：
 *   输入：head = [1,2,3,4]
 *   输出：[1,4,2,3]
 *   输入：head = [1,2,3,4,5]
 *   输出：[1,5,2,4,3]
 *
 * 思路：三步组合拳（都是链表基本操作）。
 *   1) 快慢指针找中点，从中点后切断成两半（slow 从 head、fast 从 head.next 起步，slow 停在偏左中点）；
 *   2) 反转后半段；
 *   3) 两半交替合并：先取前半的一个，再取后半的一个，依次穿插。
 *
 * 合并的边界：因为中点偏左，前半段长度 >= 后半段，所以循环结束时只可能 headA 还剩节点
 *   （奇数长度时多出的那个），只需处理 if (headA != null) 即可，headB 不可能有剩余。
 *
 * 复杂度：时间 O(n)，空间 O(1)。
 */
public class 重排链表_143 {
    static class Solution{
        static class ListNode{
            int val;
            ListNode next;
            public ListNode(){};
            public ListNode(int val){this.val=val;};
            public ListNode(int val,ListNode next){
                this.val=val;
                this.next=next;
            }
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
        static ListNode toLinkedList(int[] vals){
            ListNode dum=new ListNode();
            ListNode p=dum;
            for(int val:vals){
                p.next=new ListNode(val);
                p=p.next;
            }
            return dum.next;
        }

        static ListNode reorderList(ListNode head){
            if(head==null) return null;
            ListNode slow=head,fast=head.next;
            while(fast!=null&&fast.next!=null){
                slow=slow.next;
                fast=fast.next.next;
            }
            ListNode headB=slow.next;
            slow.next=null;
            ListNode revHeadB=reverse(headB);
            return merge(head,revHeadB);
        }

        static ListNode reverse(ListNode head){
                ListNode pre=null;
                ListNode cur=head;
                while(cur!=null){
                    ListNode next=cur.next;
                    cur.next=pre;
                    pre=cur;
                    cur=next;
                }
                return pre;
        }

        static ListNode merge(ListNode headA,ListNode headB){
            ListNode dum=new ListNode();
            ListNode p=dum;
            while(headA!=null&&headB!=null){
                p.next=headA;
                p=p.next;
                headA=headA.next;
                p.next=headB;
                p=p.next;
                headB=headB.next;
            }
            if(headA!=null) p.next=headA;
            return dum.next;
        }

        public static void main(String[] args){
            int[] vals=new int[]{1,2,3,4,5};
            ListNode test1=toLinkedList(vals);
            reorderList(test1);
            printLinkedList(test1);
        }
    }
}
