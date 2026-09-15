package 链表;

//给定一个包含 n + 1 个整数的数组 nums，其数字都在 [1, n] 范围内（包括 1 和 n），
//可知至少存在一个重复的整数。假设 nums 只有一个重复的整数，返回这个重复的数。
//要求：不修改数组 nums，且只用常量级 O(1) 的额外空间。

//思路：把下标当成节点，nums[i] 当成 i -> nums[i] 的一条有向边。
//下标范围是 0..n（共 n+1 个），值域是 1..n，所以：
//  1) 任何下标 i 都有出边（nums[i] 一定落在 0..n 里）；
//  2) 没有任何边指向 0（值不会是 0），即 0 入度为 0。
//从 0 出发走，一定进不了 0，又走不到尽头，所以「0 之后必有环」。
//环的入口就是被两个不同的下标同时指向的那个值 —— 也就是重复数。
//于是本题就是 _142 环形链表2，只是 next 指针换成了 nums[i]。
public class 寻找重复数_287 {
    static class Solution{
        //快慢指针（Floyd 判圈）：时间 O(n)，空间 O(1)，不修改原数组。本题最优解。
        static int findDuplicate(int[] nums){
            int slow = 0, fast = 0;
            //第一步：快慢指针在环内相遇
            do{
                slow = nums[slow];
                fast = nums[nums[fast]];
            }while(slow != fast);
            //第二步：slow 回到起点，两人同速前进，再次相遇处即环入口
            slow = 0;
            while(slow != fast){
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow;
        }

        //二分答案：改查「值域」而不是数组。时间 O(n log n)，空间 O(1)，同样不改数组。
        //抽屉原理：1..mid 一共只有 mid 个数，若都不重复，数组中 <= mid 的元素至多 mid 个。
        static int findDuplicate2(int[] nums){
            int lo = 1, hi = nums.length - 1;
            while(lo < hi){
                int mid = lo + (hi - lo) / 2;
                int cnt = 0;
                for(int num : nums){
                    if(num <= mid) cnt++;
                }
                //超过 mid 个，说明 1..mid 里必然有重复，重复数落在左半
                if(cnt > mid) hi = mid;
                else lo = mid + 1;
            }
            return lo;
        }

        //位运算：逐位统计，和 1..n 的每一位比较。时间 O(32n)，空间 O(1)。
        //多出来的那一位就属于重复数（重复数在该位上被多算了一次）。
        static int findDuplicate3(int[] nums){
            int n = nums.length - 1;
            int res = 0;
            for(int b = 0; b < 32; b++){
                int cntNums = 0, cntBase = 0;
                for(int i = 0; i <= n; i++){
                    if(((nums[i] >> b) & 1) == 1) cntNums++;
                }
                for(int i = 1; i <= n; i++){
                    if(((i >> b) & 1) == 1) cntBase++;
                }
                if(cntNums > cntBase) res |= (1 << b);
            }
            return res;
        }

        static void check(int[] nums, int expect){
            int a = findDuplicate(nums);
            int b = findDuplicate2(nums);
            int c = findDuplicate3(nums);
            String s = "期望=" + expect + " 快慢指针=" + a + " 二分答案=" + b + " 位运算=" + c;
            System.out.println((a == expect && b == expect && c == expect ? "通过  " : "失败  ") + s);
        }

        public static void main(String[] args){
            check(new int[]{1, 3, 4, 2, 2}, 2);
            check(new int[]{3, 1, 3, 4, 2}, 3);
            check(new int[]{1, 1}, 1);
            check(new int[]{1, 1, 2}, 1);
            check(new int[]{2, 2, 2, 2, 2}, 2);
        }
    }
}
