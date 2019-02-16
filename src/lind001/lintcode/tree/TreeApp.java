package lind001.lintcode.tree;

import java.util.Vector;

import lind001.jds.exception.JDSException;
import lind001.jds.tree.BinarySortTree;
import lind001.jds.tree.BinaryTree;
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
	    System.out.println("\n");
	    height = Tree.getHeightRE(bst.root); //expect 4
	    System.out.print(height);
	    // ③计算双分支结点个数
	    System.out.println("\n");
	    int result = Tree.calFullNode(bst);
	    System.out.print(result);//expect 4
	    // ④交换树的所有满节点 的左右孩子
	    System.out.println("\n");
	    Tree.exchange(bst.root);
	    bst.levelOrder();
	    // ⑤删除以x为根的子树
	    System.out.println("\n");
	    BinaryTree bt = Tree.removeSubtree(bst, 15);
	    bt.levelOrder();
	    // ⑥是否为完全二叉树
	    System.out.println("");
	    System.out.print(Tree.isCBT(bt)+"  ");
	    System.out.print(Tree.isCBTEasyImpl(bt));
	    Tree.removeSubtree(bt, 74);
	    System.out.println("");
	    System.out.print(Tree.isCBT(bt)+"  ");
	    System.out.print(Tree.isCBTEasyImpl(bt));
	    // ⑦获取关键字为x节点的所有祖先（倒序排序）
	    System.out.println("\n");
	    Vector<TreeNode> ancestors = Tree.getAncestors(bt, 41);
	    for (TreeNode node: ancestors) {
	    	if (ancestors.indexOf(node) != ancestors.size() -1) {
	    		System.out.print(node.key+"->");
	    	} else {
	    		System.out.print(node.key);
	    	}
	    }
	    // ⑧寻找p、q最近公共祖先
	    System.out.println("\n");
	    TreeNode r = Tree.findCommonAncestor(bt, new TreeNode(87), new TreeNode(23));
	    System.out.print(r.key+"  ");
	    r = Tree.findCommonAncestor(bt, new TreeNode(41), new TreeNode(20));
	    System.out.print(r.key);

	}
}
