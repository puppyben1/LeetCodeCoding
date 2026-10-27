package 栈;
import 链表.合并K个升序链表_23;

import java.util.*;

/*
 * 20. 有效的括号 (Valid Parentheses)
 *
 * 题目:给定一个只包含 '(' ')' '{' '}' '[' ']' 的字符串 s,判断它是否有效。
 * 有效需同时满足:
 *   1) 左括号必须用相同类型的右括号闭合;
 *   2) 左括号必须以正确的顺序闭合;
 *   3) 每个右括号都有一个对应的同类型左括号。
 * 示例:
 *   "()"      -> true
 *   "()[]{}"  -> true
 *   "(]"      -> false
 *   "([)]"    -> false
 *   "{[]}"    -> true
 *
 * 思路:栈 + 哈希表
 *   - map 存「右括号 -> 对应的左括号」,用于快速反查;
 *   - 遇到左括号:直接入栈,等待被闭合;
 *   - 遇到右括号:栈顶必须是它的匹配左括号 -> 弹出;否则(栈空或不匹配)立即返回 false;
 *   - 遍历结束后栈必须为空,否则说明有左括号没闭合。
 *
 * 复杂度:时间 O(n),空间 O(n)。
 * 备注:括号匹配是栈的典型场景 —— 后遇到的左括号必须先被闭合,天然符合 LIFO。
 *      也可不用 map,直接 if/switch 判断右括号对应的左括号。
 */
public class 有效的括号_20 {
    static class Solution{
        static boolean isValid(String s){
            if(s.length()==0) return false;
            Deque<Character> stack=new ArrayDeque<>();
            Map<Character,Character> map=new HashMap<>();
            map.put(')','(');
            map.put('}','{');
            map.put(']','[');
            for(char c:s.toCharArray()){
                if(!map.containsKey(c)) stack.push(c);
                else{
                    if(!stack.isEmpty()&&stack.peek()==map.get(c)) stack.pop();
                    else return false;
                }
            }
            return stack.isEmpty();
        }
        public static void main(String[] args){
            String s="()[]{}";
            System.out.println(isValid(s));
        }
    }
}
