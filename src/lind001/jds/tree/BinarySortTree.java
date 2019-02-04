package lind001.jds.tree;

import lind001.jds.exception.JDSException;

/**
 * 二叉搜索树
 * 
 * @author lind001
 * @date 2018/09/26
 */
public class BinarySortTree extends BinaryTree {

    /**
     * 构造函数（构造空树）
     */
    public BinarySortTree() {
        this.root = null;
    }

    /**
     * 构造函数
     * 
     * @param root
     */
    public BinarySortTree(TreeNode root) {
        this.root = root;
    }

    /**
     * 查找（树平衡时时间效率 O(log2N)，最坏情况下（插入节点有序）时间效率O（n））
     * 
     * @param key
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unused")
    public TreeNode find(int key) throws JDSException {
        if (this.root == null) {
            throw new JDSException("Can not find the element : this BST is empty!");
        } else {
            TreeNode current = this.root;
            while (current.key != key) {
                if (key < current.key) {
                    current = current.lchild;
                } else {
                    current = current.rchild;
                }
            }
            if (current == null) {
                throw new JDSException("Can not find the element");
            }
            return current;
        }
    }


    /**
     * 插入（以查找算法为基础）
     * 
     * @param node
     */
    public void insert(TreeNode node) {
        if (this.isEmpty()) {
            this.root = node;
        } else {
            TreeNode start = this.root;
            boolean hasInsert = false;
            while (!hasInsert) {
                if (node.key < start.key || node.key == start.key) {
                    if (start.lchild == null) {
                        start.lchild = node;
                        hasInsert = true;
                    } else {
                        start = start.lchild;
                    }
                } else if (node.key > start.key) {
                    if (start.rchild == null) {
                        start.rchild = node;
                        hasInsert = true;
                    } else {
                        start = start.rchild;
                    }
                }
            }
        }
    }


    /**
     * 删除（以查找算法为基础）
     * 
     * @param key
     * @throws JDSException
     */
    public void delete(int key) throws JDSException {
        // ①首先获取要删除的节点及其父亲节点
        TreeNode current = this.root;
        TreeNode parent = this.root;
        boolean isLeftChild = false;
        while (current.key != key) {
            parent = current;
            if (key < current.key) {
                isLeftChild = true;
                current = current.lchild;
            } else {
                isLeftChild = false;
                current = current.rchild;
            }
            if (current == null) {
                throw new JDSException("can not find this node");
            }
        }
        // ②分情况讨论：1>被删除节点为叶子节点 2>被删除节点有一个孩子 3>被删除节点是满节点
        if (current.isLeaf()) {
            if (isLeftChild) {
                parent.lchild = null;
            } else {
                parent.rchild = null;
            }
        } else if (current.isFull()) {
            TreeNode successor = this.getSuccessor(current)[0];
            TreeNode sParent = this.getSuccessor(current)[1];
            // case1: 被删除节点的中序后继是其右子节点
            if (current.rchild == successor) {
                current.rchild = null;
                successor.lchild = current.lchild;
                current.lchild = null;
                if (isLeftChild) {
                    parent.lchild = successor;
                } else {
                    parent.rchild = successor;
                }
            }
            // case2: 被删除节点的中序后继是其右子节点的左子孙节点，且该后继没有右子树
            else if (successor.rchild == null) {
                sParent.lchild = null;
                successor.lchild = current.lchild;
                current.lchild = null;
                successor.rchild = current.rchild;
                current.rchild = null;
                if (isLeftChild) {
                    parent.lchild = successor;
                } else {
                    parent.rchild = successor;
                }
            }
            // case3: 被删除节点的中序后继是其右子节点的左子孙节点，且该后继有右子树
            else {
                sParent.lchild = successor.rchild;
                successor.rchild = current.rchild;
                current.rchild = null;
                successor.lchild = current.lchild;
                current.lchild = null;
                if (isLeftChild) {
                    parent.lchild = successor;
                } else {
                    parent.rchild = successor;
                }
            }
        } else {
            if (current.lchild != null) {
                if (isLeftChild) {
                    // LL
                    parent.lchild = current.lchild;
                    current.lchild = null;
                } else {
                    // RL
                    parent.rchild = current.lchild;
                    current.lchild = null;
                }
            } else {
                if (isLeftChild) {
                    // LR
                    parent.lchild = current.rchild;
                    current.rchild = null;
                } else {
                    // RR
                    parent.rchild = current.rchild;
                    current.rchild = null;
                }
            }
        }
    }

    /**
     * 获取当前节点的中序后继节点及后继的父亲节点
     * 
     * @param current
     * @return
     */
    private TreeNode[] getSuccessor(TreeNode current) {
        TreeNode[] nodes = new TreeNode[2];
        TreeNode successor = current.rchild;
        TreeNode sParent = current;
        while (successor.lchild != null) {
            sParent = successor;
            successor = successor.lchild;
        }
        nodes[0] = successor;
        nodes[1] = sParent;
        return nodes;
    }


}
