package lind001.jds.tree;

import lind001.jds.exception.JDSException;

/**
 * 二叉搜索树BST测试用例
 * 
 * @author lind001
 * @date 2018/09/26
 */
public class BSTApp {
    public static void main(String[] args) throws JDSException {
        BinarySortTree bst = new BinarySortTree();
        int[] keys = {40, 20, 54, 87, 23, 41, 15, 16, 74, 14};
        for (int i = 0; i < keys.length; i++) {
            TreeNode node = new TreeNode(keys[i]);
            bst.insert(node);
        }
        bst.inOrder();
        // delete leaf node
        System.out.println("");
        bst.delete(16);
        bst.inOrder();
        // delete full node
        System.out.println("");
        bst.delete(54);
        bst.inOrder();
        // delete node which has one child
        System.out.println("");
        bst.delete(15); // LL
        bst.inOrder();
    }
}
