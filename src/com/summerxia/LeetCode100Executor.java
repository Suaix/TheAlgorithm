package com.summerxia;

import com.summerxia.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LeetCode100Executor implements AlgorithmsExecutor{

    @Override
    public void execAlgorithmCase() {
        ListNode<Integer> head = new ListNode<>(1);
        ListNode<Integer> n1 = new ListNode<>(2);
        head.next = n1;
        ListNode<Integer> n2 = new ListNode<>(2);
        n1.next = n2;
        ListNode<Integer> n3 = new ListNode<>(3);
        n2.next = n3;
        ListNode<Integer> n4 = new ListNode<>(3);
        n3.next = n4;
        ListNode<Integer> result = deleteDuplication(head);
        while (result != null){
            System.out.println(result.val);
            result = result.next;
        }
//        printOneToMax(3);
//        int[] nums = new int[]{10,11,12,7,8,9,6,5};
//        int index = findNumInRotateArray(nums, 4);
//        System.out.println(index);
    }

    private ListNode<Integer> deleteDuplication(ListNode<Integer> head){
        Map<Integer, Boolean> map = new HashMap<>();
        ListNode<Integer> temp = head;
        while (temp != null){
            if (map.containsKey(temp.val)){
                map.put(temp.val, true);
            } else {
                map.put(temp.val, false);
            }
            temp = temp.next;
        }
        ListNode<Integer> nullNode = new ListNode<>(null);
        nullNode.next = head;
        ListNode<Integer> preNode = nullNode;
        ListNode<Integer> nextNode = head;
        while (preNode != null && nextNode != null){
            if (map.get(nextNode.val)){
                nextNode = nextNode.next;
                preNode.next = nextNode;
            } else {
                nextNode = nextNode.next;
                preNode = preNode.next;
            }
        }
        return nullNode.next;
    }

    private void deleteNode(ListNode head, ListNode deleteNode){
        if (head == null || deleteNode == null)
            return;
        if (deleteNode.next != null){
            ListNode next = deleteNode.next;
            deleteNode.val = next.val;
            deleteNode.next = next.next;
        } else {
            if (head == deleteNode){

            }
        }
    }

    private void printOneToMax(int n){
        if (n <= 0){
            return;
        }
        char[] num = new char[n];
        printOneToMax(num, 0);
    }

    private void printOneToMax(char[] num, int digit) {
        if (digit == num.length){
            printNum(num);
            return;
        }
        for (int i = 0; i < 10; i++) {
            num[digit] = (char) (i + '0');
            printOneToMax(num, digit+1);
        }
    }

    private void printNum(char[] num) {
        int index = 0;
        while (index < num.length && num[index] == '0'){
            index++;
        }
        while (index < num.length){
            System.out.print(num[index++]);
        }
        System.out.println();
    }

    private Map<Integer, Integer> indexForInOrders = new HashMap<>();
    /**
     * 重构
     * @param pre 二叉树前序遍历结果
     * @param in 二叉树中序遍历结果
     * @return 重建后的二叉树
     */
    private TreeNode reConstructBinaryTree(int[] pre, int[] in){
        for (int i = 0; i < in.length; i++) {
            indexForInOrders.put(in[i], i);
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
    }

    /**
     * 重建二叉树
     * @param pre 二叉树前序遍历结果
     * @param preL 二叉树最左侧index
     * @param preR 二叉树最右侧index
     * @param inL 在中序遍历列表中的左侧角标
     * @return
     */
    private TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL){
        if (preL > preR)
            return null;
        TreeNode root = new TreeNode(pre[preL]);
        int inIndex = indexForInOrders.get(root.val);
        int leftSize = inIndex - inL;
        // 在前序遍历数组中获取根节点左子树
        root.left = reConstructBinaryTree(pre, preL + 1, preL + leftSize, inL);
        root.right = reConstructBinaryTree(pre, preL + leftSize + 1, preR, inL + leftSize + 1);
        return root;
    }

    private int findNumInRotateArray(int[] nums, int target){
        return findNum(nums, 0, nums.length - 1, target);
    }

    private int findNum(int[] nums, int l, int h, int target) {
        Integer.bitCount(l);
        if (l > h){
            return -1;
        }
        int m = l + (h - l) / 2;
        if (target == nums[m]){
            return m;
        } else {
            int index = findNum(nums, l, m - 1, target);
            if (index != -1){
                return index;
            }
            return findNum(nums, m + 1, h, target);
        }
    }


    /**
     * 最长回文子串
     */
    private String longestPalindrome(String s){
        return s;
    }

    /**
     * 寻找两个正序数组的中位数
     */
    private double findMedianSortedArrays(int[] nums1, int[] nums2){
        int totalLength = nums1.length + nums2.length;
        int n1=0, n2=0;
        int median = totalLength / 2;
        int firstNum = 0, secondNum = 0;
        boolean isEven = totalLength % 2 == 0;
        int temp;
        for (int i = 0; i < totalLength; i++) {
            if (n1 >= nums1.length){
                temp = nums2[n2];
                n2++;
            } else if (n2 >= nums2.length){
                temp = nums1[n1];
                n1++;
            } else if (nums1[n1] >= nums2[n2]) {
                temp = nums2[n2];
                n2++;
            } else {
                temp = nums1[n1];
                n1++;
            }
            if (isEven){
                // 两个数组总共是偶数位个数字，需要取中间两个数字
                if (i == median){
                    secondNum = temp;
                    break;
                } else if (i == median - 1){
                    firstNum = temp;
                }
            } else {
                // 两个数组总共是奇数位个数字，只需取中间一个数字
                if (i == median) {
                    firstNum = temp;
                    secondNum = temp;
                    break;
                }
            }
        }
        return (firstNum + secondNum) / 2.0d;
    }

    /**
     * 无重复字符的最长子串
     * @param s
     * @return
     */
    private int lengthOfLongestSubstring(String s){
        char[] chars = s.toCharArray();
        int length = 0;
        Map<Character, Integer> temp = new HashMap<>();
        int start = 0, end = 0;
        char c;
        for (; end < chars.length; end++) {
            c = chars[end];
            if (temp.containsKey(c)){
                length = Math.max(length, end - start);
                start = Math.max(start, temp.get(c) + 1);
            }
            temp.put(c, end);
        }
        length = Math.max(length, end - start);
        return length;
    }

    /**
     * 两数相加
     * @param nums
     * @param target
     * @return
     */
    private int[] twoSum(int[] nums, int target){
        int[] result = new int[2];
        HashMap<Integer, Integer> temp = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int key = target - nums[i];
            if(temp.containsKey(key)){
                result[0] = i;
                result[1] = temp.get(key);
                return result;
            }
            temp.put(nums[i], i);
        }
        return result;
    }
}
