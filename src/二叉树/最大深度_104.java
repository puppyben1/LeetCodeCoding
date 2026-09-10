package 二叉树;
import java.util.*;
public class 最大深度_104 {
    static class Solution{
                static class TreeNode{
            int val;
            TreeNode left,right;
            public TreeNode(){};
            public TreeNode(int val){this.val=val;};
            public TreeNode(int val,TreeNode left,TreeNode right){
                this.val=val;
                this.left=left;
                this.right=right;
            }
        }

        static TreeNode createTree(Integer[] vals){
            if(vals.length==0) return null;
            Deque<TreeNode> deque=new ArrayDeque<>();
            TreeNode root=new TreeNode(vals[0]);
            deque.addFirst(root);
            int i=1;
            while(!deque.isEmpty()&&i<vals.length){
                TreeNode node=deque.pollFirst();
                if(i<vals.length&&vals[i]!=null){
                    node.left=new TreeNode(vals[i]);
                    deque.offerLast(node.left);
                }
                i++;
                if(i<vals.length&&vals[i]!=null){
                    node.right=new TreeNode(vals[i]);
                    deque.offerLast(node.right);
                }
                i++;
            }
            return root;
        }

        static void printTree(TreeNode root){
            if(root==null) {
                System.out.println("null");
                return;
            }
            List<String> res=new ArrayList<>();
            Deque<TreeNode> deque=new LinkedList<>();
            deque.offerLast(root);
            while(!deque.isEmpty()){
                TreeNode node=deque.pollFirst();
                if(node!=null){
                    res.add(String.valueOf(node.val));
                    deque.offerLast(node.left);
                    deque.offerLast(node.right);
                }
                else{
                    res.add("null");
                }
            }
            int last=res.size()-1;
            while(last>=0&&res.get(last).equals("null")){
                last--;
            }
            for(int i=0;i<=last;i++){
                if(i==last) System.out.println(res.get(i));
                else System.out.print(res.get(i)+",");
            }
        }

        static int maxDepth(TreeNode root){
                    if(root==null) return 0;
                    int left=maxDepth(root.left);
                    int right=maxDepth(root.right);
                    return Math.max(left,right)+1;
        }

        public static void main(String[] args){
                    // 例子1：{1, null, 2} —— 只有一个右孩子，深度 2
                    Integer[] res1=new Integer[]{1,null,2};
                    TreeNode root1=createTree(res1);
                    System.out.print("例子1 树结构: ");
                    printTree(root1);
                    System.out.println("最大深度 = " + maxDepth(root1));

                    // 例子2：LeetCode 标准样例 {3,9,20,null,null,15,7} —— 深度 3
                    Integer[] res2=new Integer[]{3,9,20,null,null,15,7};
                    TreeNode root2=createTree(res2);
                    System.out.print("例子2 树结构: ");
                    printTree(root2);
                    System.out.println("最大深度 = " + maxDepth(root2));
        }
    }
}
