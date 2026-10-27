package 二分查找;

public class 二分查找_704 {
    static class Solution{
        static int search(int[] nums,int target){
            int left = 0,right = nums.length - 1;
            while(left <= right){
                int mid = (left +right) / 2;
                if(nums[mid] < target){
                    left = mid +1;
                }else if(nums[mid] > target){
                    right = mid - 1;
                }else{
                    return mid;
                }
            }
            return -1;
        }

        public static void main(String[] args){
            int[] test=new int[]{1,2,3,4,5};
            System.out.println(search(test,4));
        }
    }
}
