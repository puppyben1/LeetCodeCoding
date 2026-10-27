package 滑动窗口;

import com.sun.source.tree.WhileLoopTree;

import java.util.*;

// 3. 无重复字符的最长子串
// 题：给定一个字符串 s，找出其中不含有重复字符的最长子串的长度
// 示例：
//   "abcabcbb" → 3   （"abc"）
//   "bbbbb"    → 1   （"b"）
//   "pwwkew"   → 3   （"wke"）
//
// 思路：滑动窗口 [slow, fast)，用 HashSet 记录窗口内的字符
//   fast 指向"准备加入窗口"的字符：
//     不在窗口 → 加入，更新 res = max(res, fast-slow+1)，fast++
//     已在窗口 → 左边界右移：移除 s[slow]，slow++（直到能容纳 s[fast]）
//   窗口内始终无重复，res 记录出现过的最大长度
//   时间 O(n)，slow / fast 各自最多走 n 步；空间 O(字符集大小)
//
// 示例过程 "abcabcbb"：
//   fast=0 'a' → 窗口 "a"     len=1
//   fast=1 'b' → 窗口 "ab"    len=2
//   fast=2 'c' → 窗口 "abc"   len=3
//   fast=3 'a' 重复 → 移除 s[0]='a'，slow=1 → 窗口 "bc"
//           再试 'a' → 加入，窗口 "bca"   len=3
//   fast=4 'b' 重复 → 移除 s[1]='b'，slow=2 → 窗口 "ca"
//           加入 'b'，窗口 "cab"          len=3
//   ... 后面的窗口长度都不超过 3
//   答案 3
public class 无重复字符的最长子串_3 {
    static class Solution{
        static int lengthOfLongestSubstring(String s){
            int res=0;
            Set<Character> set=new HashSet<>();
            int slow=0,fast=0;
            while(fast<s.length()){
                if(!set.contains(s.charAt(fast))){
                    set.add(s.charAt(fast));
                    res=Math.max(res,fast-slow+1);
                    fast++;
                }else{
                    set.remove(s.charAt(slow));
                    slow++;
                }
            }return res;
            int res = 0;

        }
        public static void main(String[] args){
            String s1="abcabcbb";
            System.out.println(lengthOfLongestSubstring(s1));
        }
    }
}
