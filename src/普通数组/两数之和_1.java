package 普通数组;
import java.util.*;
//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target 的两个整数，
//并返回它们的数组下标。你可以假设每种输入只会对应一个答案，并且不能重复使用数组中的同一个元素。
public class 两数之和_1 {
    static class Solution{

        static int[] twoSum(int[] nums,int target){
            Map<Integer,Integer> map=new HashMap<>();
            for(int i=0;i<nums.length;i++){
                int res=target-nums[i];
                if(map.containsKey(res)) return new int[]{i,map.get(res)};
                map.put(nums[i],i);
            }return new int[]{-1,-1};
        }
        public static void main(String[] args){
            int[] test=new int[]{1,2,3,4,5};
            System.out.println(Arrays.toString(twoSum(test,6)));


        }
    }
}
