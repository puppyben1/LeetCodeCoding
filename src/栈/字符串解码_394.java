package 栈;

import java.util.*;

// 394. 字符串解码
// 题：给定一个经过编码的字符串，返回它解码后的字符串。
//     编码规则为 k[encoded_string]，表示方括号内部的字符串正好重复 k 次。
//     输入总是有效的，原始数据不含数字，所有数字只表示重复次数。
// 示例：
//   "3[a]2[bc]"     → "aaabcbc"
//   "3[a2[c]]"      → "accaccacc"
//   "2[abc]3[cd]ef" → "abcabccdcdcdef"
//
// 思路：两个栈
//   countStack 存"待重复的次数"，strStack 存"进入括号前已经拼好的字符串"
//   遇数字  → 累加 num（num*10 处理 "12[a]" 这种多位数）
//   遇 '[' → 把 num 和当前串压栈，然后 num 清零、开一个新串
//   遇 ']' → 弹出次数 k 和之前的串，把当前串重复 k 次接到后面，成为新的当前串
//   遇字母  → 直接拼到当前串
//
// 示例过程 "3[a2[c]]"：
//   读到 3 → num = 3
//   读到 [ → countStack=[3]，strStack=[""]，num=0，cur=""
//   读到 a → cur = "a"
//   读到 2 → num = 2
//   读到 [ → countStack=[3,2]，strStack=["","a"]，num=0，cur=""
//   读到 c → cur = "c"
//   读到 ] → k=2，prev="a"，"a" + 2×"c" = "acc"
//   读到 ] → k=3，prev=""，3×"acc" = "accaccacc"
//   return "accaccacc"
public class 字符串解码_394 {
    static class Solution {
        static String decodeString(String s) {
            Deque<Integer> countStack = new ArrayDeque<>();
            Deque<StringBuilder> strStack = new ArrayDeque<>();
            StringBuilder cur = new StringBuilder();
            int num = 0;

            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    num = num * 10 + (c - '0');
                } else if (c == '[') {
                    countStack.push(num);
                    strStack.push(cur);
                    num = 0;
                    cur = new StringBuilder();
                } else if (c == ']') {
                    int k = countStack.pop();
                    StringBuilder prev = strStack.pop();
                    for (int i = 0; i < k; i++) {
                        prev.append(cur);
                    }
                    cur = prev;
                } else {
                    cur.append(c);
                }
            }
            for(char c : s.toCharArray()){
                if(Character.isDigit(c)){
                    num = num *10 +(c-'0');
                }else if(c =='['){
                    countStack.push(num);
                    strStack.push(cur);
                    num = 0;
                    cur = new StringBuilder();

                }
            }
            return cur.toString();
        }

        public static void main(String[] args) {
            System.out.println(decodeString("3[a2[c]]"));       // accaccacc
            System.out.println(decodeString("2[abc]3[cd]ef"));  // abcabccdcdcdef
            System.out.println(decodeString("10[a]"));          // aaaaaaaaaa
        }
    }
}
