package lind001.lintcode.tree;

import lind001.jds.exception.JDSException;
import lind001.jds.tree.BinarySortTree;
import lind001.jds.tree.TreeNode;

/**
 * KY二叉树数据结构题测试用例
 * @author ld
 *
 */
public class TreeApp {
	public static void main(String[] args) throws JDSException {
		// ①非递归算法求二叉树高度
	    BinarySortTree bst = new BinarySortTree();
	    int[] keys = {40, 20, 54, 87, 23, 41, 15, 16, 74, 14};
	    for (int i = 0; i < keys.length; i++) {
	        TreeNode node = new TreeNode(keys[i]);
	        bst.insert(node);
	    }
	    // 二叉排序树继承二叉树，所以入参类型符合要求
	    int height = Tree.getHeight(bst); //expect 4
	    System.out.print(height);
	    // ②递归算法求二叉树高度（递归自顶向下，访问节点不止一次，效率较低）
	    System.out.println("");
	    height = Tree.getHeightRE(bst.root); //expect 4
	    System.out.print(height);
	    // ③删除以x为根的子树
	    System.out.println("");
	    bst.levelOrder();
//	    Tree.removeSubtree(bst, 15);
//	    System.out.println("");
//	    bst.levelOrder();
	    // ⑤计算双分支结点个数
	    System.out.println("");
	    int result = Tree.calFullNode(bst);
	    System.out.print(result);//expect 4

	}
}
