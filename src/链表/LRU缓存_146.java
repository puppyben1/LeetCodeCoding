package 链表;
import java.awt.desktop.PreferencesEvent;
import java.lang.foreign.AddressLayout;
import java.util.*;

//请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
//实现 LRUCache 类：
//LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
//int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
//void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
//函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
//示例：
//  LRUCache cache = new LRUCache(2);
//  cache.put(1, 1);   // 缓存是 {1=1}
//  cache.put(2, 2);   // 缓存是 {1=1, 2=2}
//  cache.get(1);      // 返回 1
//  cache.put(3, 3);   // 淘汰关键字 2，缓存是 {1=1, 3=3}
//  cache.get(2);      // 返回 -1（未找到）
//  cache.put(4, 4);   // 淘汰关键字 1，缓存是 {4=4, 3=3}
//  cache.get(1);      // 返回 -1（未找到）
//  cache.get(3);      // 返回 3
//  cache.get(4);      // 返回 4
//思路：HashMap 存 key -> 节点实现 O(1) 定位；双向链表维护使用顺序（head 端最新、tail 端最旧），
//  两个哑节点让插入 / 删除都不需要判空。
public class LRU缓存_146 {
    static class Solution{

        /*
         * 记忆口诀：
         *   一个 HashMap + 一条双向链表 + 两个哑节点（head / tail）
         *   head 端 = 最新，tail 端 = 最旧
         *   命中（get 或 put 已存在的 key）：remove(n) + addFirst(n)
         *   未命中：先看满没满 → 满了就踢 tail.pre → 再 new / map.put / addFirst
         */
        static class LRUCache {

            static class Node {
                int key, val;
                Node pre, next;
                Node(int key, int val) { this.key = key; this.val = val; }
            }
            
            private final int capacity;
            private final Map<Integer, Node> map = new HashMap<>();
            private final Node head = new Node(0, 0);   // 哑节点：新节点插在它后面
            private final Node tail = new Node(0, 0);   // 哑节点：淘汰它前面那个
            
            public LRUCache(int capacity) {
                this.capacity = capacity;
                head.next = tail;   // 空链表也要首尾相连，省掉所有 null 判断
                tail.pre = head;
            }
        
            // 插到最前面（head 之后）：先把 n 的两根指针接好，再让邻居认它
            private void addFirst(Node n) {
                n.next = head.next;
                n.pre = head;
                head.next.pre = n;
                head.next = n;
            }
  
            // 从链表中摘下 n（n 一定在链表里）
            private void remove(Node n) {
                n.pre.next = n.next;
                n.next.pre = n.pre;
            }

            public int get(int key) {
                Node n = map.get(key);      // 不会存 null，所以一次 get 就够
                if (n == null) return -1;
                remove(n);
                addFirst(n);                // 用过了，提到最前
                return n.val;
            }

            public void put(int key, int value) {
                Node n = map.get(key);
                if (n != null) {            // 已存在：改值 + 提到最前
                    n.val = value;
                    remove(n);
                    addFirst(n);
                    return;
                }
                if (map.size() == capacity) {   // 满了：先踢最旧的（tail 前面那个）
                    Node old = tail.pre;
                    remove(old);
                    map.remove(old.key);        // 必须用 old.key，不能按 value 找
                }
                n = new Node(key, value);       // 再插入新的
                map.put(key, n);
                addFirst(n);
            }
           
            
        }
 
            
        }
        public static void main(String[] args){
            Solution.LRUCache lRUCache = new Solution.LRUCache(2);
            lRUCache.put(1, 1); // 缓存是 {1=1}
            lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
            System.out.println(lRUCache.get(1));    // 返回 1
            lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
            System.out.println(lRUCache.get(2));     // 返回 -1 (未找到)
            lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
            System.out.println(lRUCache.get(1));     // 返回 -1 (未找到)
            System.out.println(lRUCache.get(3));     // 返回 3
            System.out.println(lRUCache.get(4));     // 返回 4
        }
    }
