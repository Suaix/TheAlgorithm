package com.summerxia.tree;

import com.summerxia.AlgorithmsExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeExecutor implements AlgorithmsExecutor {

    @Override
    public void execAlgorithmCase() {
//        testInorderTraversal();
        testValideBST();
    }

    private void testValideBST() {
        TreeNode root = new TreeNode(10);
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(15);
        root.left = t1;
        root.right = t2;
        TreeNode t3 = new TreeNode(6);
        TreeNode t4 = new TreeNode(20);
        t2.left = t3;
        t2.right = t4;
        boolean validBST = isValidBST(root);
        System.out.println("result:"+validBST);
    }

    /**
     * 是否是有效二叉搜索树 BST：二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root){
        return helper(root, null, null);
    }

    private boolean helper(TreeNode node, Integer lower, Integer upper){
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (!helper(node.left, lower, val)) return false;
        if (!helper(node.right, val, upper)) return false;
        return true;
    }

    private boolean isValidBST2(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        return true;
    }

    private void testInorderTraversal() {
        TreeNode treeNode = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        treeNode.right = t1;
        t1.left = t2;
        inorderTraversal(treeNode);
    }

    /**
     * 将二叉树的值按照中序遍历获取
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root){
        if (root == null){
            return null;
        }
        List<Integer> result = new ArrayList<>();
        traversal(root, result);
        return result;
    }

    private void traversal(TreeNode root, List<Integer> list) {
        if (root.left != null){
            traversal(root.left, list);
        }
        list.add(root.val);
        if (root.right != null){
            traversal(root.right, list);
        }
    }

    private void baseTreeTraversal(TreeNode root){
        if (root == null){
            return;
        }
        // 先获取值：前序遍历
        int val = root.val;
        baseTreeTraversal(root.left);
        // 先遍历左侧再取值：中序遍历
        // int val = root.val;
        baseTreeTraversal(root.right);
        // 最后取值：后序遍历
        // int val = root.val;
    }
}
