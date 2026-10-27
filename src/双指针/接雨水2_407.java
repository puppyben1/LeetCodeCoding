package 双指针;

import java.util.*;

// 407. 接雨水 II
// 题：m x n 的二维高度图（每个格子的高度），求下雨后最多能接多少体积的雨水
// 示例：
//   [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]               → 4
//   [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]] → 10
//
// 思路：小顶堆 + BFS，从边界向内部"灌水"
//   ① 边界上的格子水一定会流走，接不住水，全部入小顶堆（按高度排序），标记已访问
//   ② 每次弹出当前高度最低的格子，检查它的上下左右邻居：
//        未访问 → 标记访问
//                 邻居比当前矮 → 能接 h - nh 的水
//                 邻居入堆，高度记为 max(h, nh)（相当于水位，木桶的最短板）
//   ③ 从最矮的边界开始一层层向内推进，每个格子第一次被访问时的 h
//      就是它能达到的最高水位（所有通往边界的路径中，最短板的最大值）
//   时间 O(mn log(mn))，空间 O(mn)
//
// 示例过程（第一组，3×6）：
//   把 14 个边界格子全部入堆
//   内部只有 4 个格子：(1,1)=2、(1,2)=1、(1,3)=3、(1,4)=2
//   从最低的边界向内扩展，每个内部格子第一次被访问时确定水位：
//     (1,1)：水位 3（左/下/上都通向高度 3 的边界）→ 接 3-2 = 1
//     (1,2)：水位 3（四周是 2、3、3、3）          → 接 3-1 = 2
//     (1,3)：水位 3（上方边界高度只有 1，但被挡住了）→ 接 3-3 = 0
//     (1,4)：水位 3（上/下/左都是 3）              → 接 3-2 = 1
//   合计 1 + 2 + 0 + 1 = 4
public class 接雨水2_407 {
    static class Solution {
        static int trapRainWater(int[][] heightMap) {
            int m = heightMap.length;
            int n = heightMap[0].length;
            if (m < 3 || n < 3) return 0;   // 没有内部格子，接不住水

            boolean[][] visited = new boolean[m][n];
            // 小顶堆：{行, 列, 高度}，按高度升序
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

            // ① 边界全部入堆
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        pq.offer(new int[]{i, j, heightMap[i][j]});
                        visited[i][j] = true;
                    }
                }
            }

            int water = 0;
            int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            // ② 从最低处向内部扩展
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int r = cur[0], c = cur[1], h = cur[2];

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || visited[nr][nc]) continue;

                    visited[nr][nc] = true;
                    int nh = heightMap[nr][nc];
                    if (nh < h) water += h - nh;              // 邻居矮 → 能积水
                    pq.offer(new int[]{nr, nc, Math.max(h, nh)});  // 水位取较短板
                }
            }
            return water;
        }

        public static void main(String[] args) {
            int[][] map1 = {
                    {1, 4, 3, 1, 3, 2},
                    {3, 2, 1, 3, 2, 4},
                    {2, 3, 3, 2, 3, 1}
            };
            System.out.println(trapRainWater(map1));   // 4

            int[][] map2 = {
                    {3, 3, 3, 3, 3},
                    {3, 2, 2, 2, 3},
                    {3, 2, 1, 2, 3},
                    {3, 2, 2, 2, 3},
                    {3, 3, 3, 3, 3}
            };
            System.out.println(trapRainWater(map2));   // 10
        }
    }
}
