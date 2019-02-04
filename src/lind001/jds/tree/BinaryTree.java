package lind001.jds.tree;

import lind001.jds.exception.JDSException;
import lind001.jds.queue.LinkQueue;
import lind001.jds.stack.LinkStack;

/**
 * 二叉树类
 * 
 * @author lind001
 * @date 2018/09/26
 */
public class BinaryTree extends Tree {

    public TreeNode root;

    /**
     * 构造函数（构造空树）
     */
    public BinaryTree() {
        this.root = null;
    }

    /**
     * 构造函数
     * 
     * @param root
     */
    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    /**
     * 先序非递归遍历
     * 
     * @throws JDSException
     */
    @Override
    public void preOrder() throws JDSException {
        System.out.print("preOrder: ");
        LinkStack stack = new LinkStack();
        stack.push(this.root);
        while (!stack.isEmpty()) {
            TreeNode topNode = stack.getTop();
            System.out.print(topNode.key + " ");
            stack.pop();
            // the order of pushing into stack should be contrary to preOrder(root->left child->right child)
            if (topNode.rchild != null) {
                stack.push(topNode.rchild);
            }
            if (topNode.lchild != null) {
                stack.push(topNode.lchild);
            }
        }
    }

    /**
     * 中序非递归遍历
     * 
     * @throws JDSException
     */
    @Override
    public void inOrder() throws JDSException {
        System.out.print("inOrder: ");
        LinkStack stack = new LinkStack();
        // first put root node into the link stack
        stack.push(this.root);
        // while stack is not empty
        while (!stack.isEmpty()) {
            // get (notice: not pop) top element of the stack, then check whether its left child exists
            TreeNode topNode = stack.getTop();
            if (topNode.lchild != null && !topNode.lchild.hasVisited) {
                // if its left child exists, then push its left child into stack
                stack.push(topNode.lchild);
            } else {
                // if its left child does not exist, check whether its right child exists
                if (topNode.rchild != null) {
                    // if its right child exists, first pop it and then push its right child into stack
                    System.out.print(topNode.key + " ");
                    topNode.hasVisited = true;
                    stack.pop();
                    stack.push(topNode.rchild);
                } else {
                    // if neither its left child nor its right child does not exist, then pop it
                    System.out.print(topNode.key + " ");
                    topNode.hasVisited = true;
                    stack.pop();
                }
            }
        }
        // reset
        this.resetHasVisited(this.root);
    }

    /**
     * 后序非递归遍历
     * 
     * @throws JDSException
     */
    @Override
    public void postOrder() throws JDSException {
        System.out.print("postOrder: ");
        LinkStack stack = new LinkStack();
        stack.push(this.root);
        while (!stack.isEmpty()) {
            TreeNode topNode = stack.getTop();
            if (topNode.lchild != null && !topNode.lchild.hasVisited) {
                stack.push(topNode.lchild);
            } else {
                if (topNode.rchild != null && !topNode.rchild.hasVisited) {
                    stack.push(topNode.rchild);
                } else {
                    System.out.print(topNode.key + " ");
                    topNode.hasVisited = true;
                    stack.pop();
                }
            }
        }
        // reset
        this.resetHasVisited(this.root);
    }


    /**
     * 重置hasVistied标记，以用于中序非递归遍历、后序非递归遍历等多次使用（采用先序递归）
     * 
     * @param node
     */
    private void resetHasVisited(TreeNode node) {
        if (node != null) {
            node.hasVisited = false;
            if (node.lchild != null) {
                this.resetHasVisited(node.lchild);
            }
            if (node.rchild != null) {
                this.resetHasVisited(node.rchild);
            }
        }

    }

    /**
     * 层次遍历（与广度优先搜索类似，辅助数据结构：队列） （在输出格式上做了优化，但仅限于满二叉树这类节点总数与层数有确定函数关系的二叉树）
     * 
     * @throws JDSException
     */
    @Override
    public void levelOrder() throws JDSException {
        System.out.println("levelOrder: ");
        LinkQueue linkQueue = new LinkQueue(this.root);
        int count = 0, threshold = 1, layerMax = 1;
        while (!linkQueue.isEmpty()) {
            TreeNode headNode = linkQueue.getHead();
            System.out.print(headNode.key + " ");
            count++;
            if (count == threshold) {
                System.out.println("");
                layerMax = layerMax + layerMax;
                threshold = layerMax + count;
            }
            linkQueue.remove();
            if (headNode.lchild != null) {
                linkQueue.insert(headNode.lchild);
            }
            if (headNode.rchild != null) {
                linkQueue.insert(headNode.rchild);
            }
        }
    }


    /**
     * 是否为空
     * 
     */
    @Override
    public boolean isEmpty() {
        if (this.root == null) {
            return true;
        }
        return false;
    }


}
