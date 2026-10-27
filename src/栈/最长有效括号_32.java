package 栈;

import java.util.*;

// 32. 最长有效括号
// 题：给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
// 示例：
//   "(()"    → 2   （"()"）
//   ")()())" → 4   （"()()"）
//   ""       → 0
//
// 思路：栈里存"下标"，栈底放一个"参照下标"
//   初始 push(-1)，表示最后一个没有被匹配的位置
//   遇 '(' → 压入它的下标
//   遇 ')' → 先弹出栈顶（相当于匹配掉一个左括号）
//             若栈空了 → 这个 ')' 无法匹配，把它自己的下标压入，作为新的参照
//             否则     → i - 栈顶 就是当前有效长度，更新 max
//
// 示例过程 ")()())"：
//   i=0 ')' → 栈[-1] 弹出后为空，压入 0        stack=[0]      max=0
//   i=1 '(' → 压入 1                           stack=[0,1]    max=0
//   i=2 ')' → 弹 1，栈非空 → 2-0               stack=[0]      max=2
//   i=3 '(' → 压入 3                           stack=[0,3]    max=2
//   i=4 ')' → 弹 3，栈非空 → 4-0               stack=[0]      max=4
//   i=5 ')' → 弹 0，栈空 → 压入 5              stack=[5]      max=4
//   return 4
public class 最长有效括号_32 {
    static class Solution {
        static int longestValidParentheses(String s) {
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(-1);
            int max = 0;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(i);
                    } else {
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
            return max;
        }

        public static void main(String[] args) {
            System.out.println(longestValidParentheses("(()"));      // 2
            System.out.println(longestValidParentheses(")()())"));   // 4
            System.out.println(longestValidParentheses(""));         // 0
        }
    }
}
