package lind001.jds.heap;

import lind001.jds.exception.JDSException;
import lind001.jds.queue.LinkQueue;
import lind001.jds.tree.BinaryTree;
import lind001.jds.tree.TreeNode;

/**
 * 堆树类（基于二叉树实现）
 * 
 * @author lind001
 * @date 2019/01/24
 */
public class TreeHeap {
    /**
     * 二叉树，不需要二叉搜索树（堆是弱序的）
     */
    public BinaryTree tree;

    /**
     * 构造函数
     * 
     * @param root
     */
    public TreeHeap(TreeNode root) {
        this.tree = new BinaryTree(root);
    }

    /**
     * 构造函数
     * 
     * @param rootKey
     */
    public TreeHeap(int rootKey) {
        TreeNode root = new TreeNode(rootKey);
        this.tree = new BinaryTree(root);
    }

    /**
     * 插入节点（要保证弱序）
     * 
     * @param key
     */
    public void insert(int key) {
        TreeNode node = new TreeNode(key);
        // find the location to insert first（思路：从根节点开始扫描找到未满节点）
        TreeNode start = this.tree.root;
        while (start.isFull()) {
            // only if its left child is also full, turn and try its right child
            start = start.lchild.isFull() ? (start.rchild.isFull() ? start.lchild : start.rchild) : start.lchild;
        }
        if (start.lchild == null) {
            start.lchild = node;
        } else {
            start.rchild = node;
        }
        node.parent = start;
        // then move up until the new treeNode reach a proper location（思路：扫描父亲节点，若不符合弱序特性，则交换节点，注意维护父亲和孩子关联关系）
        while (node != this.tree.root) {
            if (node.key > node.parent.key) {
                int tmp = node.parent.key;
                node.parent.key = node.key;
                node.key = tmp;
            }
            node = node.parent;
        }
    }

    /**
     * 获取堆顶节点
     * 
     * @return
     * @throws JDSException
     */
    public TreeNode getTopNode() throws JDSException {
        if (this.tree.isEmpty()) {
            throw new JDSException("this heap is empty");
        }
        return this.tree.root;
    }

    /**
     * 获取层次遍历的最后一个节点
     * 
     * @return
     * @throws JDSException
     */
    private TreeNode getLastByLevelOrder() throws JDSException {
        TreeNode lastNode = null;
        LinkQueue linkQueue = new LinkQueue(this.tree.root);
        while (!linkQueue.isEmpty()) {
            TreeNode headNode = linkQueue.getHead();
            lastNode = linkQueue.last;
            linkQueue.remove();
            if (headNode.lchild != null) {
                linkQueue.insert(headNode.lchild);
            }
            if (headNode.rchild != null) {
                linkQueue.insert(headNode.rchild);
            }
        }
        return lastNode;
    }

    /**
     * 移除堆顶节点（要保持弱序）
     * 
     * @throws JDSException
     */
    public void remove() throws JDSException {
        if (this.tree.isEmpty()) {
            throw new JDSException("this heap is empty");
        }
        TreeNode last = this.getLastByLevelOrder();
        // ①初始化堆底节点及其父节点的指针域
        if (last != this.tree.root) {
            if (last.parent.lchild == last) {
                last.parent.lchild = null;
            } else if (last.parent.rchild == last) {
                last.parent.rchild = null;
            } else {
                throw new JDSException("该树节点的指针域有误！");
            }
            last.parent = null;
            // ②交换堆顶与堆底的关键字，并向下移动直到新的堆顶关键字到达合适位置
            this.tree.root.key = last.key;
            TreeNode start = this.tree.root;
            while (!start.isLeaf()) {
                TreeNode choosen = start.lchild != null ?
                    (start.rchild != null ? (start.lchild.key < start.rchild.key ? start.rchild : start.lchild)
                        : start.lchild)
                    : start.rchild;
                if (choosen.key > start.key) {
                    int tmpKey = start.key;
                    start.key = choosen.key;
                    choosen.key = tmpKey;
                }
                start = choosen;
            }
        } else {
            this.tree.root = null;
        }
    }

    /**
     * 二叉树遍历
     * 
     * @throws Exception
     */
    public void display() throws JDSException {
        this.tree.inOrder();
        System.out.println("");
        this.tree.preOrder();
        System.out.println("");
        this.tree.postOrder();
        System.out.println("");
        this.tree.levelOrder();
    }


}
