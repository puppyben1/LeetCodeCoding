package 其他;
import java.util.*;
public class 快速排序 {
    static class Solution{
        static int[] QuickSort(int[] nums){
            QS(nums,0,nums.length-1);
            return nums;
        }
        static void QS(int[] nums,int left,int right){
          if(left>=right) return;
          int i=left,j=right;
          int temp=nums[left];
          while(i<j){
              while(i<j&&nums[j]>=temp) j--;
              nums[i]=nums[j];
              while(i<j&&nums[i]<=temp) i++;
              nums[j]=nums[i];
          }
          nums[i]=temp;
          QS(nums,left,i-1);
          QS(nums,i+1,right);
        }




        public static void main(String[] args){
            int[] test1=new int[]{3,2,4,5,1};
            System.out.println(Arrays.toString(QuickSort(test1)));
        }
    }
}
