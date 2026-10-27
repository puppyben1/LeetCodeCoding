package 堆;

import java.util.*;

/*
 * 215. 数组中的第 K 个最大元素
 *
 * 思路：小顶堆维护"目前见过的最大的 k 个"，堆顶是这 k 个里最小的，也就是当前第 k 大
 *   size < k   → 直接入堆
 *   num > 堆顶  → 比当前第 k 大还大，有资格进前 k：poll 掉堆顶，再 offer(num)
 *   否则        → 撑死第 k+1 名，忽略
 *   扫完，堆顶即全局第 k 大
 *
 * 例：nums = [3,2,1,5,6,4], k = 2
 *   3 → [3]        2 → [2,3]      1 → [2,3]    (1 > 2? 否)
 *   5 → [3,5]      6 → [5,6]      4 → [5,6]    (4 > 5? 否)
 *   return peek() = 5
 *
 * 复杂度：时间 O(n log k)，空间 O(k)
 *PriorityQueue<Integer> maxHeap = new
  PriorityQueue<>((a, b) -> Integer.compare(b, a));大根堆
 * 语法（PriorityQueue）：
 *   小顶堆 new PriorityQueue<>()；大顶堆 new PriorityQueue<>(Collections.reverseOrder())
 *   offer 入堆 / peek 看堆顶 / poll 弹堆顶（空堆时 peek、poll 返回 null）
 *   遍历堆是乱序的，只有反复 poll 才有序
 *   求第 k 大用小顶堆，求第 k 小用大顶堆（反着用）
 *   比较器别写 a-b（int 溢出），用 Integer.compare(a, b)
 */
public class 数组中的第K个最大元素_215 {
    static class Solution{
        static int  findKthLargest(int[] nums,int k){
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for(int num :nums){
                if(pq.size() < k)pq.offer(num);          // 还没攒够 k 个
                else if(num > pq.peek()){                // 比"当前第 k 大"还大
                    pq.poll();                           // 踢掉 k 个里最小的
                    pq.offer(num);
                }
            }
            return pq.peek();

        }

        public static void main(String[] args){
            int[] test1=new int[]{3,2,1,5,6,4};
            System.out.println(findKthLargest(test1,2));
        }
    }
}
