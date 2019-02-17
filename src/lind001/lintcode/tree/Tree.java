package lind001.lintcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import lind001.jds.exception.JDSException;
import lind001.jds.heap.Heap;
import lind001.jds.queue.LinkQueue;
import lind001.jds.stack.LinkStack;
import lind001.jds.tree.BinaryTree;
import lind001.jds.tree.TreeNode;

/**
 * 二叉树（KY数据结构题）
 * 
 * @author ld
 *
 */
public class Tree {
	/**
	 * 非递归法求二叉树高度 p122-5
	 * 
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
	 * 递归法求二叉树高度（拓展） Get-Height-By-Recursion
	 * 
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
			return getHeightRE(root.lchild) + 1;
		} else {
			return getHeightRE(root.rchild) + 1;
		}
	}

	/**
	 * 是否为完全二叉树（叶子节点只存在于最低两层等） Is-Complete-Binary-Tree p122-7
	 * 
	 * @param tree
	 * @return
	 * @throws JDSException
	 */
	public static boolean isCBT(BinaryTree tree) throws JDSException {
		// Special cases:空树是完全二叉树
		if (tree.isEmpty()) {
			return true;
		}
		// 思路：按照从上至下、从左至右的层次遍历给树节点编号
		// 规则：①当当前节点有孩子节点时，检查同层编号靠前节点（对于每层第一个节点，则是检查上一个节点是否为满节点）是否为满节点
		// 规则：②只有有左孩子节点后才能添加右孩子
		LinkQueue queue = new LinkQueue(tree.root);
		// 不确定入参二叉树有多少个节点，使用集合List
		List<TreeNode> nodeList = new ArrayList<>();
		nodeList.add(tree.root);
		while (!queue.isEmpty()) {
			TreeNode headNode = queue.getHead();
			queue.remove();
			if (headNode.lchild != null) {
				// 不符合规则①
				if (!checkFrontNodes(nodeList, headNode)) {
					return false;
				}
				queue.insert(headNode.lchild);
				nodeList.add(headNode.lchild);
				// 规则②
				if (headNode.rchild != null) {
					queue.insert(headNode.rchild);
					nodeList.add(headNode.rchild);
				}
			} else {
				// 不符合规则②：没有左孩子却有右孩子
				if (headNode.rchild != null) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 规则检查（是否为完全二叉树）
	 * 
	 * @param nodeList
	 * @param node
	 * @return
	 */
	private static boolean checkFrontNodes(List<TreeNode> nodeList, TreeNode node) {
		int serialNo = nodeList.indexOf(node) + 1;
		// 节点所在层数（规定根节点为第0层）
		int grade = (int) Math.floor(Heap.logArithm(2, serialNo));
		int beginIndex = (int)(Math.pow(2, grade) - 1);
		if (beginIndex == serialNo - 1) {
			if (beginIndex - 1 >= 0) {
				if (!nodeList.get(beginIndex-1).isFull()) {
					return false;
				}
			}
		}
		while(beginIndex < serialNo - 1) {
			if (!nodeList.get(beginIndex).isFull()) {
				return false;
			}
			beginIndex ++;
		}
		return true;
	}

	/**
	 * 是否为完全二叉树（以数组作为二叉树存储结构）
	 * @param tree
	 * @return
	 * @throws JDSException 
	 */
	public static boolean isCBTEasyImpl(BinaryTree tree) throws JDSException {
		if (tree.isEmpty()) {
			return true;
		}
		LinkQueue queue = new LinkQueue(tree.root);
		List<TreeNode> treeArray = new ArrayList<>();
		treeArray.add(tree.root);
		// get tree array by level order and check whether it has null element
		TreeNode lastTreeNode = new TreeNode();
		while (!queue.isEmpty()) {
			TreeNode headNode = queue.getHead();
			lastTreeNode = headNode;
			queue.remove();
			if (headNode.lchild != null) {
				queue.insert(headNode.lchild);
				treeArray.add(headNode.lchild);
			}else {
				treeArray.add(headNode.lchild);
			}
			if (headNode.rchild != null) {
				queue.insert(headNode.rchild);
				treeArray.add(headNode.rchild);
			}else {
				treeArray.add(headNode.rchild);
			}
		}
		int endIndex = treeArray.indexOf(lastTreeNode);
		for (int i=0; i<endIndex; i++) {
			if (treeArray.get(i) == null) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 计算二叉树中双分支节点数目 p122-8
	 * 
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

	/**
	 * 交换树B的所有节点的左右子树
	 * 
	 * @param root
	 * @return
	 */
	public static void exchange(TreeNode root) {
		if (root.isFull()) {
			// 交换root的左右孩子
			TreeNode tmp = root.lchild;
			root.lchild = root.rchild;
			root.rchild = tmp;
		}
		if (root.lchild != null) {
			exchange(root.lchild);
		}
		if (root.rchild != null) {
			exchange(root.rchild);
		}
	}

	/**
	 * 删除以x为根的子树（由于未限定入参为二叉排序树，所以使用层次遍历，时间复杂度为O(n)，而非BST的O(logn)） p122-11
	 * 
	 * @param tree
	 * @param x
	 * @return
	 * @throws JDSException
	 */
	public static BinaryTree removeSubtree(BinaryTree tree, int x) throws JDSException {
		// 思路：多维护parent节点
		LinkQueue queue = new LinkQueue(tree.root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.getHead();
			queue.remove();
			if (node.key == x) {
				// if root.key == x
				if (node == tree.root) {
					tree.root = null;
				}
				// delete
				if (node.parent.lchild == node) {
					node.parent.lchild = null;
				}
				if (node.parent.rchild == node) {
					node.parent.rchild = null;
				}
			}
			if (node.lchild != null) {
				node.lchild.parent = node;
				queue.insert(node.lchild);
			}
			if (node.rchild != null) {
				node.rchild.parent = node;
				queue.insert(node.rchild);
			}
		}
		return tree;
	}


	/**
	 * 寻找关键字为x的节点的所有祖先
	 * p122-12
	 * @param tree
	 * @param x
	 * @return
	 * @throws JDSException 
	 */
	public static Vector<TreeNode> getAncestors(BinaryTree tree,int x) throws JDSException {
		// 二叉搜索树求祖先的算法实现非常方便
		// 思路一：利用二叉树数组存储结构中，节点索引值与其孩子节点索引值之间的函数关系
		List<TreeNode> treeArray = transToTreeArray(tree);
		Vector<TreeNode> ancestors = new Vector<>();
		// 获取x的索引，并向上追溯直至根节点
		int index = -1,pIndex = -1;
		for (TreeNode node: treeArray) {
			if (node != null && node.key == x) {
				index  = treeArray.indexOf(node);
				break;
			} 
		}
		if (index == -1) {
			throw new JDSException("该节点不存在");
		}
		// 使用index!=0而非pIndex!=0作为condition，是为了避免对当前节点为根节点的情形做单独处理
		while (index != 0) {
			pIndex = (int) Math.floor((index-1)/2);
			ancestors.add(treeArray.get(pIndex));
			index = pIndex;
		}
		return ancestors;
	}
	
	/**
	 * 转化为数组存储结构的二叉树（层次遍历顺序）
	 * @param tree
	 * @return
	 * @throws JDSException
	 */
	private static List<TreeNode> transToTreeArray(BinaryTree tree) throws JDSException{
		LinkQueue queue = new LinkQueue(tree.root);
		List<TreeNode> treeArray = new ArrayList<>();
		treeArray.add(tree.root);
		while (!queue.isEmpty()) {
			TreeNode headNode = queue.getHead();
			queue.remove();
			if (headNode.lchild != null) {
				queue.insert(headNode.lchild);
				treeArray.add(headNode.lchild);
			}else {
				treeArray.add(headNode.lchild);
			}
			if (headNode.rchild != null) {
				queue.insert(headNode.rchild);
				treeArray.add(headNode.rchild);
			}else {
				treeArray.add(headNode.rchild);
			}
		}	
		return treeArray;
	}

	/**
	 * 寻找最近公共祖先
	 * p122-13
	 * @param tree
	 * @param p
	 * @param q
	 * @return
	 * @throws JDSException 
	 */
	public static TreeNode findCommonAncestor(BinaryTree tree,TreeNode p,TreeNode q) throws JDSException {
		// 思路：利用已经实现的getAncestors()这个轮子，获取p和q所有祖先
		TreeNode r = new TreeNode();
		Vector<TreeNode> pA = getAncestors(tree, p.key);
		Vector<TreeNode> qA = getAncestors(tree, q.key);
		if (pA.size()>0 && qA.size()>0) {
			for (TreeNode pAncestor : pA) {
				for (TreeNode qAncestor : qA) {
					if (pAncestor == qAncestor) {
						r = pAncestor;
					}
				}
			}
		}
		return r;
	}
	
	/**
	 * 按从左至右的顺序连接叶子节点
	 * p122-16
	 * @param bt
	 * @return
	 * @throws JDSException 
	 */
	public static List<TreeNode> joinLeafNodes(BinaryTree bt) throws JDSException {
		// 思路：从左至右顺序，可以联想到中序遍历获取叶子节点，然后连接这些叶子节点
		LinkStack stack  = new LinkStack();
		List<TreeNode> leafList = new ArrayList<>();
		stack.push(bt.root);
		while (!stack.isEmpty()) {
			TreeNode topNode = stack.getTop();
			// 需要判断topNode的左孩子是否已经访问过，避免陷入死循环
			if (topNode.lchild!=null && !topNode.lchild.hasVisited) {
				stack.push(topNode.lchild);
			}else {
				if (topNode.rchild != null) {
					stack.pop();
					topNode.hasVisited = true;
					stack.push(topNode.rchild);
				}else {
					if (topNode.isLeaf() && leafList.size()>0) {
						leafList.get(leafList.size()-1).rchild = topNode;
					}
					if (topNode.isLeaf()) {
						leafList.add(topNode);
					}
					stack.pop();
					topNode.hasVisited = true;
				}
			}
		}
		return leafList;
	}  
	
	
	/**
	 * 判断两颗二叉树是否相似
	 * p122-17
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static boolean isSimilar(TreeNode t1,TreeNode t2) {
		if (t1==null && t2==null) {
			return true;
		}
		if (t1!=null && t2!= null) {
			if (isSimilar(t1.lchild, t2.lchild) && isSimilar(t1.rchild, t2.rchild)) {
				return true;
			}	
		}
		return false;
	}
	
}
