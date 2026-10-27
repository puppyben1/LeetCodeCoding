package 链表;

/*
 * 25. K 个一组翻转链表（Reverse Nodes in k-Group）
 *
 * 题目：给你链表的头节点 head，每 k 个节点一组进行翻转，请你返回修改后的链表。
 *      k 是一个正整数，它的值小于或等于链表的长度。
 *      如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *      不能只是单纯地改变节点内部的值，而是需要实际进行节点交换。
 *      进阶：你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 *
 * 示例：
 *   输入：head = [1,2,3,4,5]，k = 2
 *   输出：[2,1,4,3,5]
 *   输入：head = [1,2,3,4,5]，k = 3
 *   输出：[3,2,1,4,5]
 *   输入：head = [1,2]，k = 3
 *   输出：[1,2]（不足 k 个，保持原序）
 *
 * 思路：哑节点 + 分组。
 *   每轮从 start 出发走 k 步定位本组尾 end；若 end 为 null，说明剩余不足 k 个，直接结束。
 *   记下 next = end.next，令 end.next = null 把本组切断；翻转 start.next 到 end 这一段；
 *   再把翻转后的头接到 start 后、原来的组头（现为组尾）接到 next 上，最后 start 前移到组尾。
 *   注意：循环条件用 end != null 而不是链表末尾，这样最后一组不足 k 个自然被跳过。
 *
 * 复杂度：时间 O(n)，空间 O(1)。
 */
public class K个一组反转链表_25 {
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
        static ListNode reverseKGroup(ListNode head, int k){
            ListNode dum=new ListNode();
            dum.next = head;
            ListNode start = dum,end = dum;
            while(end!=null){
                for(int i=0;i<k;i++){
                    end=end.next;
                    if(end==null) break;
                }
                if(end==null) break;
                ListNode trueStart=start.next;
                ListNode next=end.next;
                end.next=null;
                start.next=reverse(trueStart);
                trueStart.next=next;
                start=trueStart;
                end=trueStart;
            }
            return dum.next;
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
        public static void main(String[] args){
            int[] testVals=new int[]{1,2,3,4,5};
            ListNode testNode= createLinkedList(testVals);
            printLinkedList(reverseKGroup(testNode,2));
        }
    }
}
