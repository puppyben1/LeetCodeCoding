package 双指针;

import java.util.Arrays;

import static 双指针.移动零_283.Solution.removeZero;

// 283. 移动零
// 题：把所有 0 移到数组末尾，同时保持非零元素的相对顺序，必须原地操作
// 示例：
//   [0,1,0,3,12] → [1,3,12,0,0]
//   [0]          → [0]
//
// 思路：快慢指针
//   slow 指向"下一个非零元素应该放的位置"，fast 负责扫描
//   nums[fast] != 0 时，交换 slow 和 fast，然后 slow++
//   非零元素按原顺序被换到前面，0 自然被挤到后面
//
// 示例过程 [0,1,0,3,12]
//   fast=0：是 0，跳过
//   fast=1：1≠0，交换 0 和 1  → [1,0,0,3,12]，slow=1
//   fast=2：是 0，跳过
//   fast=3：3≠0，交换 0 和 3  → [1,3,0,0,12]，slow=2
//   fast=4：12≠0，交换 0 和 12 → [1,3,12,0,0]，slow=3
//   结果 [1,3,12,0,0]
public class 移动零_283 {
    static class Solution{
        static void removeZero(int[] nums){
            int slow=0,fast=0;
            while(fast<nums.length){
                // 仅在nums[fast]不为0时，slow前进，交换位置。
                if(nums[fast]!=0){
                    int temp=nums[slow];
                    nums[slow]=nums[fast];
                    nums[fast]=temp;
                    slow++;
                }
                fast++;
            }
            }

        }

        public static void main(String[] args){
            int[] test=new int[]{0,1,0,3,12};
            removeZero(test);
            System.out.println(Arrays.toString(test));
        }
    }
}
