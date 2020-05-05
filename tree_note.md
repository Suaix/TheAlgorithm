## 树相关的算法知识

### 二叉树的三种遍历方式：前、中、后序遍历

```java

public class TreeNode{
    TreeNode left;
    TreeNode right;
    int val;
    TreeNode(int x){
        val = x;
    }
}
```

**前序遍历**
```
public void traverseTree(TreeNode root){
    int val = root.val;
    traverseTree(root.left);
    traverseTree(root.right);
}
```

**中序遍历**
```
public void traverseTree(TreeNode root){
    traverseTree(root.left);
    int val = root.val;
    traverseTree(root.right);
}
```

**后序遍历**
```
public void traverseTree(TreeNode root){
    traverseTree(root.left);
    traverseTree(root.right);
    int val = root.val;
}
```

### BST-二叉搜索树

特点：
* 左子树只包含 **小于** 当前节点的数；
* 右子树只包含 **大于** 当前节点的数；
* 所有的左子树和右子树都是二叉搜索树；

问题：判断一个给定的树是否是二叉搜索树

解析一：根据二叉搜索树的特性，我们知道所有根节点的只都是大于左子树的值，而小于右子树的值，且左右子树也都是二叉搜索树。我们可以设计一个递归函数 `helper(node, lower, upper)` 来递归判断，函数表示以 `root` 为根的子树，判断子树中所有节点值是否都在 `（l,r）` 的范围内。如果 root 节点的值 val 不在 (l,r)(l,r) 的范围内说明不满足条件直接返回，否则我们要继续递归调用检查它的左右子树是否满足，如果都满足才说明这是一棵二叉搜索树。

那么根据二叉搜索树的性质，在递归调用左子树时，我们需要把上界 `upper` 改成 `root.val`， 即调用 `helper(root.left, lower, root.val)` ，因为左子树所有节点的值均小于它的根节点。同理递归调用右子树时，我们需要将下界 `lower` 改成 `root.val` ，即调用 `helper(root.right, root.val, upper)` 。

函数递归调用的入口是 `helper(root, -inf, +inf)` ，`inf` 表示一个无穷大的值。

解法如下：
```java
class Solution {
  public boolean helper(TreeNode node, Integer lower, Integer upper) {
    if (node == null) return true;

    int val = node.val;
    if (lower != null && val <= lower) return false;
    if (upper != null && val >= upper) return false;

    if (! helper(node.right, val, upper)) return false;
    if (! helper(node.left, lower, val)) return false;
    return true;
  }

  public boolean isValidBST(TreeNode root) {
    return helper(root, null, null);
  }
}
```

解析二：根据二叉搜索树的特性，可以知道如果按照「中序遍历」二叉搜索树，得到的值构成的序列一定是升序的，如下图所示：

![中序遍历示意图](/images/bst_minddle_trval.gif)

这启示我们在中序遍历的时候检查当前节点的值是否大于前一个中序遍历得到的节点的值即可。如果均大于说明这个序列是升序的，整棵树是二叉搜索树，否则就不是。示例代码如下：

```java
class Solution{
    public boolean isValidBST(TreeNode root){
        Stack<TreeNode> stack = new Stack();
        double inorder = -Double.MAX_VALUE;
        
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop;
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }
}
```
