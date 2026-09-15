package 链表;

import java.util.*;

//给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，
//找到矩阵中第 k 小的元素。注意是排序后的第 k 小元素，而不是第 k 个不同的元素。
//
//思路：矩阵的每一行本身就是一条升序链表，所以本题等价于
//「合并 n 条升序链表，取合并后的第 k 个」，即 _23 的同一套骨架。
//堆里只维护「各行当前第一个还没被取走的元素」，全局最小值必定在其中。
public class 有序矩阵中第K小的元素_378 {
    static class Solution{
        //堆解法：时间 O((n + k) log n)，空间 O(n)。只用到「行有序」。
        static int kthSmallest(int[][] matrix, int k){
            if(matrix.length == 0 || matrix[0].length == 0) return -1;
            //二元组 (值, 行i, 列j)，i、j 用于定位同行的下一个元素
            PriorityQueue<int[]> pq = new PriorityQueue<>(
                    (a, b) -> Integer.compare(a[0], b[0])
            );
            //初始化：每一行的第一个元素入堆
            for(int i = 0; i < matrix.length; i++){
                pq.offer(new int[]{matrix[i][0], i, 0});
            }
            int res = -1;
            while(!pq.isEmpty() && k > 0){
                int[] cur = pq.poll();
                res = cur[0];
                k--;
                int i = cur[1], j = cur[2];
                //同行的下一个元素补位
                if(j + 1 < matrix[i].length){
                    pq.offer(new int[]{matrix[i][j + 1], i, j + 1});
                }
            }
            return res;
        }

        //二分答案（值域二分）：时间 O((n + m) log(值域))，与 k 无关。
        //这一版才用到「列有序」。k 接近 n*m 时比堆解法稳。
        static int kthSmallest2(int[][] matrix, int k){
            int n = matrix.length, m = matrix[0].length;
            int lo = matrix[0][0], hi = matrix[n - 1][m - 1];
            while(lo < hi){
                int mid = lo + (hi - lo) / 2;
                if(countLE(matrix, mid) < k) lo = mid + 1;
                else hi = mid;
            }
            return lo;
        }

        //从左下角出发，统计矩阵中 <= x 的元素个数
        static int countLE(int[][] matrix, int x){
            int i = matrix.length - 1, j = 0, cnt = 0;
            while(i >= 0 && j < matrix[0].length){
                if(matrix[i][j] <= x){
                    cnt += i + 1;//列递增：这一列 0..i 全 <= x，一次数掉 i+1 个
                    j++;
                }else{
                    i--;//行递增：这一行右边的更大，往上走
                }
            }
            return cnt;
        }

        public static void main(String[] args){
            int[][] matrix = new int[][]{
                    {1, 5, 9},
                    {10, 11, 13},
                    {12, 13, 15}

            };
            for(int k = 1; k <= 9; k++){
                System.out.println("k=" + k
                        + " 堆=" + kthSmallest(matrix, k)
                        + " 二分=" + kthSmallest2(matrix, k));
            }
        }
    }
}
