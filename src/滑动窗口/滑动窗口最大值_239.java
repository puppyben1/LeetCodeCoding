package 滑动窗口;

import java.util.*;

import static 滑动窗口.滑动窗口最大值_239.Solution.maxSlidingWindow;
import static 滑动窗口最大值_239.Solution.maxSlidingWindow;
import static 滑动窗口最大值_239.Solution.maxSlidingWindow;

// 239. 滑动窗口最大值
// 题：大小为 k 的窗口在 nums 上每次右移一格，返回每个窗口中的最大值
// 示例：
//   nums = [1,3,-1,-3,5,3,6,7], k = 3 → [3,3,5,5,6,7]
//   nums = [1], k = 1                 → [1]
//
// 思路：单调递减双端队列，队列里存"下标"
//   队列内下标对应的值从队头到队尾严格递减 → 队头就是当前窗口最大值
//   ① 队头过期（下标 < i-k+1）→ 从队头弹出
//   ② 新元素入队前，从队尾弹出所有比它小的下标（它们不可能再成为最大值）
//   ③ 新元素入队；窗口已满（i >= k-1）时，res[i-k+1] = nums[队头]
//   时间 O(n)，每个下标最多入队、出队一次；空间 O(k)
//
// 示例过程 nums = [1,3,-1,-3,5,3,6,7], k = 3
//   i=0 (1)：队 [0]
//   i=1 (3)：1 < 3，弹出 0 → 队 [1]
//   i=2 (-1)：队 [1,2]            → 窗口满，答案 nums[1] = 3
//   i=3 (-3)：队 [1,2,3]          → 答案 3
//   i=4 (5)：队头 1 过期；5 弹出 -3、-1 → 队 [4] → 答案 5
//   i=5 (3)：队 [4,5]             → 答案 5
//   i=6 (6)：6 弹出 3、5 → 队 [6]  → 答案 6
//   i=7 (7)：7 弹出 6 → 队 [7]     → 答案 7
//   结果 [3,3,5,5,6,7]
public class 滑动窗口最大值_239 {
    static class Solution{
        static int[] maxSlidingWindow(int[] nums,int k){
            // 使用双端队列，可以操控队头和队尾
            int[] res=new int[nums.length-k+1];
            Deque<Integer> deque=new ArrayDeque<>();
            for(int i=0;i<nums.length;i++){
                while(!deque.isEmpty()&&deque.peekFirst()<i-k+1) deque.pollFirst();
                while(!deque.isEmpty()&&nums[deque.peekLast()]<nums[i]) deque.pollLast();
                deque.offerLast(i);
                if(i-k+1>=0) res[i-k+1]=nums[deque.peekFirst()]; //别忘了是数值！
            }
            return res;
            }

        }
    static int[] maxSlidingWindow1(int[] nums,int k) {
        // 使用双端队列，可以操控队头和队尾
        int res = new int[nums.length-k+1];
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i =0;i< nums.length;i++){
            while (!deque.isEmpty() && deque.peekFirst() < i-k+1) deque.pollFirst();
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) deque.pollLast();
            deque.offerLast(i);
            if(i-k+1 >=0)res[i-k+1] = nums[deque.peekFirst()];

    }
        return res;
}

        public static void main(String[] args){
            int[] nums=new int[]{1,3,-1,-3,5,3,6,7};
            System.out.println(Arrays.toString(Solution.maxSlidingWindow(nums,3)));
        }
    }
}
