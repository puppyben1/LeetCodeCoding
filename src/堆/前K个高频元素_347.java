package 堆;

import java.util.*;

/*
 * 347. 前 K 个高频元素
 * 题：返回 nums 中出现频率前 k 高的元素（顺序任意）
 * 例：nums = [1,1,1,2,2,3], k = 2 → [1,2]
 *
 * 思路：
 *   ① HashMap 统计频率 → {1:3, 2:2, 3:1}
 *   ② 在"频率"上求前 k 大：容量 k 的小顶堆，堆里存 Map.Entry，按 value 比较
 *      size < k → 入堆；堆满后 value > 堆顶.value 才替换（弹堆顶再入）
 *   ③ 堆里剩下的 key 就是答案
 *
 * 演示（k = 2）：
 *   (1,3) → [(1,3)]
 *   (2,2) → [(2,2), (1,3)]     堆顶频率 2（小顶堆）
 *   (3,1) → 1 > 2? 否，跳过
 *   结果 [1,2]
 *
 * 复杂度：O(n + m log k)，m 为不同元素个数；空间 O(m + k)
 *
 * 语法：
 *   统计：map.merge(num, 1, Integer::sum)  或  map.getOrDefault(num, 0) + 1
 *   遍历：for (Map.Entry<Integer,Integer> e : map.entrySet()) { e.getKey(); e.getValue(); }
 *   按频率小顶堆：new PriorityQueue<>((a,b) -> Integer.compare(a.getValue(), b.getValue()))
 *   取答案：pq.poll().getKey()
 *   进阶 O(n)：桶排序，频率作下标，从高到低收集 k 个
 *
 * 注：当前代码是"大顶堆全部入堆 + 弹 k 次"，也对（O(m log m)）；上面的容量 k 小顶堆更优。
 */
public class 前K个高频元素_347 {
    static class Solution {
        static int[] topKFrequent(int[] nums, int k) {
            // ① 统计频率
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            // ② 大顶堆：按频率降序，堆顶是频率最高的元素
            PriorityQueue<Map.Entry<Integer, Integer>> pq =
                    new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                pq.offer(entry);
            }
            // ③ 弹 k 次，取数字
            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = pq.poll().getKey();
            }
            return res;

        }

        public static void main(String[] args) {
            int[] nums = new int[]{1, 1, 1, 2, 2, 3};
            System.out.println(Arrays.toString(topKFrequent(nums, 2)));
        }
    }
}
