package 双指针;
import java.util.*;
// 15. 三数之和
// 题：找出所有和为 0 且不重复的三元组 [nums[i], nums[j], nums[k]]（下标互不相同）
// 示例：
//   [-1,0,1,2,-1,-4] → [[-1,-1,2],[-1,0,1]]
//   [0,1,1]          → []
//   [0,0,0]          → [[0,0,0]]
//
// 思路：排序 + 固定一个数 + 双指针
//   ① 先排序，把问题变成"有序数组里找两数之和 = -nums[i]"
//   ② i 从左往右固定，j=i+1、k=n-1 从两端向中间夹
//      和 < 目标 → j++；和 > 目标 → k--；相等 → 收集
//   ③ 去重：i 跳过相同值；收集后 j、k 也跳过相邻的相同值
//   ④ nums[i] > 0 时 break（排序后后面都 ≥ 它，不可能再凑出 0）
//
// 示例过程 [-1,0,1,2,-1,-4] → 排序后 [-4,-1,-1,0,1,2]
//   i=0 (-4)：目标 4，j/k 夹完没有命中
//   i=1 (-1)：目标 1
//     (-1,2) 和=1 → 收集 [-1,-1,2]
//     (0,1)  和=1 → 收集 [-1,0,1]
//   i=2 (-1)：与上一个相同，跳过
//   i=3 (0)：目标 0，无命中；i=4 时 nums[i]>0，break
//   结果 [[-1,-1,2],[-1,0,1]]
public class 三数之和_15 {
    static class Solution{
        static List<List<Integer>> ThreeSum(int[] nums){
            List<List<Integer>> res=new ArrayList<>();
            if(nums.length<3) return res;
            Arrays.sort(nums); //注意要排序！
            for(int i=0;i<nums.length-2;i++){
                if(nums[i]>0) break;
                if(i>0&&nums[i-1]==nums[i]) continue;
                int j=i+1,k=nums.length-1;
                while(j<k){
                    int sum=nums[j]+nums[k];
                    if(sum<-nums[i]) j++;
                    else if(sum>-nums[i]) k--;
                    else{
                        res.add(new ArrayList<>(Arrays.asList(nums[i],nums[j],nums[k]))); //重点
                        j++;k--;
                        while(j<k&&nums[j-1]==nums[j]) j++;
                        while(j<k&&nums[k+1]==nums[k]) k--;
                    }
                }
            }
            return res;
        }
        static List<List<Integer>> sumThree(int[] nums){
            List<List<Integer>> res = new ArrayList<>();
            if(nums.length < 3)return res;
            Arrays.sort(nums);
            for(int i=0;i< nums.length;i++){
                if(nums[i] > 0 )break;;
                if(i>0 && nums[i] =nums[i-1]) i++;
                int j=i+1,k= nums.length-1;
                int target=nums[j]+nums[k];
                while(j < k){
                    if(nums[i] < -target){
                        k--;
                    }else if(nums[i] > -target){
                        j++;
                    }else{
                        res.add(new ArrayList<>(nums[i],nums[j],nums[k]));
                        while (j<k)if (nums[j]==nums[j-1])j++;
                        while(j<k) if (nums[k]==nums[k+1])k--;
                    }
                }
            }
        }
        public static void main(String[] args){
            int[] test=new int[]{0,1,1};
            System.out.println(ThreeSum(test));
        }
    }
}
