package 双指针;

// 42. 接雨水
// 题：n 个非负整数表示宽度为 1 的柱子的高度图，求下雨后能接多少雨水
// 示例：
//   [0,1,0,2,1,0,1,3,2,1,2,1] → 6
//   [4,2,0,3,2,5]             → 9
//
// 思路：每个位置能接的水 = min(左边最高, 右边最高) - height[i]（小于 0 记为 0）
//   ① preMax[i]  = 位置 i 左边（不含 i）的最大高度
//   ② nextMax[i] = 位置 i 右边（不含 i）的最大高度
//   ③ 累加 max(0, min(preMax[i], nextMax[i]) - height[i])
//   时间 O(n)，空间 O(n)
//   备注：也可以用双指针把空间降到 O(1)（每次先处理较矮的一侧）
//
// 示例过程 [4,2,0,3,2,5]
//   preMax  = [0,4,4,4,4,4]
//   nextMax = [5,5,5,5,5,0]
//   i=1：min(4,5)-2 = 2
//   i=2：min(4,5)-0 = 4
//   i=3：min(4,5)-3 = 1
//   i=4：min(4,5)-2 = 2
//   合计 2+4+1+2 = 9
public class 接雨水_42 {
    public static class Solution{
        static int trap(int[] height){
            int res=0;
            int[] preMax=new int[height.length];
            int[] nextMax=new int[height.length];
            for(int i=1;i<height.length;i++){
                preMax[i]=Math.max(preMax[i-1],height[i-1]);
            }
            for(int i=height.length-2;i>=0;i--){
                nextMax[i]=Math.max(nextMax[i+1],height[i+1]);
            }
            for(int i=0;i<height.length;i++){
                if(Math.min(preMax[i],nextMax[i])-height[i]>0) res+=Math.min(preMax[i],nextMax[i])-height[i];
            }return res;
        }
        public static void main(String[] args){
            int[] height=new int[]{4,2,0,3,2,5};
            System.out.println(trap(height));
        }
    }
}
