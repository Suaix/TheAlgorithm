package com.summerxia.tree;

import com.summerxia.AlgorithmsExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeExecutor implements AlgorithmsExecutor {

    @Override
    public void execAlgorithmCase() {
//        testInorderTraversal();
//        testValideBST();
        testBaseTraversal();
    }

    private void testBaseTraversal(){
        TreeNode root = new TreeNode(5);
        TreeNode t1 = new TreeNode(4);
        TreeNode t2 = new TreeNode(7);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(6);
        TreeNode t5 = new TreeNode(8);
        root.left = t1;
        root.right = t2;
        t1.left = t3;
        t2.left = t4;
        t2.right = t5;
        baseTreeTraversal(root);
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

    /**
     * 递归遍历二叉树
     * @param root
     */
    private void baseTreeTraversal(TreeNode root){
        if (root == null){
            return;
        }
        // 先获取值：前序遍历
        int val = root.val;
        System.out.println(val);
        baseTreeTraversal(root.left);
        // 先遍历左侧再取值：中序遍历
        // int val = root.val;
        baseTreeTraversal(root.right);
        // 最后取值：后序遍历
        // int val = root.val;
    }

    /**
     * 迭代遍历二叉树
     * @param root
     */
    private void traversalTree(TreeNode root){
        List<TreeNode> list = new ArrayList<>();

        while (!list.isEmpty() || root != null){
            while (root != null){
                list.add(root);
                root = root.left;
            }
            root = list.remove(list.size()-1);
            // 这里处理值为：中序遍历；
            int val = root.val;
            root = root.right;
        }
    }
}
