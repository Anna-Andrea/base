package lind001.lintcode.tree;

import lind001.jds.exception.JDSException;
import lind001.jds.queue.LinkQueue;
import lind001.jds.tree.BinaryTree;
import lind001.jds.tree.TreeNode;

/**
 * 二叉树（KY数据结构题）
 * @author ld
 *
 */
public class Tree {
	/**
	 * 非递归法求二叉树高度
	 * p122-5
	 * @param tree
	 * @return
	 * @throws JDSException 
	 */
	public static int getHeight(BinaryTree tree) throws JDSException {
		int result = 0;
		TreeNode root = tree.root;
		root.height = 1;
		LinkQueue queue = new LinkQueue(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.getHead();
			queue.remove();
			if (node.lchild != null) {
				node.lchild.height = node.height + 1;
				// 树的高度为层次遍历最新叶子节点的层数
				result = node.lchild.height;
				queue.insert(node.lchild);
			}
			if (node.rchild != null) {
				node.rchild.height = node.height + 1;
				result = node.rchild.height;
				queue.insert(node.rchild);
			}
		}
		return result;
	}
	
	/**
	 * 递归法求二叉树高度（拓展）
	 * Get-Height-By-Recursion
	 * @param root
	 * @return
	 */
	public static int getHeightRE(TreeNode root) {
		// 递归出口：为叶子节点
		if (root.isLeaf()) {
			return 1;
		} else if (root.isFull()) {
			return Math.max(getHeightRE(root.lchild), getHeightRE(root.rchild)) + 1;
		} else if (root.lchild != null) {
			return getHeightRE(root.lchild) +1;
		} else {
			return getHeightRE(root.rchild) +1;
		}
	}
	
	/**
	 * 是否为完全二叉树（叶子节点只存在于最低两层等）
	 * Is-Complete-Binary-Tree
	 * p122-7
	 * @param tree
	 * @return
	 */
	public static boolean isCBT(BinaryTree tree) {
		return false;
	}
	
	/**
	 * 计算二叉树中双分支节点数目
	 * p122-8
	 * @param tree
	 * @return
	 * @throws JDSException 
	 */
	public static int calFullNode(BinaryTree tree) throws JDSException {
		int count = 0;
		LinkQueue queue = new LinkQueue(tree.root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.getHead();
			queue.remove();
			if (node.isFull()) {
				count++;
			}
			if (node.lchild != null) {
				queue.insert(node.lchild);
			}
			if (node.rchild != null) {
				queue.insert(node.rchild);
			}
		}
		return count;
	}
	
	/**交换树B的所有节点的左右子树
	 * @param root
	 * @return
	 */
	public static void exchange(TreeNode root) {
		// 递归出口：待交换节点的孩子节点非满节点
		if (!root.lchild.isFull() && !root.rchild.isFull()) {
			// 交换左右孩子，需要通过current暂存节点
			TreeNode current = root.lchild;
			root.lchild = root.rchild;
			root.rchild = current;
		}
	}
	
	
	/**
	 * 删除以x为根的子树（由于未限定入参为二叉排序树，所以使用层次遍历，时间复杂度为O(n)，而非BST的O(logn)）
	 * p122-11
	 * @param tree
	 * @param x
	 * @return
	 * @throws JDSException 
	 */
	public static BinaryTree removeSubtree(BinaryTree tree, int x) throws JDSException {
		LinkQueue queue = new LinkQueue(tree.root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.getHead();
			queue.remove();
			if (node.key == x) {
				// delete
				node.lchild = null;
				node.rchild = null;
			}
			if (node.lchild != null) {
				queue.insert(node.lchild);
			}
			if (node.rchild != null) {
				queue.insert(node.rchild);
			}
		}
		return tree;
	}
	
}
