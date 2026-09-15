package 链表;

import java.util.PriorityQueue;

//给你一个链表数组，每个链表都已经按升序排列。
//请你将所有链表合并到一个升序链表中，并返回合并后的链表。
public class 合并K个升序链表_23 {
    static class Solution{
        static class ListNode{
            int val;
            ListNode next;
            public ListNode(){};
            public ListNode(int val){
                this.val=val;
            }
            public ListNode(int val,ListNode next){
                this.val=val;
                this.next=next;
            }
        }

        static ListNode mergeKLists(ListNode[] lists){
            // TODO: 在这里重写你的实现
            if(lists.length == 0 ) return null;
            ListNode dummy = new ListNode(-1);
            ListNode p = dummy;
            PriorityQueue<ListNode> pq = new PriorityQueue<>(
                    lists.length,
                    (a, b) -> Integer.compare(a.val, b.val)
            );
            //空链表（null）不能入堆，PriorityQueue 禁止 null 元素
            for(ListNode t : lists){
                if(t != null){
                    pq.add(t);
                }
            }
            while(!pq.isEmpty()){
                ListNode node = pq.poll();
                p.next = node;
                if(node.next != null){
                    pq.add(node.next);
                }
                p = p.next;
            }
            return   dummy.next;
        }
    }
}
