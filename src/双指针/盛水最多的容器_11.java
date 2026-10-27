package 双指针;
import java.util.*;
// 11. 盛水最多的容器
// 题：height[i] 表示第 i 条垂线的高度，选两条线与 x 轴围成容器，求最大水量
//     面积 = min(height[left], height[right]) * (right - left)
// 示例：
//   [1,8,6,2,5,4,8,3,7] → 49（选下标 1 的 8 和下标 8 的 7）
//   [1,1]               → 1
//
// 思路：双指针从两端向中间收
//   每次算当前面积并更新 res，然后移动较矮的一侧
//   原因：宽度只会变小，若移动高的一侧，min 不会变大 → 面积不可能增加
//         只有移动矮的一侧，才可能遇到更高的柱子，从而得到更大面积
//
// 示例过程 [1,8,6,2,5,4,8,3,7]
//   l=0,r=8：min(1,7)*8 = 8   → 移动 l
//   l=1,r=8：min(8,7)*7 = 49  → 移动 r
//   l=1,r=7：min(8,3)*6 = 18  → 移动 r
//   l=1,r=6：min(8,8)*5 = 40  → 移动 l
//   后续面积都更小，最大 49
public class 盛水最多的容器_11 {
    static class Solution{
       static int maxArea(int[] height){
           int left=0,right=height.length-1;
           int res=0;
           while(left<right){
               res=Math.max(res,Math.min(height[left],height[right])*(right-left));
               if(height[left]<=height[right]) left++;
               else right--;
           }return res;
       }

       public static void main(String[] args){
           int[] test=new int[]{1,8,6,2,5,4,8,3,7};
           System.out.println(maxArea(test));
       }
    }
}
