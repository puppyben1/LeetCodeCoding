package 滑动窗口;

// 76. 最小覆盖子串
// 题：返回 s 中涵盖 t 所有字符的最短子串；不存在则返回空字符串
//     重复字符按数量算（t 里有 2 个 'a'，窗口里也至少要 2 个）
// 示例：
//   s = "ADOBECODEBANC", t = "ABC" → "BANC"
//   s = "a", t = "a"               → "a"
//   s = "a", t = "aa"              → ""
//
// 思路：滑动窗口 + 计数
//   need[c]   记录 t 中每个字符需要多少个
//   window[c] 记录当前窗口里每个字符有多少个
//   valid     记录"数量已满足"的字符种数；valid == required 时窗口覆盖了 t
//   ① 右指针扩张：把 s[right] 放进窗口，更新 window 和 valid
//   ② 当 valid == required：先尝试更新答案（长度更短就记录），
//      然后左指针右移收缩，直到窗口不再覆盖 t
//   ③ 重复 ①② 直到 right 走到末尾
//   时间 O(|s| + |t|)，空间 O(字符集)
//
// 示例过程 s = "ADOBECODEBANC", t = "ABC"（need: A=1, B=1, C=1，required=3）
//   扩张到 "ADOBEC"（[0,6)）：A、B、C 都满足，valid=3 → 记录长度 6
//   收缩：去掉 A → "DOBEC"，valid=2，停止
//   继续扩张到 "DOBECODEBA"（[1,11)）：长度 10，不更新
//   收缩：去掉 D、O、B、E、C → 窗口 "ODEBA"，C 缺失，停止
//   扩张到 C（下标 12）：窗口 "ODEBANC"，长度 7
//   收缩：去掉 O → "DEBANC"(6) → 去掉 D → "EBANC"(5) → 去掉 E → "BANC"(4)
//   再去掉 B 就不再覆盖，停止
//   最短答案 "BANC"
public class 最小覆盖子串_76 {
    static class Solution {
        static String minWindow(String s, String t) {
            if (s.length() < t.length()) return "";

            int[] need = new int[128];
            int[] window = new int[128];
            int required = 0;                  // t 中不同字符的种数
            for (char c : t.toCharArray()) {
                if (need[c] == 0) required++;
                need[c]++;
            }

            int left = 0;
            int valid = 0;                     // 已满足数量的字符种数
            int start = 0;
            int minLen = Integer.MAX_VALUE;

            for (int right = 0; right < s.length(); right++) {
                // ① 扩张
                char c = s.charAt(right);
                if (need[c] > 0) {
                    window[c]++;
                    if (window[c] == need[c]) valid++;
                }

                // ② 已覆盖，尝试收缩
                while (valid == required) {
                    if (right - left + 1 < minLen) {
                        start = left;
                        minLen = right - left + 1;
                    }
                    char d = s.charAt(left);
                    left++;
                    if (need[d] > 0) {
                        if (window[d] == need[d]) valid--;
                        window[d]--;
                    }
                }
            }

            return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
        }

        public static void main(String[] args) {
            System.out.println(minWindow("ADOBECODEBANC", "ABC")); // BANC
            System.out.println(minWindow("a", "a"));               // a
            System.out.println("[" + minWindow("a", "aa") + "]");  // []
        }
    }
}
